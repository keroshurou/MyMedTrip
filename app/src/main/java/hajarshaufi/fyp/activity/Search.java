package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import hajarshaufi.fyp.R;

public class Search extends AppCompatActivity {

    TextView searchOnMap, nearMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchOnMap = findViewById(R.id.searchOnMap);
        nearMe = findViewById(R.id.nearMe);

        searchOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search.this, CurrentLocation.class);
                startActivity(intent);
            }
        });
    }
}