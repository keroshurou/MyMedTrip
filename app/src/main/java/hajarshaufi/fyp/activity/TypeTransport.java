package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import hajarshaufi.fyp.R;

public class TypeTransport extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerTransportType;
    private String transportType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_transport);

        spinnerTransportType = findViewById(R.id.spinnerTransport);

        //Spinner City
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.transportType, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinnerTransportType.setAdapter(arrayAdapter);
        spinnerTransportType.setOnItemSelectedListener(TypeTransport.this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        transportType = parent.getItemAtPosition(position).toString();
        Toast.makeText(TypeTransport.this, transportType, Toast.LENGTH_SHORT).show();

        if (transportType.equals("Bus")){
            startActivity(new Intent(TypeTransport.this, BusRoutes.class));
        } else if (transportType.equals("Flight")){
            startActivity(new Intent(TypeTransport.this, fetchEstAll.class));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}