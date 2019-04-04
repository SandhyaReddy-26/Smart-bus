package com.vn8031.smartbus;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailsActivity extends AppCompatActivity {

    TextView busNumber_tv,busDriver_tv,stopName,stopTime,s_dPlace,tv_driver_contact;
    DatabaseReference myRef;
    private String val_stops,val_times;
    String stopName1,busNum,busDriverName,bus_DriverContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        busNumber_tv = findViewById(R.id.bus_number);
        busDriver_tv = findViewById(R.id.bus_driver);
        stopName = findViewById(R.id.bus_stopName);
        stopTime = findViewById(R.id.bus_stopTime);
        s_dPlace = findViewById(R.id.bus_s_d_place);
        tv_driver_contact = findViewById(R.id.bus_driver_contact);

        busNum = getIntent().getStringExtra("busNumberKey");
        busDriverName =getIntent().getStringExtra("driverNamesKey");
        bus_DriverContact = getIntent().getStringExtra("driverContactsKey");
        stopName1 = getIntent().getStringExtra("stopKey");

        busNumber_tv.setText("Bus Number: "+busNum);
        busDriver_tv.setText("Bus Driver Name: "+ busDriverName);
        tv_driver_contact.setText("Bus Driver Number:"+bus_DriverContact);

        s_dPlace.setText(stopName1);
        //  Toast.makeText(this, stopName, Toast.LENGTH_SHORT).show();
        myRef = FirebaseDatabase.getInstance().getReference().child(stopName1);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot stop: dataSnapshot.getChildren())
                {
                    String name = stop.child("name").getValue(String.class);
                    String time = stop.child("time").getValue(String.class);

                 /*  stopName.setText(stopName.getText() + name);
                   stopTime.setText(stopTime.getText() + time);
                   */
                    val_stops = name +"\n";
                    val_times = time +"\n";
                    stopName.setText(stopName.getText()+val_stops);
                    stopName.setTextColor(Color.GREEN);
                    stopName.setAllCaps(true);
                    stopTime.setText(stopTime.getText() +val_times);
                    stopTime.setTextColor(Color.BLUE);

                    Toast.makeText(DetailsActivity.this, ""+ name, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
