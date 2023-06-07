package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class AddEstablishment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner typeSpinner;
    EditText establishmentNameEdt, addressEdt, daysEdt, hoursEdt, days2Edt, hours2Edt, cityEdt, tag1Edt, tag2Edt, tag3Edt;
    Button addBtn;

    private String type, establishmentName, address, days, hours, days2, hours2, city, tag1, tag2, tag3;

    String url = "http://192.168.219.86/mymedtrip/addEst.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_establishment);

        //get all ids
        typeSpinner = findViewById(R.id.spinnerType);
        establishmentNameEdt = findViewById(R.id.establishmentName);
        addressEdt = findViewById(R.id.address);
        daysEdt = findViewById(R.id.days);
        hoursEdt = findViewById(R.id.hours);
        days2Edt = findViewById(R.id.days2);
        hours2Edt = findViewById(R.id.hours2);
        cityEdt = findViewById(R.id.city);
        tag1Edt = findViewById(R.id.tag1);
        tag2Edt = findViewById(R.id.tag2);
        tag3Edt = findViewById(R.id.tag3);
        addBtn = findViewById(R.id.addBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddEstablishment.this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(AddEstablishment.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEst();
            }
        });

    }

    private void getEst(){

        type = typeSpinner.getSelectedItem().toString().trim();
        establishmentName = establishmentNameEdt.getText().toString();
        address = addressEdt.getText().toString();
        days = daysEdt.getText().toString();
        hours = hoursEdt.getText().toString();
        days2 = days2Edt.getText().toString();
        hours2 = hours2Edt.getText().toString();
        city = cityEdt.getText().toString();
        tag1 = tag1Edt.getText().toString();
        tag2 = tag2Edt.getText().toString();
        tag3 = tag3Edt.getText().toString();

        if (TextUtils.isEmpty(establishmentName)){
            establishmentNameEdt.setError("Please enter establishment name");
        } else if(TextUtils.isEmpty(address)){
            addressEdt.setError("Please enter address");
        } else if(TextUtils.isEmpty(days)) {
            daysEdt.setError("Please enter operating day");
        } else if(TextUtils.isEmpty(hours)) {
            hoursEdt.setError("Please enter operating hours");
        }else if(TextUtils.isEmpty(city)) {
            cityEdt.setError("Please enter Delivered Date");
        }else {
            addDataToDatabase(type, establishmentName, address, days, hours, days2, hours2, city, tag1, tag2, tag3);

//            Intent intentSuccess = new Intent(this, AddSuccess.class);
//            startActivity(intentSuccess);
        }
    }

    private void addDataToDatabase(String type, String establishmentName, String address,
                                   String days, String hours, String days2, String hours2,
                                   String city, String tag1, String tag2, String tag3) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(AddEstablishment.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(AddEstablishment.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                establishmentNameEdt.setText("");
                addressEdt.setText("");
                daysEdt.setText("");
                hoursEdt.setText("");
                days2Edt.setText("");
                hours2Edt.setText("");
                cityEdt.setText("");
                tag1Edt.setText("");
                tag2Edt.setText("");
                tag3Edt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddEstablishment.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("type", type);
                params.put("establishmentName", establishmentName);
                params.put("address", address);
                params.put("days", days);
                params.put("hours", hours);
                params.put("days2", days2);
                params.put("hours2", hours2);
                params.put("city", city);
                params.put("tag1", tag1);
                params.put("tag2", tag2);
                params.put("tag3", tag3);

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
        //expressBrand = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}