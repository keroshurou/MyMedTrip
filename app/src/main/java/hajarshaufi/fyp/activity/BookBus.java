package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hajarshaufi.fyp.R;

public class BookBus extends AppCompatActivity {

    Spinner selectRoutes, selectTime;
    private String selectedRoute, selectedTime;
    EditText ticketsEdt;
    List<String> timeSch = new ArrayList<>();
    Button bookBtn;
    TextView dateEdt;
    private DatePickerDialog datePicker;
    private String route, time, date, tickets;

    String url = "http://10.200.66.178/mymedtrip/addBusBooking.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_bus);

        //Get All ids
        ticketsEdt = findViewById(R.id.tickets);
        selectRoutes = findViewById(R.id.selectRoutes);
        selectTime = findViewById(R.id.selectTime);
        dateEdt = findViewById(R.id.date);
        bookBtn = findViewById(R.id.bookBtn);

        //Routes List
        List<String> routesList = new ArrayList<>();
        routesList.add("Select Route");
        routesList.add("Batang Melaka");
        routesList.add("Batu Berendam (Taman Merdeka)");
        routesList.add("Krubong");
        routesList.add("MITC 1");

        //Spinner Routes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BookBus.this, android.R.layout.simple_spinner_item, routesList);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        selectRoutes.setAdapter(arrayAdapter);

        selectRoutes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedRoute = parent.getItemAtPosition(position).toString();
                Toast.makeText(BookBus.this, selectedRoute, Toast.LENGTH_SHORT).show();
                if (selectedRoute.equals("Batang Melaka")){
                    //Spinner Time
                    timeSch.clear();
                    timeSch.add("Select Time");
                    timeSch.add("7.15 am");
                    timeSch.add("9.15 am");
                    timeSch.add("11.15 am");
                    timeSch.add("1.15 pm");
                    timeSch.add("3.15 pm");
                    timeSch.add("5.15 pm");
                    timeSch.add("7.15 pm");
                    fillSpinner();
                }else if (selectedRoute.equals("Batu Berendam (Taman Merdeka)")){
                    //Spinner Time
                    timeSch.clear();
                    timeSch.add("Select Time");
                    timeSch.add("7.00 am");
                    timeSch.add("9.30 am");
                    timeSch.add("12.00 pm");
                    timeSch.add("2.30 pm");
                    timeSch.add("5.00 pm");
                    timeSch.add("7.30 pm");
                    fillSpinner();
                }else if (selectedRoute.equals("Krubong")){
                    //Spinner Time
                    timeSch.clear();
                    timeSch.add("Select Time");
                    timeSch.add("8.00 am");
                    timeSch.add("11.00 am");
                    timeSch.add("2.00 pm");
                    timeSch.add("5.00 pm");
                    fillSpinner();
                }else if (selectedRoute.equals("MITC 1")){
                    //Spinner Time
                    timeSch.clear();
                    timeSch.add("Select Time");
                    timeSch.add("7.20 am");
                    timeSch.add("9.15 am");
                    timeSch.add("10.45 am");
                    timeSch.add("12.15 pm");
                    timeSch.add("1.45 pm");
                    timeSch.add("3.15 pm");
                    timeSch.add("5.00 pm");
                    timeSch.add("7.00 pm");
                    fillSpinner();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner time, get spinner time data
        selectTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedTime = parent.getItemAtPosition(position).toString();
                Toast.makeText(BookBus.this, selectedTime, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                datePicker = new DatePickerDialog(BookBus.this, R.style.DialogTheme,
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

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long longTime2 = System.currentTimeMillis();
                        startActivity(new Intent(getApplicationContext(), TripPageBus.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });

    }

    private void getData() {

        route = selectedRoute;
        time = selectedTime;
        date = dateEdt.getText().toString();
        tickets = ticketsEdt.getText().toString();

        //Set error if any fields is empty
        if (TextUtils.isEmpty(tickets)){
            ticketsEdt.setError("Please enter number of tickets");
        }else{
            addDataToDatabase(route, time, date, tickets);
        }
    }

    private void addDataToDatabase(String route, String time, String date, String tickets) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(BookBus.this);

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
                        Toast.makeText(BookBus.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(BookBus.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Trip already exist
                    if (object.get("code").equals("200")) {
                        Toast.makeText(BookBus.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                ticketsEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(BookBus.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("route", route);
                params.put("time", time);
                params.put("date", date);
                params.put("tickets", tickets);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }

    private void fillSpinner(){

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(BookBus.this, android.R.layout.simple_spinner_item, timeSch);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTime.setAdapter(timeAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BookBus.this, BusRoutes.class));
    }
}