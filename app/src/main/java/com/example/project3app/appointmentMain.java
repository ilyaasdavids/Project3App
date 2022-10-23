package com.example.project3app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class appointmentMain extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private String date ,validDate ,validTime , time ,error;
    private boolean isSuccess;
    Long appId = Long.valueOf(0);
    appointmentDomain appointmentDomain;

    Button btnBook,btnDate,btnAppMade;
    Spinner spnTime;

    TextView txtDate;
    CalendarView calendarView;
    //
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment_main);

        spnTime = findViewById(R.id.spinnerTime);

        txtDate = findViewById(R.id.textViewDate);
        txtDate.setText(getTodaysDate());

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setDate(getMiliTime(),false,true);
        calendarView.setMinDate(getMiliTime()-1000);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                month++;
                date = makeDateString(day,month,year);
                txtDate.setText(date);
            }
        });

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
                            appId = (snapshot.getChildrenCount());

                        for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            validTime = dataSnapshot.child("time").getValue().toString();
                            validDate = dataSnapshot.child("date").getValue().toString();

                            if (validTime.equals(spnTime.getSelectedItem()) && validDate.equals(txtDate.getText())){
                                isSuccess = false;
                                Toast.makeText(appointmentMain.this, "DATE and TIME has been reserved already", Toast.LENGTH_SHORT).show();
                                break;
                            }else{
                                isSuccess = true;
                                Toast.makeText(appointmentMain.this, "Your appointment has been booked", Toast.LENGTH_SHORT).show();
                            }
                        }



                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if(isSuccess) {
                    time = spnTime.getSelectedItem().toString();
                    String patientId = "0010125038456";
                    appId = ++appId;
                    String date = txtDate.getText().toString();

                    appointmentDomain = new appointmentDomain(String.valueOf(appId), patientId, date, time);
                    reference.child(String.valueOf(appId)).setValue(appointmentDomain);
                }

                System.out.println("Entering values...");

            }
        });
    }

    private long getMiliTime() {
        String date = setCalendarDate();
        String parts[] = date.split("/");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,day);

        long miliTime = calendar.getTimeInMillis();
        return miliTime;
    }

    private void openAppMade() {
        Intent intent = new Intent(this, AppointmentMade.class);
        startActivity(intent);
    }

    private String getTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month++;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        day += 7;
        return makeDateString(day,month,year);
    }

    private String setCalendarDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        day += 7;
        return calendarViewDate(day,month,year);
    }

    private String makeDateString(int day, int month, int year) {
        return day + "-" + getMonthFormat(month) + "-" + year ;
    }

    private String calendarViewDate(int day, int month, int year) {
        return day + "/" + month + "/" + year ;
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