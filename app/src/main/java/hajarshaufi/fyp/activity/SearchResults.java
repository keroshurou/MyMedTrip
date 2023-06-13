package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Establishment;

public class SearchResults extends AppCompatActivity {

    public static ArrayList<Establishment> estArrayList = new ArrayList<>();
    ImageView backBtn;
    ListView listView;
    EstAdapter estAdapter;
    Establishment establishment;
    String data;
    String url = "http://192.168.10.86/mymedtrip/fetchEst.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        //Get All IDs
        backBtn = findViewById(R.id.backBtn);

        //list view
        listView = findViewById(R.id.searchResultsListView);
        estAdapter = new EstAdapter(this, estArrayList);
        listView.setAdapter(estAdapter);

        data = getIntent().getStringExtra("searchData");

        getData();
    }

    private void getData() {

        RequestQueue queue = Volley.newRequestQueue(SearchResults.this);

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        estArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            if(success.equals("1")){

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id");
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

                                    establishment = new Establishment(id,type,name,address,days,hours,days2,hours2,city,tag1,tag2);
                                    estArrayList.add(establishment);
                                    estAdapter.notifyDataSetChanged();
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
                Toast.makeText(SearchResults.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
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
                params.put("data", data);
                params.put("data2", data);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}