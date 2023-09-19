package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityRiverCruiseBinding;

public class RiverCruise extends AppCompatActivity {

    private ActivityRiverCruiseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_river_cruise);

        binding = ActivityRiverCruiseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Book AttrBooking
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RiverCruise.this, BookAttraction.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(RiverCruise.this, MainpageAttr.class));
    }
}