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
import hajarshaufi.fyp.java.Admin;

public class AdminAccountAdapter extends ArrayAdapter<Admin> {

    Context context;
    List<Admin> adminArrayList;

    TextView adminName, adminStaffNo;

    private String name, staffNo;

    public AdminAccountAdapter(@NonNull Context context, List<Admin> adminList) {
        super(context, R.layout.admin_account_design);

        this.context = context;
        this.adminArrayList = adminList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_account_design,
                null, true);

        adminName = itemView.findViewById(R.id.staffName);
        adminStaffNo = itemView.findViewById(R.id.staffNo);

        adminName.setText(adminArrayList.get(position).getName());
        adminStaffNo.setText(adminArrayList.get(position).getStaffNo());

        name = adminArrayList.get(position).getName();
        staffNo = adminArrayList.get(position).getStaffNo();

        return itemView;
    }
}
