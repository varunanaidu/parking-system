public class LoginActivity extends AppCompatActivity {
    EditText usernameEt, passwordEt;
    Button loginBtn;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEt = findViewById(R.id.etUsername);
        passwordEt = findViewById(R.id.etPassword);
        loginBtn = findViewById(R.id.btnLogin);
        session = new SessionManager(this);

        loginBtn.setOnClickListener(v -> {
            String user = usernameEt.getText().toString();
            String pass = passwordEt.getText().toString();
            if (!user.isEmpty() && !pass.isEmpty()) {
                session.saveAuth(user, pass);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Username & Password required", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
