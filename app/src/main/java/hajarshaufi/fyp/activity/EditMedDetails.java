package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import hajarshaufi.fyp.R;

public class EditMedDetails extends AppCompatActivity {

    EditText edtBloodType, edtAllergies, edtMedCon, edtMedications;
    Button editBtn;
    private String bloodType, allergies, medCon, medications;

    String url = "http://10.200.66.178/mymedtrip/editMedDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med_details);

        //Get All ids
        edtBloodType = findViewById(R.id.bloodType);
        edtAllergies = findViewById(R.id.allergies);
        edtMedCon = findViewById(R.id.medCon);
        edtMedications = findViewById(R.id.medications);
        editBtn = findViewById(R.id.editBtn);

        edtBloodType.setText(MedicalDetails.getBloodType());
        edtAllergies.setText(MedicalDetails.getAllergies());
        edtMedCon.setText(MedicalDetails.getMedCon());
        edtMedications.setText(MedicalDetails.getMedications());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long longTime2 = System.currentTimeMillis();
                        startActivity(new Intent(getApplicationContext(), MedicalDetails.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });
    }

    private void getDetails() {

        String username = LoginActivity.getValue();

        //get data from EditText
        bloodType = edtBloodType.getText().toString();
        allergies = edtAllergies.getText().toString();
        medCon = edtMedCon.getText().toString();
        medications = edtMedications.getText().toString();

        if (TextUtils.isEmpty(bloodType)){
            edtBloodType.setError("Please enter blood type");
        } else if(TextUtils.isEmpty(allergies)){
            edtAllergies.setError("Please enter allergies, if none leave empty");
            edtAllergies.setText("NULL");
        } else if(TextUtils.isEmpty(medCon)) {
            edtMedCon.setError("Please enter medical conditions, if none leave empty");
            edtMedCon.setText("NULL");
        } else if(TextUtils.isEmpty(medications)) {
            edtMedications.setError("Please enter medications, if none leave empty");
            edtMedications.setText("NULL");
        } else {
            updateDatabase(username, bloodType, allergies, medCon, medications);
        }
    }

    private void updateDatabase(String username, String bloodType, String allergies, String medCon, String medications) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(EditMedDetails.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    //Update successful
                    if (object.get("code").equals("201")) {
                        Toast.makeText(EditMedDetails.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(EditMedDetails.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                edtBloodType.setText("");
                edtAllergies.setText("");
                edtMedCon.setText("");
                edtMedications.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(EditMedDetails.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("username", username);
                params.put("bloodType", bloodType);
                params.put("allergies", allergies);
                params.put("medConditions", medCon);
                params.put("medications", medications);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}