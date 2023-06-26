package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import hajarshaufi.fyp.R;

public class SearchAttractions extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView nearMe;
    Spinner spinnerCity;

    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_attractions);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}