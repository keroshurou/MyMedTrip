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
import android.widget.TextView;
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

import hajarshaufi.fyp.R;

public class EditPersonalDetails extends AppCompatActivity {

    EditText edtName, edtEmail, tvUsername, tvPassword;
    Button editBtn;
    private static String username, password, name, email;

    String url = "http://10.200.66.178/mymedtrip/editPersonalDetails.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_details);

        //get all ids
        edtName = findViewById(R.id.name);
        edtEmail = findViewById(R.id.email);
        tvUsername = findViewById(R.id.username);
        tvUsername.setEnabled(false);
        tvPassword = findViewById(R.id.password);
        tvPassword.setEnabled(false);
        editBtn = findViewById(R.id.editBtn);

        edtName.setText(PersonalDetails.getName());
        edtEmail.setText(PersonalDetails.getEmail());
        tvUsername.setText(PersonalDetails.getUsername());
        tvPassword.setText(PersonalDetails.getPassword());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDetails();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        long longTime2 = System.currentTimeMillis();
                        startActivity(new Intent(getApplicationContext(), PersonalDetails.class));
                    }
                };
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(runnable, 3000); //delayed 3 seconds
            }
        });
    }

    private void getDetails() {

        username = PersonalDetails.getUsername();
        password = PersonalDetails.getPassword();
        name = edtName.getText().toString();
        email = edtEmail.getText().toString();

        if (TextUtils.isEmpty(name)) {
            edtName.setError("Please enter name");
        } else if (TextUtils.isEmpty(email)) {
            edtEmail.setError("Please enter email");
        } else {
            updateDatabase(name, email);
        }
    }

    private void updateDatabase(String name, String email) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(EditPersonalDetails.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    //Update successful
                    if (object.get("code").equals("201")) {
                        Toast.makeText(EditPersonalDetails.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(EditPersonalDetails.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // and setting data to edit text as empty
                tvUsername.setText("");
                tvPassword.setText("");
                edtName.setText("");
                edtEmail.setText("");
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(EditPersonalDetails.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("name", name);
                params.put("email", email);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}