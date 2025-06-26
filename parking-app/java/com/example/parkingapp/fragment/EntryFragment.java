public class EntryFragment extends Fragment {

    private EditText etPlate;
    private Spinner spType;
    private Button btnPark;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entry, container, false);

        etPlate = view.findViewById(R.id.etPlate);
        spType = view.findViewById(R.id.spType);
        btnPark = view.findViewById(R.id.btnPark);

        // Setup Spinner
        String[] vehicleTypes = {"MOTORCYCLE", "CAR", "BUS"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, vehicleTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(adapter);

        btnPark.setOnClickListener(v -> {
            String plate = etPlate.getText().toString();
            String type = spType.getSelectedItem().toString();

            VehicleDto vehicle = new VehicleDto();
            vehicle.setPlate(plate);
            vehicle.setType(type);

            SessionManager session = new SessionManager(requireContext());
            ApiService api = RetrofitClient.getInstance(session).create(ApiService.class);

            api.park(vehicle).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Parkir sukses: " + response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Gagal parkir: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        return view;
    }
}
