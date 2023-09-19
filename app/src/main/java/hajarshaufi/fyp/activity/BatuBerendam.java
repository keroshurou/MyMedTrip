package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityBatuBerendamBinding;

public class BatuBerendam extends AppCompatActivity {

    private ActivityBatuBerendamBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batu_berendam);

        binding = ActivityBatuBerendamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bookBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BatuBerendam.this, BookBus.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BatuBerendam.this, MainpageBus.class));
    }
}