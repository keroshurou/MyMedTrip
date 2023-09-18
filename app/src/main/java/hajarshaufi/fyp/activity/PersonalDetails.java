package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hajarshaufi.fyp.R;

public class PersonalDetails extends AppCompatActivity {

    TextView tvName, tvEmail, tvUsername, tvPassword;
    private static String name;
    private static String username;
    private static String email;
    private static String password;
    ImageButton editBtn;
    String url = "http://10.200.66.178/mymedtrip/profile.php?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        getData();

        //Get All ids
        tvName = findViewById(R.id.name);
        tvEmail = findViewById(R.id.email);
        tvUsername = findViewById(R.id.username);
        tvPassword = findViewById(R.id.password);
        tvPassword.setTransformationMethod(new PasswordTransformationMethod());
        editBtn = findViewById(R.id.editBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalDetails.this, EditPersonalDetails.class));
            }
        });

    }

    private void getData() {

        String username = LoginActivity.getValue();

        if (username.equals("")) {

            Toast.makeText(this, "Check Detail!", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSONS(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PersonalDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSONS(String response) {

        String dataName = null;
        String dataUsername = null;
        String dataEmail = null;
        String dataPassword = null;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data = result.getJSONObject(0);
            dataName = data.getString("name");
            dataUsername = data.getString("username");
            dataEmail = data.getString("email");
            dataPassword = data.getString("password");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Set Info
        tvName.setText(dataName);
        tvEmail.setText(dataEmail);
        tvUsername.setText(dataUsername);
        tvPassword.setText(dataPassword);

        name = dataName;
        username = dataUsername;
        email = dataEmail;
        password = dataPassword;
    }

    public static String getName(){
        return name;
    }

    public static String getUsername(){
        return username;
    }

    public static String getEmail(){
        return email;
    }

    public static String getPassword(){
        return password;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(PersonalDetails.this, Profile.class));
    }
}