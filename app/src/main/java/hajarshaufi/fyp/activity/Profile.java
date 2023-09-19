package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityHelpModuleBinding;
import hajarshaufi.fyp.databinding.ActivityProfileBinding;
import hajarshaufi.fyp.java.Account;
import hajarshaufi.fyp.java.Establishment;

public class Profile extends AppCompatActivity {

    private ActivityProfileBinding binding;
    TextView nameEdt, usernameEdt;
    private static String name;
    private static String username;
    private static String email;
    private static String password;
    //List<Account> retrieveList;
    String url = "http://10.200.66.178/mymedtrip/profile.php?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Get All ids
        nameEdt = findViewById(R.id.name);
        usernameEdt = findViewById(R.id.username);

        getData();

        //Personal Details
        binding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, PersonalDetails.class));
            }
        });

        //Medical Details
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, MedicalDetails.class));
            }
        });

        //logout
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an AlertDialog Builder
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Profile.this);

                // Get the custom dialog layout
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.change_status_dialog, null);

                // Set the custom layout to the dialog
                dialogBuilder.setView(dialogView);

                // Initialize the dialog elements
                TextView dialogTitle = dialogView.findViewById(R.id.alertTitle);
                TextView dialogMessage = dialogView.findViewById(R.id.alertMessage);
                TextView positiveButton = dialogView.findViewById(R.id.yesBtn);
                TextView negativeButton = dialogView.findViewById(R.id.cancelBtn);

                dialogTitle.setText("Logout");
                dialogMessage.setText("Are you sure you want to logout?");

                // Create and show the dialog
                AlertDialog dialog = dialogBuilder.create();
                dialog.show();

                // Set click listeners for the buttons
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Profile.this, LoginActivity.class));
                    }
                });

                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

        //Bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomDashboard);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.userExplore:
                    startActivity(new Intent(getApplicationContext(), Homepage.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.userTrip:
                    startActivity(new Intent(getApplicationContext(), TripPageUpcoming.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.userHelp:
                    startActivity(new Intent(getApplicationContext(), HelpModule.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.userProfile:
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
                        Toast.makeText(Profile.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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

        nameEdt.setText(dataName);
        usernameEdt.setText(dataUsername);

        name = dataName;
        username = dataUsername;
        email = dataEmail;
        password = dataPassword;
    }

    public static String getDataName(){

        return name;
    }

    public static String getDataUsername(){

        return username;
    }

    public static String getDataEmail(){

        return email;
    }

    public static String getDataPassword(){

        return password;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}