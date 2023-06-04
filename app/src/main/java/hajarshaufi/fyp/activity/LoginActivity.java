package hajarshaufi.fyp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import hajarshaufi.fyp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    String url = "http://192.168.56.86/mymedtrip/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = binding.edtUsername.getText().toString().trim();
                String password = binding.edtPassword.getText().toString().trim();
                String generatedPassword = "null";

                if (TextUtils.isEmpty(username)){
                    binding.edtUsername.setError("Please enter username");
                } else if(TextUtils.isEmpty(password)){
                    binding.edtPassword.setError("Please enter password");
                }else {

                    /*//Encryption Hash
                    try
                    {
                        // Create MessageDigest instance for MD5
                        MessageDigest md = MessageDigest.getInstance("MD5");

                        // Add password bytes to digest
                        md.update(password.getBytes());

                        // Get the hash's bytes
                        byte[] bytes = md.digest();

                        // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < bytes.length; i++) {
                            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                        }

                        // Get complete hashed password in hex format
                        generatedPassword = sb.toString();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    String encrypted = generatedPassword;*/

                    //Volley
                    StringRequest request = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject object = new JSONObject(response);
                                        if (object.get("code").equals("202")) {
                                            Toast.makeText(LoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                            binding.edtUsername.setText("");
                                            binding.edtPassword.setText("");
                                        }
                                        //Login User
                                        else if (object.get("code").equals("200")) {
                                            Toast.makeText(LoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), UserHomepage.class));
                                        }
                                        //Login Admin
                                        else if (object.get("code").equals("201")) {
                                            Toast.makeText(LoginActivity.this, object.getString("msg"), Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), AdminHomepage.class));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }
                    };

                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(request);
                }
            }
        });

        binding.txtVwSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}