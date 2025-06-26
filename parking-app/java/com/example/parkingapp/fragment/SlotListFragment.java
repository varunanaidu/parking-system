public class SlotListFragment extends Fragment {

    private ListView slotListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slot_list, container, false);
        slotListView = view.findViewById(R.id.slotListView);

        SessionManager session = new SessionManager(requireContext());
        ApiService api = RetrofitClient.getInstance(session).create(ApiService.class);

        api.getSlots().enqueue(new Callback<List<Slot>>() {
            @Override
            public void onResponse(Call<List<Slot>> call, Response<List<Slot>> response) {
                if (response.isSuccessful()) {
                    List<Slot> slots = response.body();
                    List<String> slotDesc = new ArrayList<>();
                    for (Slot s : slots) {
                        slotDesc.add("Lantai " + s.getFloor() + " - Slot " + s.getNumber() + ": " +
                                (s.isOccupied() ? "Terisi" : "Kosong"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                            android.R.layout.simple_list_item_1, slotDesc);
                    slotListView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Slot>> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal load slot: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
