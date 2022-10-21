package com.example.project3app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private String date;
    Long appId = Long.valueOf(0);
    appointmentDomain appointmentDomain;

    Button btnBook,btnDate,btnAppMade;
    Spinner spnTime;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnTime = findViewById(R.id.spinnerTime);

        initDatePicker();
        btnDate = findViewById(R.id.buttonDate);
        btnDate.setText(getTodaysDate());

        btnAppMade = findViewById(R.id.buttonAppMade);
        btnAppMade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppMade();
            }
        });

        btnBook = findViewById(R.id.buttonBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference().child("Appointments");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                            appId = (snapshot.getChildrenCount()
                            );
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                String time = spnTime.getSelectedItem().toString();
                String patientId = "0010125038456";
                appId = ++appId;
                String date = btnDate.getText().toString();

                appointmentDomain = new appointmentDomain(String.valueOf(appId),patientId,date,time);

                reference.child(String.valueOf(appId)).setValue(appointmentDomain).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Your appointment has been booked",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                System.out.println("Entering values...");
            }
        });
    }

    private void openAppMade() {
        Intent intent = new Intent(this, AppointmentMade.class);
        startActivity(intent);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month++;
                date = makeDateString(day,month,year);
                btnDate.setText(date);
            }

        };
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month++;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "-" + getMonthFormat(month) + "-" + year ;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

}