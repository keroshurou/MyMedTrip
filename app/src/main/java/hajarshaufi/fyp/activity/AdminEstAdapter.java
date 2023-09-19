package hajarshaufi.fyp.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Establishment;

public class AdminEstAdapter extends ArrayAdapter<Establishment> {

    Context context;
    List<Establishment> arrayEstList;

    Button deleteBtn, editBtn;
    ImageView icon;
    TextView estType,estName,estAddress,estDays,estHours,estDays2,estHours2,estCity,estTag1,estTag2,estTag3;

    private String id, nameEst;
    String url = "http://10.200.66.178/mymedtrip/deleteEst.php";

    private String type,name,address,days,hours,days2,hours2,city,tag1,tag2,tag3;

    public AdminEstAdapter(@NonNull Context context, List<Establishment>establishmentList) {
        super(context, R.layout.establishment_design, establishmentList);

        this.context = context;
        this.arrayEstList = establishmentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_est_design,
                null, true);

        estType = itemView.findViewById(R.id.type);
        estName = itemView.findViewById(R.id.establishmentName);
        estAddress = itemView.findViewById(R.id.address);
        estDays = itemView.findViewById(R.id.days);
        estHours = itemView.findViewById(R.id.hours);
        estDays2 = itemView.findViewById(R.id.days2);
        estHours2 = itemView.findViewById(R.id.hours2);
        estTag1 = itemView.findViewById(R.id.tag1);
        estTag2 = itemView.findViewById(R.id.tag2);
        estTag3 = itemView.findViewById(R.id.tag3);
        deleteBtn = itemView.findViewById(R.id.deleteBtn);
        editBtn = itemView.findViewById(R.id.editBtn);

        estType.setText(arrayEstList.get(position).getType());
        estName.setText(arrayEstList.get(position).getEstablishmentName());
        estAddress.setText(arrayEstList.get(position).getAddress());
        estDays.setText(arrayEstList.get(position).getDays());
        estHours.setText(arrayEstList.get(position).getHours());
        estDays2.setText(arrayEstList.get(position).getDays2());
        estHours2.setText(arrayEstList.get(position).getHours2());
        estTag1.setText(arrayEstList.get(position).getCity());
        estTag2.setText(arrayEstList.get(position).getTag1());
        estTag3.setText(arrayEstList.get(position).getTag2());

        type = arrayEstList.get(position).getType();
        name = arrayEstList.get(position).getEstablishmentName();
        address = arrayEstList.get(position).getAddress();
        days = arrayEstList.get(position).getDays();
        hours = arrayEstList.get(position).getHours();
        days2 = arrayEstList.get(position).getDays2();
        hours2 = arrayEstList.get(position).getHours2();
        tag1 = arrayEstList.get(position).getCity();
        tag2 = arrayEstList.get(position).getTag1();
        tag3 = arrayEstList.get(position).getTag2();

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = arrayEstList.get(position).getId();
                nameEst = arrayEstList.get(position).getEstablishmentName();
                //Toast.makeText(context, id + " " + nameEst , Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                View dialogView = LayoutInflater.from(itemView.getContext()).inflate(R.layout.change_status_dialog, null);
                builder.setView(dialogView);

                // Create and show the dialog
                AlertDialog dialogMain = builder.create();
                dialogMain.show();

                //Get All ids
                TextView titleTextView = dialogView.findViewById(R.id.alertTitle);
                TextView messageTextView = dialogView.findViewById(R.id.alertMessage);
                TextView yesBtn = dialogView.findViewById(R.id.yesBtn);
                TextView cancelBtn = dialogView.findViewById(R.id.cancelBtn);

                titleTextView.setText("Delete Establishment");
                messageTextView.setText("Are you sure you want to delete " + nameEst + "?");

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMain.dismiss();
                    }
                });

                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteEst();
                        Intent intent = new Intent(itemView.getContext(), AdminEstablishment.class);
                        itemView.getContext().startActivity(intent);
                        dialogMain.dismiss();
                    }
                });
            }

        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = arrayEstList.get(position).getId();
                nameEst = arrayEstList.get(position).getEstablishmentName();
                //Toast.makeText(context, id + " " + nameEst , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(itemView.getContext(), editEstAdmin.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                intent.putExtra("name", name);
                intent.putExtra("address", address);
                intent.putExtra("days", days);
                intent.putExtra("hours", hours);
                intent.putExtra("days2", days2);
                intent.putExtra("hours2", hours2);
                intent.putExtra("tag1", tag1);
                intent.putExtra("tag2", tag2);
                intent.putExtra("tag3", tag3);
                itemView.getContext().startActivity(intent);
            }
        });

        return itemView;
    }

    private void deleteEst() {

        // url to post our data
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);

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
                        Toast.makeText(context, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                    //Something went wrong
                    if (object.get("code").equals("202")) {
                        Toast.makeText(context, object.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
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
                params.put("id", id );

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }
}
