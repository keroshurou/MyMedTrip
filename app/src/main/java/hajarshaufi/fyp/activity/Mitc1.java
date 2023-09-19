package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityMitc1Binding;

public class Mitc1 extends AppCompatActivity {

    private ActivityMitc1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitc1);

        binding = ActivityMitc1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bookBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Mitc1.this, BookBus.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Mitc1.this, MainpageBus.class));
    }
}