package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityAdminProfileBinding;
import hajarshaufi.fyp.databinding.ActivityProfileBinding;

public class AdminProfile extends AppCompatActivity {

    private ActivityAdminProfileBinding binding;
    TextView nameEdt, usernameEdt;
    private static String name;
    private static String username;
    private static String password;
    private static String staffNo;
    //List<Account> retrieveList;
    String url = "http://10.200.66.178/mymedtrip/adminProfile.php?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        binding = ActivityAdminProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get All ids
        nameEdt = findViewById(R.id.name);
        usernameEdt = findViewById(R.id.username);

        getData();

        //Personal Details NOT FINISHED YET
        binding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminProfile.this, PersonalDetails.class));
            }
        });

        //logout
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminProfile.this, LoginActivity.class));
            }
        });

        //Bottom Navigation View
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomProfile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomDashboard:
                    startActivity(new Intent(getApplicationContext(), AdminEstablishment.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottomManageAccount:
                    startActivity(new Intent(getApplicationContext(), AdminManageAccount.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottomProfile:
                    return true;
            }
            return false;
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
                        Toast.makeText(AdminProfile.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONS(String response) {
        String dataName = null;
        String dataUsername = null;
        String dataPassword = null;
        String dataStaffNo = null;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data = result.getJSONObject(0);
            dataName = data.getString("name");
            dataUsername = data.getString("username");
            dataPassword = data.getString("password");
            dataStaffNo = data.getString("staffNo");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        nameEdt.setText(dataName);
        usernameEdt.setText(dataUsername);

        name = dataName;
        username = dataUsername;
        password = dataPassword;
        staffNo = dataStaffNo;
    }

    public static String getDataName(){

        return name;
    }

    public static String getDataUsername(){

        return username;
    }

    public static String getDataPassword(){

        return password;
    }

    public static String getDataStaffNo(){

        return staffNo;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}