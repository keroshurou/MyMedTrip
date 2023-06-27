package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hajarshaufi.fyp.R;

public class AddAccount extends AppCompatActivity {

    EditText usernameAccEdt, passwordAccEdt, passwordAccReEdt, staffNameEdt, staffNoEdt;
    Button addAcc;
    ImageView backBtn;
    String url = "http://192.168.234.86/mymedtrip/addAcc.php";

    private String username, password, retypePass, staffName, staffNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        usernameAccEdt = findViewById(R.id.usernameAcc);
        passwordAccEdt = findViewById(R.id.passwordAcc);
        passwordAccReEdt = findViewById(R.id.passwordAccRe);
        staffNameEdt = findViewById(R.id.staffName);
        staffNoEdt = findViewById(R.id.staffNo);
        addAcc = findViewById(R.id.addBtnAcc);


        addAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAcc();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long longTime2 = System.currentTimeMillis();
                        startActivity(new Intent(getApplicationContext(), AdminManageAccount.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });
    }

    private void getAcc() {

        username = usernameAccEdt.getText().toString();
        password = passwordAccEdt.getText().toString();
        retypePass = passwordAccReEdt.getText().toString();
        staffName = staffNameEdt.getText().toString();
        staffNo = staffNoEdt.getText().toString();

        if (TextUtils.isEmpty(username)){
            usernameAccEdt.setError("Please enter username");
        } else if(TextUtils.isEmpty(password)){
            passwordAccEdt.setError("Please enter password");
        } else if(TextUtils.isEmpty(retypePass)) {
            passwordAccReEdt.setError("Please enter password");
        } else if(TextUtils.isEmpty(staffName)) {
            staffNameEdt.setError("Please enter staff name");
        }else if(TextUtils.isEmpty(staffNo)) {
            staffNoEdt.setError("Please enter staff no");
        } else {
            if (password.equals(retypePass)){
                addToDatabase(username, password, staffNo, staffName);
            }else {
                passwordAccEdt.setError("Password is not same");
                passwordAccReEdt.setError("Password is not same");
            }
        }

    }

    private void addToDatabase(String username, String password, String staffNo, String staffName) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(AddAccount.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    //Add account successful
                    if (object.get("code").equals("201")) {
                        Toast.makeText(AddAccount.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(AddAccount.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Staff already admin
                    if (object.get("code").equals("200")) {
                        Toast.makeText(AddAccount.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //username already exist
                    if (object.get("code").equals("400")) {
                        Toast.makeText(AddAccount.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                usernameAccEdt.setText("");
                passwordAccEdt.setText("");
                passwordAccReEdt.setText("");
                staffNameEdt.setText("");
                staffNoEdt.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(AddAccount.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                // as we are passing data in the form of url encoded
                // so we are passing the content type below
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
            @Override
            protected Map<String, String> getParams() {

                // below line we are creating a map for storing
                // our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our
                // key and value pair to our parameters.
                params.put("username", username);
                params.put("password", password);
                params.put("name", staffName);
                params.put("staffNo", staffNo);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(AddAccount.this, AdminManageAccount.class));
    }
}