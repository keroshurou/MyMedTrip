package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Admin;

public class AdminManageAccount extends AppCompatActivity {

    Button addAccount;
    EditText searchEdt;

    public static ArrayList<Admin> adminArrayList = new ArrayList<>();
    ListView listView;
    AdminAccountAdapter adminAccountAdapter;
    Admin admin;
    String url = "http://192.168.213.86/mymedtrip/fetchEst.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_account);

        //Get all ids
        addAccount = findViewById(R.id.addAccount);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottomManageAccount);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottomDashboard:
                    startActivity(new Intent(getApplicationContext(), AdminHomepage.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottomManageAccount:
                    return true;
                case R.id.bottomProfile:
                    startActivity(new Intent(getApplicationContext(), AdminProfile.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminManageAccount.this, AddAccount.class);
                startActivity(intent);
            }
        });
    }


}