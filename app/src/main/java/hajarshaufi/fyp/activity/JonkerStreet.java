package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityJonkerStreetBinding;

public class JonkerStreet extends AppCompatActivity {

    private ActivityJonkerStreetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jonker_street);

        binding = ActivityJonkerStreetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Book AttrBooking
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JonkerStreet.this, BookAttraction.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(JonkerStreet.this, MainpageAttr.class));
    }
}