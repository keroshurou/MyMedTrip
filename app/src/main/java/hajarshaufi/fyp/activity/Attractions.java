package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityAttractionsBinding;
import hajarshaufi.fyp.databinding.ActivityBusRoutesBinding;

public class Attractions extends AppCompatActivity {

    private ActivityAttractionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        binding = ActivityAttractionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //A'Famosa
        binding.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, Afamosa.class));
            }
        });

        //Baba&Nyonya Heritage Museum
        binding.layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, BabaNyonya.class));
            }
        });

        //Jonker Street
        binding.layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, JonkerStreet.class));
            }
        });

        //Shore Sky Tower
        binding.layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, ShoreSkyTower.class));
            }
        });

        //Melaka River Cruise
        binding.layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, RiverCruise.class));
            }
        });

        //Book Attractions
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Attractions.this, BookAttraction.class));
            }
        });
    }
}