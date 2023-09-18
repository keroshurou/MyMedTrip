package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Trip;

public class TripPageCompleted extends AppCompatActivity {

    public static ArrayList<Trip> tripCompletedList = new ArrayList<>();
    Trip trip;
    TripUpAdapter tripUpAdapter;
    ListView listView;
    TextView upcoming, completed, cancelled;

    String url = "http://10.200.66.178/mymedtrip/fetchTripCompleted.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_page_completed);

        //Get All ids
        upcoming = findViewById(R.id.upcoming);
        completed = findViewById(R.id.completed);
        cancelled = findViewById(R.id.cancelled);

        //list view
        listView = findViewById(R.id.savedListView);
        tripUpAdapter = new TripUpAdapter(this, tripCompletedList);
        listView.setAdapter(tripUpAdapter);

        //upcoming button
        upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TripPageUpcoming.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
            }
        });

        //cancelled button
        cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TripPageCancelled.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
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
                    return true;
                case R.id.userHelp:
                    startActivity(new Intent(getApplicationContext(), HelpModule.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.userProfile:
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        getDataCompleted();
    }

    private void getDataCompleted() {

        RequestQueue queue = Volley.newRequestQueue(TripPageCompleted.this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        tripCompletedList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            if(success.equals("1")){

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
                                    String tripdate = object.getString("tripdate");
                                    String triptime = object.getString("triptime");
                                    String status = object.getString("status");
                                    String type = object.getString("type");
                                    String name = object.getString("name");
                                    String address = object.getString("address");
                                    String days = object.getString("days");
                                    String hours = object.getString("hours");
                                    String days2 = object.getString("days2");
                                    String hours2 = object.getString("hours2");
                                    String city = object.getString("city");
                                    String tag1 = object.getString("tag1");
                                    String tag2 = object.getString("tag2");

                                    trip = new Trip(id,tripdate,triptime,status,type,name,address,days,hours,days2,hours2,city,tag1,tag2);
                                    tripCompletedList.add(trip);
                                    tripUpAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TripPageCompleted.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}