package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import hajarshaufi.fyp.R;

public class AddEstablishment extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner typeSpinner;
    EditText establishmentNameEdt, addressEdt, daysEdt, hoursEdt, days2Edt, hours2Edt, cityEdt, tag1Edt, tag2Edt, tag3Edt;
    Button addBtn;

    private String type, establishmentName, address, days, hours, days2, hours2, city, tag1, tag2, tag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_establishment);

        //get all ids
        typeSpinner = findViewById(R.id.spinnerType);
        establishmentNameEdt = findViewById(R.id.establishmentName);
        addressEdt = findViewById(R.id.address);
        daysEdt = findViewById(R.id.days);
        hoursEdt = findViewById(R.id.hours);
        days2Edt = findViewById(R.id.days2);
        hours2Edt = findViewById(R.id.hours2);
        cityEdt = findViewById(R.id.city);
        tag1Edt = findViewById(R.id.tag1);
        tag2Edt = findViewById(R.id.tag2);
        tag3Edt = findViewById(R.id.tag3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddEstablishment.this, R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setOnItemSelectedListener(AddEstablishment.this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEst();
            }
        });

    }

    private void getEst(){

        type = typeSpinner.getSelectedItem().toString().trim();
        establishmentName = establishmentNameEdt.getText().toString();
        address = addressEdt.getText().toString();
        days = daysEdt.getText().toString();
        hours = hoursEdt.getText().toString();
        days2 = days2Edt.getText().toString();
        hours2 = hours2Edt.getText().toString();
        city = cityEdt.getText().toString();
        tag1 = tag1Edt.getText().toString();
        tag2 = tag2Edt.getText().toString();
        tag3 = tag3Edt.getText().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //expressBrand = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}