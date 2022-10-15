package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final Activity activity;
    private final List<Trip> trips;

    CustomAdapter(Activity activity, Context context, List<Trip> trips) {
        this.activity = activity;
        this.context = context;
        this.trips = trips;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Trip trip = trips.get(position);
        int id = trip.getId();
        String name = trip.getName();
        String des = trip.getDes();
        String date = trip.getDate();
        String desc = trip.getDesc();

        // set value to form
        holder.trip_id_txt.setText(String.valueOf(id));
        holder.trip_name_txt.setText(name);
        holder.trip_des_txt.setText(des);
        holder.trip_date_txt.setText(date);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passing parameter values
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("selectedTrip", trip);
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView trip_id_txt, trip_des_txt, trip_date_txt, trip_name_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            trip_id_txt = itemView.findViewById(R.id.trip_id_txt);
            trip_name_txt = itemView.findViewById(R.id.trip_name_txt);
            trip_des_txt = itemView.findViewById(R.id.trip_des_txt);
            trip_date_txt = itemView.findViewById(R.id.trip_date_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
