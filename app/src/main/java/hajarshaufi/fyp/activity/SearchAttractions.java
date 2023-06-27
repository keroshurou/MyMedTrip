package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import hajarshaufi.fyp.R;

public class SearchAttractions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView nearMe;
    Spinner spinnerCity;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_attractions);

        //searchOnMap = findViewById(R.id.searchOnMap);
        nearMe = findViewById(R.id.nearMe);
        spinnerCity = findViewById(R.id.spinnerCity);

        //Spinner City
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.city, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerCity.setAdapter(arrayAdapter);
        spinnerCity.setOnItemSelectedListener(SearchAttractions.this);

        nearMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchAttractions.this, NearMe.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city = parent.getItemAtPosition(position).toString();
        Toast.makeText(SearchAttractions.this, city, Toast.LENGTH_SHORT).show();

        if (city.equals("Alor Gajah")){
            startActivity(new Intent(SearchAttractions.this, NearMe.class));
        } else if (city.equals("All")){
            startActivity(new Intent(SearchAttractions.this, fetchEstAll.class));
        } else if (city.equals("Krubong")) {
            startActivity(new Intent(SearchAttractions.this, fetchEstKrubong.class));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(SearchAttractions.this, Homepage.class));
    }
}