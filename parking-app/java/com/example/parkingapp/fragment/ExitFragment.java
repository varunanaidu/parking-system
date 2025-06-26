public class ExitFragment extends Fragment {

    private EditText etExitPlate;
    private Button btnExit;
    private TextView tvFee;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exit, container, false);

        etExitPlate = view.findViewById(R.id.etExitPlate);
        btnExit = view.findViewById(R.id.btnExit);
        tvFee = view.findViewById(R.id.tvFee);

        btnExit.setOnClickListener(v -> {
            String plate = etExitPlate.getText().toString();

            SessionManager session = new SessionManager(requireContext());
            ApiService api = RetrofitClient.getInstance(session).create(ApiService.class);

            api.leave(plate).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        tvFee.setText("Biaya: " + response.body().getMessage());
                    } else {
                        tvFee.setText("Gagal keluar: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    tvFee.setText("Error: " + t.getMessage());
                }
            });
        });

        return view;
    }
}
