package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import hajarshaufi.fyp.R;

public class PersonalDetails extends AppCompatActivity {

    TextView tvName, tvEmail, tvUsername, tvPassword;
    private static String name;
    private static String username;
    private static String email;
    private static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        //Get All ids
        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvUsername = findViewById(R.id.username);
        tvPassword = findViewById(R.id.password);

        //Get Info
        name = Profile.getDataName();
        username = Profile.getDataUsername();
        email = Profile.getDataEmail();
        password = Profile.getDataPassword();

        //Set Info
        tvName.setText(name);
        tvEmail.setText(email);
        tvUsername.setText(username);
        tvPassword.setText(password);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(PersonalDetails.this, Profile.class));
    }
}