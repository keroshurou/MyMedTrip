package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class Krubong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krubong);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Krubong.this, BusRoutes.class));
    }
}