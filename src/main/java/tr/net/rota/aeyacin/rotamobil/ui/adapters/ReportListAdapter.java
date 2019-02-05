package tr.net.rota.aeyacin.rotamobil.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import tr.net.rota.aeyacin.rotamobil.R;
import tr.net.rota.aeyacin.rotamobil.model.sbt.ReportData;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {

    private List<ReportData> rArrayList;
    private Activity rActivity;
    private int vehicleId;



    public ReportListAdapter(Activity activity,List<ReportData> arrayList,int vehicleId){

        this.rArrayList = new ArrayList<>();
        this.rArrayList.addAll(arrayList);



        this.rActivity = activity;
        this.vehicleId = vehicleId;
    }


    @Override
    public ReportListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.report_road_item,viewGroup,false);
        return new ReportListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        final ReportData item = rArrayList.get(i);

        viewHolder.totalKm.setText(String.valueOf(item.DayTotalKm + " Km"));
        viewHolder.city.setText(String.valueOf(item.CityInside + " "));
        viewHolder.district.setText(String.valueOf(item.DistrictInside));
        viewHolder.highSpeed.setText(String.valueOf(item.HighestSpeed + " Km/s"));
        viewHolder.atworkTime.setText(String.valueOf(item.AtWorkTimeKm + " Km"));
        viewHolder.outWorkTime.setText(String.valueOf(item.OutOfWorkKm + " Km"));
        String title="";

        try {
            Date readedDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(item.DateTimeToday);
            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
            title = outFormat.format(readedDate) + " (" + String.valueOf(new SimpleDateFormat("dd.MM.yyyy").format(readedDate)) + ")";
            if (new SimpleDateFormat("dd.MM.yyyy").format(readedDate).toString().equalsIgnoreCase(new SimpleDateFormat("dd.MM.yyyy").format(new Date()))) {
                title = rActivity.getString(R.string.monthly_report_today) + " (" + String.valueOf(new SimpleDateFormat("dd.MM.yyyy").format(readedDate)) + ")";

            }
        } catch (ParseException e) {
            e.printStackTrace();
            title = item.DateTimeToday;
        }

        viewHolder.rDate.setText(String.valueOf(title));
    }

    @Override
    public int getItemCount() {
        return rArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rDate,totalKm,highSpeed,city,district,atworkTime,outWorkTime;

        public ViewHolder(View view) {
            super(view);
            rDate = view.findViewById(R.id.report_date);
            totalKm = view.findViewById(R.id.report_distance);
            highSpeed = view.findViewById(R.id.report_max_speed);
            city = view.findViewById(R.id.report_city);
            district = view.findViewById(R.id.report_district);
            atworkTime = view.findViewById(R.id.report_atWorkTime);
            outWorkTime = view.findViewById(R.id.report_outOfWorkTime);

        }
    }
}