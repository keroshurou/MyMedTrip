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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import hajarshaufi.fyp.R;

public class MedicalDetails extends AppCompatActivity {

    TextView tvBloodType, tvAllergies, tvMedCon, tvMedications;

    String url = "http://10.200.66.4/mymedtrip/medDetails.php?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_details);

        //Get All ids
        tvBloodType = findViewById(R.id.bloodType);
        tvAllergies = findViewById(R.id.allergies);
        tvMedCon = findViewById(R.id.medCon);
        tvMedications = findViewById(R.id.medications);

        getData();
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
                        Toast.makeText(MedicalDetails.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSONS(String response) {

        String dataBloodType = null;
        String dataAllergies = null;
        String dataMedConditions = null;
        String dataMedications = null;

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data = result.getJSONObject(0);
            dataBloodType = data.getString("bloodType");
            dataAllergies = data.getString("allergies");
            dataMedConditions = data.getString("medConditions");
            dataMedications = data.getString("medications");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        tvBloodType.setText(dataBloodType);
        tvAllergies.setText(dataAllergies);
        tvMedCon.setText(dataMedConditions);
        tvMedications.setText(dataMedications);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(MedicalDetails.this, Profile.class));
    }
}