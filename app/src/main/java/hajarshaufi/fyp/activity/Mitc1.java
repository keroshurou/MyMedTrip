package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class Mitc1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitc1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Mitc1.this, BusRoutes.class));
    }
}