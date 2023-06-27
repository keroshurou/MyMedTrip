package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class ShoreSkyTower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shore_sky_tower);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(ShoreSkyTower.this, Attractions.class));
    }
}