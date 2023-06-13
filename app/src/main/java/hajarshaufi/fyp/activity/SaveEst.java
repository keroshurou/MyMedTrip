package hajarshaufi.fyp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hajarshaufi.fyp.R;

public class SaveEst extends AppCompatActivity {

    TextView dateEdt, timeEdt;
    String status = "upcoming";

    private DatePickerDialog datePicker;
    private TimePickerDialog timePicker;
    int position;
    TextView estType,estName,estAddress,estDays,estHours,estDays2,estHours2,estCity,estTag1,estTag2,estTag3;
    private String type,name,address,days,hours,days2,hours2,city,tag1,tag2,tag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_est);

        //Get All ids
        dateEdt = findViewById(R.id.date);
        timeEdt = findViewById(R.id.time);

        //date picker
        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String date = simpleDateFormat.format(calendar.getTime());

                    //Get day, month, year
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);

                    // date picker dialog
                    datePicker = new DatePickerDialog(SaveEst.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                      int dayOfMonth) {
                                    dateEdt.setText(dayOfMonth + "/" + (monthOfYear + 1)
                                            + "/" + year);
                                }
                            }, year, month, day);
                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                    datePicker.show();
            }
        });

        //time picker
        timeEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePicker = new TimePickerDialog(SaveEst.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0,0,0, hourOfDay, minute);

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
                        timeEdt.setText(sdf.format(calendar.getTime()));

                    }
                },8,0,false);
                timePicker.show();

            }
        });

//        //Date Picker
//        dateEdt.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//
//                    final Calendar calendar = Calendar.getInstance();
//
//                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                    String date = simpleDateFormat.format(calendar.getTime());
//
//                    //Get day, month, year
//                    int day = calendar.get(Calendar.DAY_OF_MONTH);
//                    int month = calendar.get(Calendar.MONTH);
//                    int year = calendar.get(Calendar.YEAR);
//
//                    // date picker dialog
//                    datePicker = new DatePickerDialog(SaveEst.this,
//                            new DatePickerDialog.OnDateSetListener() {
//                                @Override
//                                public void onDateSet(DatePicker view, int year, int monthOfYear,
//                                                      int dayOfMonth) {
//                                    dateEdt.setText(dayOfMonth + "/" + (monthOfYear + 1)
//                                            + "/" + year);
//                                }
//                            }, year, month, day);
//                    datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
//                    datePicker.show();
//                }
//                return false;
//            }
//        });
//
//        //Time Picker
//        timeEdt.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//
//                    timePicker = new TimePickerDialog(SaveEst.this, new TimePickerDialog.OnTimeSetListener() {
//                        @Override
//                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                            Calendar calendar = Calendar.getInstance();
//                            calendar.set(0,0,0, hourOfDay, minute);
//
//                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
//                            timeEdt.setText(sdf.format(calendar.getTime()));
//
//                        }
//                    },6,0,false);
//                    timePicker.show();
//                }
//                return false;
//            }
//        });

        //Get data from previous activity
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        name = intent.getStringExtra("name");
        address = intent.getStringExtra("address");
        days = intent.getStringExtra("days");
        hours = intent.getStringExtra("hours");
        days2 = intent.getStringExtra("days2");
        hours2 = intent.getStringExtra("hours2");
        tag1 = intent.getStringExtra("tag1");
        tag2 = intent.getStringExtra("tag2");
        tag3 = intent.getStringExtra("tag3");

        //find view
        estType = findViewById(R.id.type);
        estName = findViewById(R.id.establishmentName);
        estAddress = findViewById(R.id.address);
        estDays = findViewById(R.id.days);
        estHours = findViewById(R.id.hours);
        estDays2 = findViewById(R.id.days2);
        estHours2 = findViewById(R.id.hours2);
        estTag1 = findViewById(R.id.tag1);
        estTag2 = findViewById(R.id.tag2);
        estTag3 = findViewById(R.id.tag3);

        //Set text
        estType.setText(type);
        estName.setText(name);
        estAddress.setText(address);
        estDays.setText(days);
        estHours.setText(hours);
        estDays2.setText(days2);
        estHours2.setText(hours2);
        estTag1.setText(tag1);
        estTag2.setText(tag2);
        estTag3.setText(tag3);


    }
}