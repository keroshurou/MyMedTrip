package hajarshaufi.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

import hajarshaufi.fyp.databinding.ActivityLoginBinding;
import hajarshaufi.fyp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    String url ="http://192.168.56.86/mymedtrip/register.php";

    private String username, password, retypePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.edtUsername.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();
                String retypePass = binding.edtRetypePassword.getText().toString().trim();

                if (TextUtils.isEmpty(username)){
                    binding.edtUsername.setError("Please enter username");
                } else if(TextUtils.isEmpty(password)){
                    binding.edtPassword.setError("Please enter password");
                } else if(TextUtils.isEmpty(password)) {
                    binding.edtPassword.setError("Please retype password");
                }else {

                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        if (object.get("code").equals("201")) {
                                            finish();
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.get("code").equals("202")) {
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                        if (object.get("code").equals("200")) {
                                            Toast.makeText(RegisterActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            params.put("retypePass", retypePass);
                            return params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(request);
                }
            }
        });

    }

    /*public void getAccount(){

        username = binding.edtUsername.getText().toString().trim();
        password = binding.edtPassword.getText().toString().trim();
        retypePass = binding.edtRetypePassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            binding.edtUsername.setError("Please enter username");
        } else if(TextUtils.isEmpty(password)){
            binding.edtPassword.setError("Please enter password");
        } else if(TextUtils.isEmpty(password)) {
            binding.edtPassword.setError("Please retype password");
        }else {
            addDataToDatabase(username, password, retypePass);
        }
    }

    private void addDataToDatabase(String username, String password, String retypePass) {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("TAG", "RESPONSE IS " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // on below line we are displaying a success toast message.
                    Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(RegisterActivity.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("retypePass", retypePass);

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }*/
}