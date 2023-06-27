package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class Afamosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afamosa);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Afamosa.this, Attractions.class));
    }
}