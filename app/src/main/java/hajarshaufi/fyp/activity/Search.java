package hajarshaufi.fyp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Search extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView nearMe;
    Spinner spinnerCity;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //searchOnMap = findViewById(R.id.searchOnMap);
        nearMe = findViewById(R.id.nearMe);
        spinnerCity = findViewById(R.id.spinnerCity);

//        //ArrayList to array
//        String[] estNames = (new String[estNameArrayList.size()]);
//        estNames = estNameArrayList.toArray(estNames);

        //Spinner City
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(arrayAdapter);
        spinnerCity.setOnItemSelectedListener(Search.this);

        //city = spinnerCity.getSelectedItem().toString().trim();

        nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, NearMe.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city = parent.getItemAtPosition(position).toString();
        Toast.makeText(Search.this, city, Toast.LENGTH_SHORT).show();

        if (city.equals("Alor Gajah")){
            startActivity(new Intent(Search.this, NearMe.class));
        } else if (city.equals("All")){
            startActivity(new Intent(Search.this, fetchEstAll.class));
        } else if (city.equals("Krubong")) {
            startActivity(new Intent(Search.this, fetchEstKrubong.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Search.this, Homepage.class));
    }
}