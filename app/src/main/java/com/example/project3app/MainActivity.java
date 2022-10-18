package com.example.project3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    appointmentDomain appointmentDomain;

    Button btnBook;
    Spinner spnTime;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTime = findViewById(R.id.spinnerTime);


        btnBook = findViewById(R.id.buttonBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Appointments");

                String time = spnTime.getSelectedItem().toString();
                String id = "5";

                appointmentDomain = new appointmentDomain(id,time);

                reference.child(id).setValue(appointmentDomain);
                System.out.println("Entering values...");
            }
        });
    }
}