package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hajarshaufi.fyp.R;

public class BookAttraction extends AppCompatActivity{

    List<String> number = new ArrayList<>();
    Spinner selectAttraction;
    EditText ticketsEdt;
    TextView dateEdt, timeEdt, budgetEdt;
    Button bookBtn;
    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    private String selectedAttr, attractions, date, time, tickets;

    String url = "http://10.200.66.178/mymedtrip/addAttrBooking.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_attraction);

        //Get All ids
        ticketsEdt = findViewById(R.id.tickets);
        selectAttraction = findViewById(R.id.selectAttr);
        dateEdt = findViewById(R.id.date);
        timeEdt = findViewById(R.id.time);
        //budgetEdt = findViewById(R.id.budget);
        bookBtn = findViewById(R.id.bookBtn);


        //AttrBooking List
        List<String> attrList = new ArrayList<>();
        attrList.add("Select Attraction");
        attrList.add("A'Famosa");
        attrList.add("Baba & Nyonya Heritage Museum");
        attrList.add("Jonker Street");
        attrList.add("Shore Sky Tower");
        attrList.add("Melaka River Cruise");

        //Spinner AttrBooking
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(BookAttraction.this, android.R.layout.simple_spinner_item, attrList);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        selectAttraction.setAdapter(arrayAdapter);

        selectAttraction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAttr = parent.getItemAtPosition(position).toString();
                Toast.makeText(BookAttraction.this, selectedAttr, Toast.LENGTH_SHORT).show();

//                if (selectedAttr.equals("A'Famosa")){
//                    budgetEdt.setText("Not Applicable");
//                }else if (selectedAttr.equals("Baba & Nyonya Heritage Museum")){
//                    //Spinner Number
//                    number.clear();
//                    number.add("Quantity");
//                    number.add("1");
//                    number.add("2");
//                    number.add("3");
//                    number.add("4");
//                    number.add("5");
//                    number.add("6");
//                    number.add("7");
//                    number.add("8");
//                    number.add("9");
//                    number.add("10");
//                    fillSpinner();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        ticketsEdt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                int quantity = Integer.parseInt(parent.getItemAtPosition(position).toString());
//                int budget = quantity*16;
//                budgetEdt.setText("RM"+budget);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

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
                datePicker = new DatePickerDialog(BookAttraction.this, R.style.DialogTheme,
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

                timePicker = new TimePickerDialog(BookAttraction.this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0, hourOfDay, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                        timeEdt.setText(sdf.format(calendar.getTime()));

                    }
                }, 8, 00, true);
                timePicker.show();
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
                        startActivity(new Intent(getApplicationContext(), TripPageAttractions.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });
    }

    private void getData() {

        attractions = selectedAttr;
        date = dateEdt.getText().toString();
        time = timeEdt.getText().toString();
        tickets = ticketsEdt.getText().toString();

        //Set error if any fields is empty
        if (TextUtils.isEmpty(tickets)){
            ticketsEdt.setError("Please enter number of tickets");
        }else if (TextUtils.isEmpty(date)){
            dateEdt.setError("Please enter date");
        }else if (TextUtils.isEmpty(time)){
            timeEdt.setError("Please enter time");
        }else{
            addDataToDatabase(attractions, time, date, tickets);
        }
    }

    private void addDataToDatabase(String attractions, String time, String date, String tickets) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(BookAttraction.this);

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
                        Toast.makeText(BookAttraction.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(BookAttraction.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Trip already exist
                    if (object.get("code").equals("200")) {
                        Toast.makeText(BookAttraction.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BookAttraction.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("attractions", attractions);
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

//    private void fillSpinner(){
//
//        ArrayAdapter<String> numberAdapter = new ArrayAdapter<>(BookAttraction.this, android.R.layout.simple_spinner_item, number);
//        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ticketsEdt.setAdapter(numberAdapter);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BookAttraction.this, Attractions.class));
    }
}