package com.example.project3app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class AppointmentMade extends AppCompatActivity {
    RecyclerView listOfApp;
    ArrayList<appointmentDomain> list;
    DatabaseReference reference;
    appointmentAdapter adapter;
    appointmentDomain domain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_made);

        listOfApp = findViewById(R.id.AppList);
        reference =  FirebaseDatabase.getInstance().getReference().child("Appointments");
//        Query allByPatient = reference.orderByChild("patientId").equalTo(patientId).startAt(patientId).endAt(patientId);
        listOfApp.setHasFixedSize(true);
        listOfApp.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new appointmentAdapter(this,list);
        listOfApp.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    appointmentDomain domain = dataSnapshot.getValue(appointmentDomain.class);
                    list.add(domain);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}