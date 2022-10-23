//package com.example.project3app;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.material.button.MaterialButton;
//
//import io.realm.Realm;
//import io.realm.RealmChangeListener;
//import io.realm.RealmResults;
//import io.realm.Sort;
//
//public class Reminder extends AppCompatActivity {
//
//    TextView notesbutton, btnNext1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reminder);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.action_bar_layout);
//
//        notesbutton = findViewById((R.id.notesbutton));
//        btnNext1 = findViewById((R.id.btnNext1));
//
//        MaterialButton addNoteBtn = findViewById(R.id.addnewnotebtn);
//
//        addNoteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Reminder.this,AddNoteActivity.class));
//            }
//        });
//
//        notesbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent R = new Intent(Reminder.this, appointmentMain.class);
//                startActivity(R);
//                finish();
//            }
//        });
//
//        btnNext1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent R = new Intent(Reminder.this, Contact.class);
//                startActivity(R);
//                finish();
//            }
//        });
//
//        Realm.init(getApplicationContext());
//        Realm realm = Realm.getDefaultInstance();
//
//        RealmResults<Note> notesList = realm.where(Note.class).findAllSorted("createdTime", Sort.DESCENDING);
//
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),notesList);
//        recyclerView.setAdapter(myAdapter);
//
//        notesList.addChangeListener(new RealmChangeListener<RealmResults<Note>>() {
//            @Override
//            public void onChange(RealmResults<Note> notes) {
//                myAdapter.notifyDataSetChanged();
//            }
//        });
//
//
//    }
//}