public interface ApiService {
    @POST("entry/park")
    Call<ApiResponse> park(@Body VehicleDto vehicle);

    @POST("exit/leave")
    Call<ApiResponse> leave(@Query("plate") String plate);

    @GET("slots/status")
    Call<List<Slot>> getSlots();
}
