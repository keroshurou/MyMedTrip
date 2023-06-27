package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class BabaNyonya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baba_nyonya);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BabaNyonya.this, Attractions.class));
    }
}