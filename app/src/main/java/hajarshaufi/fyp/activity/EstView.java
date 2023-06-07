package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import hajarshaufi.fyp.R;

public class EstView extends AppCompatActivity {

    String type, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_view);

        type = getIntent().getStringExtra("type");
        city = getIntent().getStringExtra("city");
    }
}