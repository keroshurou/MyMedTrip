package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class BatangMelaka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batang_melaka);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BatangMelaka.this, BusRoutes.class));
    }
}