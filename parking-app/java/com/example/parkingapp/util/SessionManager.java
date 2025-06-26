public class SessionManager {
    private final SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences("parking_session", Context.MODE_PRIVATE);
    }

    public void saveAuth(String username, String password) {
        String basicAuth = "Basic " + Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);
        prefs.edit().putString("auth", basicAuth).apply();
    }

    public String getAuth() {
        return prefs.getString("auth", null);
    }
}
