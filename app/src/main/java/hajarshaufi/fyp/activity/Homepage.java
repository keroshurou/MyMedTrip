package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Establishment;

public class Homepage extends AppCompatActivity {

    Button selectBtn;
    EditText edtCity,edtType;
    String city, type;

    private RecyclerView recyclerView;
    ArrayList<Establishment> establishmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        selectBtn = findViewById(R.id.btnSelect);
        edtCity = findViewById(R.id.edtCity);
        edtType = findViewById(R.id.edtType);

        city = edtCity.getText().toString();
        type = edtType.getText().toString();

        recyclerView = findViewById(R.id.recyclerVw);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        establishmentArrayList = new ArrayList<Establishment>();
        JSONFetch jsonFetch = new JSONFetch();
        jsonFetch.execute();

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homepage.this, EstView.class);
                intent.putExtra("city", city);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });

    }

    public class JSONFetch extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}