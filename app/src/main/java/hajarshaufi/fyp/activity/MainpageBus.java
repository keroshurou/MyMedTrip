package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityBusRoutesBinding;
import hajarshaufi.fyp.databinding.ActivityMainpageBusBinding;

public class MainpageBus extends AppCompatActivity {

    private ActivityMainpageBusBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_bus);

        binding = ActivityMainpageBusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Batang Melaka
        binding.rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageBus.this, BatangMelaka.class));
            }
        });

        //Batu Berendam
        binding.rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageBus.this, BatuBerendam.class));
            }
        });

        //Krubong
        binding.rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageBus.this, Krubong.class));
            }
        });

        //MITC 1
        binding.rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageBus.this, Mitc1.class));
            }
        });

        binding.bookBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageBus.this, BookBus.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(MainpageBus.this, Homepage.class));
    }
}