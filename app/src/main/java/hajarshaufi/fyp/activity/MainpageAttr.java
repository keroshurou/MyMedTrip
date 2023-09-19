package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityMainpageAttrBinding;

public class MainpageAttr extends AppCompatActivity {

    private ActivityMainpageAttrBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage_attr);

        binding = ActivityMainpageAttrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //A'Famosa
        binding.rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, Afamosa.class));
            }
        });

        //Baba&Nyonya Heritage Museum
        binding.rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, BabaNyonya.class));
            }
        });

        //Jonker Street
        binding.rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, JonkerStreet.class));
            }
        });

        //Shore Sky Tower
        binding.rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, ShoreSkyTower.class));
            }
        });

        //Melaka River Cruise
        binding.rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, RiverCruise.class));
            }
        });

        //Book AttrBooking
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainpageAttr.this, BookAttraction.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(MainpageAttr.this, Homepage.class));
    }
}