package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Establishment;

public class AdminHomepage extends AppCompatActivity {

    public static ArrayList<Establishment> estArrayList = new ArrayList<>();
    Button addNewButton;
    EditText searchBarEdt;
    ListView listView;
    EstAdapter estAdapter;
    Establishment establishment;
    String url = "http://192.168.124.86/mymedtrip/fetchEst.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        getData();

        //Get All IDs
        addNewButton = findViewById(R.id.addNewBtn);
        searchBarEdt = findViewById(R.id.edtSearch);

        //list view
        listView = findViewById(R.id.estListView);
        estAdapter = new EstAdapter(this, estArrayList);
        listView.setAdapter(estAdapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomDashboard);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomDashboard:
                    return true;
                case R.id.bottomManageAccount:
                    startActivity(new Intent(getApplicationContext(), AdminManageAccount.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottomProfile:
                    startActivity(new Intent(getApplicationContext(), AdminProfile.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        addNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepage.this, AddEstablishment.class);
                startActivity(intent);
            }
        });

        String data = searchBarEdt.getText().toString();

    }

    private void getData() {

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
                Toast.makeText(AdminHomepage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


}