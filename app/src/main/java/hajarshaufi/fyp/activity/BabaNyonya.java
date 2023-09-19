package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityBabaNyonyaBinding;

public class BabaNyonya extends AppCompatActivity {

    private ActivityBabaNyonyaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baba_nyonya);

        binding = ActivityBabaNyonyaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Book AttrBooking
        binding.bookAttr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BabaNyonya.this, BookAttraction.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(BabaNyonya.this, MainpageAttr.class));
    }
}