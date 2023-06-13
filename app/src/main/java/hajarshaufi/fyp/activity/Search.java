package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.EstName;
import hajarshaufi.fyp.java.Establishment;

public class Search extends AppCompatActivity {

    AutoCompleteTextView searchAuto;
    TextView searchOnMap, nearMe;
    EditText searchBar;
    public static ArrayList<EstName> estNameArrayList = new ArrayList<>();
    EstName estName;
    String url = "http://192.168.10.86/mymedtrip/fetchEstName.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchOnMap = findViewById(R.id.searchOnMap);
        nearMe = findViewById(R.id.nearMe);
        searchBar = findViewById(R.id.search_bar);

        getData();

//        //ArrayList to array
//        String[] estNames = (new String[estNameArrayList.size()]);
//        estNames = estNameArrayList.toArray(estNames);
//
//        //autocomplete textview
//        searchAuto = findViewById(R.id.searchAuto);
//        String[] names = getResources().getStringArray(R.array.estName);
//        String[] estNames2 = estNames;
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, estNames2);
//        searchAuto.setAdapter(adapter);

        nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, NearMe.class);
                startActivity(intent);
            }
        });

        String searchData = searchBar.getText().toString();

        searchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Intent intent = new Intent(Search.this, SearchResults.class);
                    intent.putExtra("searchData", searchData);
                    startActivity(intent);
                    Toast.makeText(Search.this, searchBar.getText(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void getData() {

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        estNameArrayList.clear();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("item");

                            if(success.equals("1")){

                                for(int i=0; i<jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String name = object.getString("name");

                                    estName = new EstName(name);
                                    estNameArrayList.add(estName);
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
                Toast.makeText(Search.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}