public class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getInstance(SessionManager session) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                        .addHeader("Authorization", session.getAuth())
                        .build();
                    return chain.proceed(request);
                }).build();

            retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
