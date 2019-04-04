package com.vn8031.smartbus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.BusInfo>
{
    Context context;
    int busImages[];
    String busNumbers[];
    String s_dPlace[];
    String sTime[];
    String dTime[];
    String locations[];
    String driverNames[];
    String driverContacts[];

    public BusAdapter(Context context, int[] busImages, String[] busNumbers, String[] s_dPlace, String[] sTime, String[] dTime, String[] locations, String[] driverNames, String[] driverContacts)
    {
        this.context = context;
        this.busImages = busImages;
        this.busNumbers = busNumbers;
        this.s_dPlace = s_dPlace;
        this.sTime = sTime;
        this.dTime = dTime;
        this.locations = locations;
        this.driverNames = driverNames;
        this.driverContacts = driverContacts;
    }

    @NonNull
    @Override
    public BusInfo onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
        return new BusInfo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusInfo busInfo, final int i) {
        busInfo.mImageView.setImageResource(busImages[i]);
        busInfo.bus_no_tv.setText(busNumbers[i]);
        busInfo.bus_stops_tv.setText(s_dPlace[i]);
        busInfo.sourceTime_tv.setText(sTime[i]);

        busInfo.bus_stops_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("stopKey",s_dPlace[i]);
                intent.putExtra("driverNamesKey",driverNames[i]);
                intent.putExtra("driverContactsKey",driverContacts[i]);
                intent.putExtra("busNumberKey",busNumbers[i]);
                context.startActivity(intent);
            }
        });

        busInfo.navigate_map.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                String location = locations[i].toLowerCase().trim();
                Uri data = Uri.parse("geo:0,0?q=" + location);
                Intent i = new Intent(Intent.ACTION_VIEW,data);
                if (i.resolveActivity(context.getPackageManager()) != null)
                {
                    context.startActivity(i);
                }
                else {
                    Toast.makeText(context, "Can't handle this Intent", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return busImages.length;
    }

    public class BusInfo extends RecyclerView.ViewHolder
    {
        CircleImageView mImageView;
        ImageView navigate_map;
        TextView bus_no_tv,bus_stops_tv,sourceTime_tv, destination_tv ,details_tv;

        public BusInfo(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imgView_id);
            navigate_map = itemView.findViewById(R.id.navigate_map);
            bus_no_tv = itemView.findViewById(R.id.bus_number_id);
            bus_stops_tv = itemView.findViewById(R.id.bus_stops_id);
            sourceTime_tv =itemView.findViewById(R.id.times_id);
        }
    }
}
