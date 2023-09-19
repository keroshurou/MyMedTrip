package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityAfamosaBinding;

public class Afamosa extends AppCompatActivity {

    private ActivityAfamosaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afamosa);

        binding = ActivityAfamosaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Book AttrBooking
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Afamosa.this, BookAttraction.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Afamosa.this, MainpageAttr.class));
    }
}