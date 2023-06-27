package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class JonkerStreet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jonker_street);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(JonkerStreet.this, Attractions.class));
    }
}