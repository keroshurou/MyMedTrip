package hajarshaufi.fyp.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import hajarshaufi.fyp.R;
import hajarshaufi.fyp.java.AttrBooking;
import hajarshaufi.fyp.java.BusBooking;

public class AttractionsAdapter extends ArrayAdapter<AttrBooking> {

    Context context;
    List<AttrBooking> attrBookingList;
    TextView tvAttr, tvDate, tvTime, tvTickets;

    public AttractionsAdapter(@NonNull Context context, List<AttrBooking> attrBookings) {
        super(context, R.layout.attractions_design, attrBookings);

        this.context = context;
        this.attrBookingList = attrBookings;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attractions_design,
                null, true);

        tvAttr = itemView.findViewById(R.id.attrName);
        tvDate = itemView.findViewById(R.id.attrDate);
        tvTime = itemView.findViewById(R.id.attrTime);
        tvTickets = itemView.findViewById(R.id.attrTickets);

        tvAttr.setText(attrBookingList.get(position).getAttractions());
        tvDate.setText(attrBookingList.get(position).getDate());
        tvTime.setText(attrBookingList.get(position).getTime());
        tvTickets.setText(attrBookingList.get(position).getTickets());

        return itemView;
    }
}
