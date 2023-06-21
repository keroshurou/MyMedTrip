package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import hajarshaufi.fyp.R;

public class SaveEst extends AppCompatActivity {

    TextView dateEdt, timeEdt;
    String statusDefault = "upcoming";
    Button saveTrip;

    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    int position;
    TextView estType,estName,estAddress,estDays,estHours,estDays2,estHours2,estCity,estTag1,estTag2,estTag3;
    private String type,name,address,days,hours,days2,hours2,city,tag1,tag2,tag3;
    private String tripdate,triptime,status,tripType,tripName, tripAddress, tripDays, tripHours, tripDays2, tripHours2, tripCity, tripTag1, tripTag2;
    String url = "http://192.168.212.86/mymedtrip/addTrip.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_est);

        //Get All ids
        dateEdt = findViewById(R.id.date);
        timeEdt = findViewById(R.id.time);
        saveTrip = findViewById(R.id.saveTrip);

        //date picker
        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = simpleDateFormat.format(calendar.getTime());

                    //Get day, month, year
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);

                    // date picker dialog
                    datePicker = new DatePickerDialog(SaveEst.this, R.style.DialogTheme,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    dateEdt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, year, month, day);
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePicker.show();
            }
        });

        //time picker
        timeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePicker = new TimePickerDialog(SaveEst.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0, hourOfDay, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                        timeEdt.setText(sdf.format(calendar.getTime()));

                    }
                }, 8, 00, false);
                timePicker.show();
            }
        });


        //Get data from previous activity
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        days = intent.getStringExtra("days");
        hours = intent.getStringExtra("hours");
        days2 = intent.getStringExtra("days2");
        hours2 = intent.getStringExtra("hours2");
        tag1 = intent.getStringExtra("tag1");
        tag2 = intent.getStringExtra("tag2");
        tag3 = intent.getStringExtra("tag3");

        //find view
        estType = findViewById(R.id.type);
        estName = findViewById(R.id.establishmentName);
        estAddress = findViewById(R.id.address);
        estDays = findViewById(R.id.days);
        estHours = findViewById(R.id.hours);
        estDays2 = findViewById(R.id.days2);
        estHours2 = findViewById(R.id.hours2);
        estTag1 = findViewById(R.id.tag1);
        estTag2 = findViewById(R.id.tag2);
        estTag3 = findViewById(R.id.tag3);

        //Set text
        estType.setText(type);
        estName.setText(name);
        estAddress.setText(address);
        estDays.setText(days);
        estHours.setText(hours);
        estDays2.setText(days2);
        estHours2.setText(hours2);
        estTag1.setText(tag1);
        estTag2.setText(tag2);
        estTag3.setText(tag3);

        saveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTrip();
            }
        });

    }

    private void getTrip() {

        tripdate = dateEdt.getText().toString();
        triptime = timeEdt.getText().toString();
        status = statusDefault;
        tripType = estType.getText().toString();
        tripName = estName.getText().toString();
        tripAddress = estAddress.getText().toString();
        tripDays = estDays.getText().toString();
        tripHours = estHours.getText().toString();
        tripDays2 = estDays2.getText().toString();
        tripHours2 = estHours2.getText().toString();
        tripCity = estTag1.getText().toString();
        tripTag1 = estTag2.getText().toString();
        tripTag2 = estTag3.getText().toString();

        addDataToDatabase(tripdate, triptime, status, tripType, tripName, tripAddress, tripDays, tripHours,
                tripDays2, tripHours2, tripCity, tripTag1, tripTag2);

    }

    private void addDataToDatabase(String tripdate, String triptime, String status, String tripType, String tripName,
                                   String tripAddress, String tripDays, String tripHours, String tripDays2,
                                   String tripHours2, String tripCity, String tripTag1, String tripTag2) {


        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(SaveEst.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    //Save Trip successful
                    if (object.get("code").equals("201")) {
                        Toast.makeText(SaveEst.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(SaveEst.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Trip already exist
                    if (object.get("code").equals("200")) {
                        Toast.makeText(SaveEst.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(SaveEst.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("tripdate", tripdate);
                params.put("triptime", triptime);
                params.put("status", status);
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


}