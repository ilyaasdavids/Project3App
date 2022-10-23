package com.example.project3app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    TextView button2;

    private EditText editTextTo;
    private EditText editTextSubject;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        button2 = findViewById((R.id.button2));

        editTextTo = findViewById(R.id.editText1);
        editTextSubject = findViewById(R.id.editText2);
        editTextMessage = findViewById(R.id.editText3);

        Button buttonSend = findViewById(R.id.button1);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent R = new Intent(Contact.this, Reminder.class);
                //startActivity(R);
                //finish();
            }
        });
    }

    private void sendMail() {
        String recipientList = editTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));


    }


}
