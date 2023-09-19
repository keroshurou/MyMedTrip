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
import hajarshaufi.fyp.java.BusBooking;

public class BusAdapter extends ArrayAdapter<BusBooking> {

    Context context;
    List<BusBooking> busBookingList;
    TextView tvRoute, tvDate, tvTime, tvTickets;

    Button btnChangeStatus;
    private String id;
    String url = "http://10.200.66.178/mymedtrip/updateBus.php";

    public BusAdapter(@NonNull Context context, List<BusBooking> busBookings) {
        super(context, R.layout.busbookings_design ,busBookings);

        this.context = context;
        this.busBookingList = busBookings;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.busbookings_design,
                null, true);

        //Get All ids
        tvRoute = itemView.findViewById(R.id.routeName);
        tvDate = itemView.findViewById(R.id.bbDate);
        tvTime = itemView.findViewById(R.id.bbTime);
        tvTickets = itemView.findViewById(R.id.bbTickets);
        btnChangeStatus = itemView.findViewById(R.id.btnChangeStatus);

        //Set Text
        tvRoute.setText(busBookingList.get(position).getRoute());
        tvDate.setText(busBookingList.get(position).getDate());
        tvTime.setText(busBookingList.get(position).getTime());
        tvTickets.setText(busBookingList.get(position).getTickets());

        //Change Status
        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                id = busBookingList.get(position).getId();
                //Toast.makeText(context, id, Toast.LENGTH_SHORT).show();

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

                titleTextView.setText("Change Status");
                messageTextView.setText("Do you want to change status?");

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogMain.dismiss();
                    }
                });

                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Create an array of options
                        final String[] options = {"Upcoming", "Completed", "Cancelled"};

                        // Create an AlertDialog.Builder
                        AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                        builder.setTitle("Choose an option");

                        // Set the items/options in the dialog
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the option click
                                String selectedOption = options[which];
                                // Perform an action based on the selected option
                                // For example:
                                if ("Upcoming".equals(selectedOption)) {
                                    updateUpcoming();
                                    Intent intent = new Intent(itemView.getContext(), TripPageBusUp.class);
                                    itemView.getContext().startActivity(intent);
                                } else if ("Completed".equals(selectedOption)) {
                                    updateCompleted();
                                    Intent intent = new Intent(itemView.getContext(), TripPageBusCom.class);
                                    itemView.getContext().startActivity(intent);
                                } else if ("Cancelled".equals(selectedOption)) {
                                    updateCancelled();
                                    Intent intent = new Intent(itemView.getContext(), TripPageBusCanc.class);
                                    itemView.getContext().startActivity(intent);
                                }

                                // Dismiss the dialog
                                dialog.dismiss();
                            }
                        });

                        // Create and show the dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialogMain.dismiss();
                    }
                });
            }
        });

        return itemView;
    }

    private void updateUpcoming() {

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
                params.put("status", "upcoming");

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    private void updateCompleted() {

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
                params.put("status", "completed");

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    private void updateCancelled() {

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
                params.put("status", "cancelled");

                // at last we are returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);

    }
}
