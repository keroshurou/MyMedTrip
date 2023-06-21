package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hajarshaufi.fyp.R;

public class Question2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent back = new Intent(Question2.this, HelpModule.class);
        startActivity(back);
    }
}