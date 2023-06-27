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
import hajarshaufi.fyp.java.BusBooking;

public class BusBookAdapter extends ArrayAdapter<BusBooking> {

    Context context;
    List<BusBooking> busBookingList;
    TextView tvRoute, tvDate, tvTime, tvTickets;

    public BusBookAdapter(@NonNull Context context, List<BusBooking> busBookings) {
        super(context, R.layout.busbookings_design ,busBookings);

        this.context = context;
        this.busBookingList = busBookings;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.busbookings_design,
                null, true);

        tvRoute = itemView.findViewById(R.id.routeName);
        tvDate = itemView.findViewById(R.id.bbDate);
        tvTime = itemView.findViewById(R.id.bbTime);
        tvTickets = itemView.findViewById(R.id.bbTickets);

        tvRoute.setText(busBookingList.get(position).getRoute());
        tvDate.setText(busBookingList.get(position).getDate());
        tvTime.setText(busBookingList.get(position).getTime());
        tvTickets.setText(busBookingList.get(position).getTickets());

        return itemView;
    }
}
