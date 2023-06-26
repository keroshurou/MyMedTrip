package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityBusRoutesBinding;
import hajarshaufi.fyp.databinding.ActivityHelpModuleBinding;

public class BusRoutes extends AppCompatActivity {

    private ActivityBusRoutesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_routes);

        binding = ActivityBusRoutesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Batang Melaka
        binding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusRoutes.this, BatangMelaka.class));
            }
        });

        //Batu Berendam
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusRoutes.this, BatuBerendam.class));
            }
        });

        //Krubong
        binding.layout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusRoutes.this, Krubong.class));
            }
        });

        //MITC 1
        binding.layout9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusRoutes.this, Mitc1.class));
            }
        });

        binding.bookBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusRoutes.this, BookBus.class));
            }
        });
    }
}