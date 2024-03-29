package hajarshaufi.fyp.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Establishment;

public class EstAdapter extends ArrayAdapter<Establishment> {

    Context context;
    List<Establishment> arrayEstList;

    Button savedBtn; //directBtn;
    ImageView icon;
    TextView estType,estName,estAddress,estDays,estHours,estDays2,estHours2,estCity,estTag1,estTag2,estTag3;

    private String type,name,address,days,hours,days2,hours2,city,tag1,tag2,tag3;

    public EstAdapter(@NonNull Context context, List<Establishment>establishmentList) {
        super(context, R.layout.establishment_design, establishmentList);

        this.context = context;
        this.arrayEstList = establishmentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.establishment_design,
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
        savedBtn = itemView.findViewById(R.id.addBtnRv);
        //directBtn = itemView.findViewById(R.id.btnDirection);


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

        savedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), SaveEst.class);
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
}
