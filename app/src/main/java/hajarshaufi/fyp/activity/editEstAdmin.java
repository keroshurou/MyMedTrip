package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class editEstAdmin extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner typeSpinner, tag1Spinner, tag2Spinner;
    EditText establishmentNameEdt, addressEdt, daysEdt, hoursEdt, days2Edt, hours2Edt, cityEdt;
    Button editBtn;

    TextView estType,estName,estAddress,estDays,estHours,estDays2,estHours2,estCity,tagging;
    private String id,type,establishmentName,address,days,hours,days2,hours2,city,tag1,tag2,tag3;
    private String tripType,tripName, tripAddress, tripDays, tripHours, tripDays2, tripHours2, tripCity, tripTag1, tripTag2;

    String url = "http://10.200.66.178/mymedtrip/editEstablishment.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_est_admin);

        //Get data from previous activity
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        establishmentName = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        days = intent.getStringExtra("days");
        hours = intent.getStringExtra("hours");
        days2 = intent.getStringExtra("days2");
        hours2 = intent.getStringExtra("hours2");
        tag1 = intent.getStringExtra("tag1");
        tag2 = intent.getStringExtra("tag2");
        tag3 = intent.getStringExtra("tag3");

        //find view
        estType = findViewById(R.id.txtVw);
        estName = findViewById(R.id.establishmentName);
        estName.setEnabled(false);
        estAddress = findViewById(R.id.address);
        estDays = findViewById(R.id.days);
        estHours = findViewById(R.id.hours);
        estDays2 = findViewById(R.id.days2);
        estHours2 = findViewById(R.id.hours2);
        estCity = findViewById(R.id.city);
        tagging = findViewById(R.id.txtVw4);
        editBtn = findViewById(R.id.editBtn);
        typeSpinner = findViewById(R.id.spinnerType);
        tag1Spinner = findViewById(R.id.tag1);
        tag2Spinner = findViewById(R.id.tag2);

        //Set text
        estType.setText("Type: "+type);
        estName.setText(establishmentName);
        estAddress.setText(address);
        estDays.setText(days);
        estHours.setText(hours);
        estDays2.setText(days2);
        estHours2.setText(hours2);
        estCity.setText(tag1);
        tagging.setText("Tagging: "+tag2+","+tag3);

        //Spinner Type
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(editEstAdmin.this, R.array.type, android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapterType);
        typeSpinner.setOnItemSelectedListener(editEstAdmin.this);

        //Spinner Tag 1
        ArrayAdapter<CharSequence> adapterTag1 = ArrayAdapter.createFromResource(editEstAdmin.this, R.array.tagging, android.R.layout.simple_spinner_item);
        adapterTag1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        tag1Spinner.setAdapter(adapterTag1);
        tag1Spinner.setOnItemSelectedListener(editEstAdmin.this);

        //Spinner Tag 2
        ArrayAdapter<CharSequence> adapterTag2 = ArrayAdapter.createFromResource(editEstAdmin.this, R.array.tagging, android.R.layout.simple_spinner_item);
        adapterTag2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        tag2Spinner.setAdapter(adapterTag2);
        tag2Spinner.setOnItemSelectedListener(editEstAdmin.this);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Call method, getEst = get all data that need to pass to database
                getEst();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long longTime2 = System.currentTimeMillis();
                        startActivity(new Intent(getApplicationContext(), AdminEstablishment.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });
    }

    private void getEst() {

        tripType = typeSpinner.getSelectedItem().toString().trim();
        tripName = estName.getText().toString();
        tripAddress = estAddress.getText().toString();
        tripDays = estDays.getText().toString();
        tripHours = estHours.getText().toString();
        tripDays2 = estDays2.getText().toString();
        tripHours2 = estHours2.getText().toString();
        tripCity = estCity.getText().toString();
        tripTag1 = tag1Spinner.getSelectedItem().toString().trim();
        tripTag2 = tag2Spinner.getSelectedItem().toString().trim();

        //Set error if any fields is empty
        if (TextUtils.isEmpty(tripAddress)){
            estAddress.setError("Please enter address");
        }else if(TextUtils.isEmpty(tripDays)){
            estDays.setError("Please enter operating day");
        }else if(TextUtils.isEmpty(tripHours)) {
            estHours.setError("Please enter operating hours");
        }else if(TextUtils.isEmpty(tripDays2)) {
            estDays2.setError("Please enter operating day");
        }else if(TextUtils.isEmpty(tripHours2)) {
            estHours2.setError("Please enter operating hours");
        }else if(TextUtils.isEmpty(tripCity)) {
            estCity.setError("Please enter city");
        }else {
            addDataToDatabase(tripType, tripName, tripAddress, tripDays, tripHours,
                    tripDays2, tripHours2, tripCity, tripTag1, tripTag2);

//            Intent intentSuccess = new Intent(this, AddSuccess.class);
//            startActivity(intentSuccess);
        }
    }

    private void addDataToDatabase(String tripType, String tripName,
                                   String tripAddress, String tripDays, String tripHours, String tripDays2,
                                   String tripHours2, String tripCity, String tripTag1, String tripTag2) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(editEstAdmin.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    //Add est successful
                    if (object.get("code").equals("201")) {
                        Toast.makeText(editEstAdmin.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(editEstAdmin.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Trip already exist
                    if (object.get("code").equals("200")) {
                        Toast.makeText(editEstAdmin.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(editEstAdmin.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                //params.put("id", id);
                params.put("type", tripType);
                params.put("name", tripName);
                params.put("address", tripAddress);
                params.put("days", tripDays);
                params.put("hours", tripHours);
                params.put("days2", tripDays2);
                params.put("hours2", tripHours2);
                params.put("city", tripCity);
                params.put("tag1", tripTag1);
                params.put("tag2", tripTag2);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}