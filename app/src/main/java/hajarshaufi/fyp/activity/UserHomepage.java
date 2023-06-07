package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.databinding.ActivityUserHomepageBinding;

public class UserHomepage extends AppCompatActivity {

    private ActivityUserHomepageBinding binding;
    EditText edtCity, edtType;
    Button selectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);

        binding.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}