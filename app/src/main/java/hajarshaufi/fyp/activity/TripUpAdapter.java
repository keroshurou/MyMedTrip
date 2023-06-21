package hajarshaufi.fyp.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.Trip;

public class TripUpAdapter extends ArrayAdapter<Trip> {

    Context context;
    List<Trip> arrayTripList;

    Button btnDirect;
    TextView tvType, tvName, tvTripDate, tvTripTime;

    public TripUpAdapter(@NonNull Context context, List<Trip> tripList) {
        super(context, R.layout.trip_design ,tripList);

        this.context = context;
        this.arrayTripList = tripList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_design,
                null, true);

        tvType = itemView.findViewById(R.id.type);
        tvName = itemView.findViewById(R.id.establishmentName);
        tvTripDate = itemView.findViewById(R.id.tripDate);
        tvTripTime = itemView.findViewById(R.id.tripTime);
        btnDirect = itemView.findViewById(R.id.btnDirection);

        tvType.setText(arrayTripList.get(position).getType());
        tvName.setText(arrayTripList.get(position).getEstablishmentName());
        tvTripDate.setText(arrayTripList.get(position).getTripdate());
        tvTripTime.setText(arrayTripList.get(position).getTriptime());

        return itemView;
    }
}
