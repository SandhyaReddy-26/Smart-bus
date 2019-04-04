package com.vn8031.smartbus;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    CardView cv;
    FirebaseAuth mAuth;
    RecyclerView rv;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rv = v.findViewById(R.id.home_recycler);
        mAuth =FirebaseAuth.getInstance();

        int images[] = {R.drawable.sritbus,R.drawable.sritbus,R.drawable.sritbus,
                R.drawable.sritbus,R.drawable.sritbus,R.drawable.sritbus,
                R.drawable.sritbus,R.drawable.sritbus,R.drawable.sritbus};
        String busNumbers[] = {"2","5","6","7","10","14","15","16","17"};
        String source_destinationPlace[] = {"Syndicate Nagar-SRIT","Bhairav Nagar-SRIT","Rajahamsa Apartment-SRIT",
                "Dwaraka Vilas-SRIT","Maruthi Nagar-SRIT","Erranal Kottal-SRIT","Arvind Nagar-SRIT",
                "Chinmayi nagar-SRIT","Nayak Nagar-SRIT"};

        String sourceTime[] = {"8:15 AM","8:25 AM","8:30 AM","8:15 AM","8:15 AM",
                "8:25 AM","8:25 AM","8:25 AM","8:25 AM"};
        String destinationTime[] = {"9:15 AM","9:20 AM","9:20 AM","9:15 AM",
                "9:15 AM","9:20 AM","9:20 AM","9:20 AM","9:15 AM"};
        String locations[] = {"Syndicate Nagar, Anantapur, Andhra Pradesh 515002",
                "Bhairav Nagar, Anantapur, Andhra Pradesh 515002",
                "Rajahamsa Apartment, Anantapur, Andhra Pradesh 515002",
                "Dwaraka Vilas, Anantapur, Andhra Pradesh 515002",
                "Maruthi Nagar, Anantapur, Andhra Pradesh 515002",
                "Erranal Kottal, Anantapur, Andhra Pradesh 515002",
                "Arvind Nagar, Anantapur, Andhra Pradesh 515002",
                "Chinmayi nagar, Anantapur, Andhra Pradesh 515002",
                "Nayak Nagar, Anantapur, Andhra Pradesh 515002"};
        String driverNames[] = {"Khasim Peera","Ramu","Vali",
                "Ramudu","viswanath","Ravi",
                "Jakriya","Peddi Reddy","Allah Badush"};
        String driverContacts[] = {"9052601770","9110538328","7799122441",
                "9849168805","9550878151","8985599166",
                "9908707415","8374326178","9885119671"};
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        BusAdapter adapter = new BusAdapter(getContext(),images,busNumbers,source_destinationPlace,sourceTime,destinationTime,locations,driverNames,driverContacts);
        rv.setAdapter(adapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        return v;
    }

}
