package com.example.project3app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextView btn1;
    Button regbtn;
    EditText name;
    EditText email;
    EditText pass;
    EditText confpassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn1 = findViewById(R.id.btn1);
        regbtn = findViewById(R.id.Reg);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.Pass);
        confpassword = findViewById(R.id.ConfirmPass);

        //initialize mAuth variable
        mAuth = FirebaseAuth.getInstance();


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        regbtn.setOnClickListener(view -> {
            createUser();
        });

        //Switch to login screen
        btn1.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, Login.class));
        });
    }

    private void createUser() {
        String userName = name.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String confirmpassword = confpassword.getText().toString().trim();

        if(userName.isEmpty()){
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }

        if(userEmail.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }
        if (password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }
        if(password.length() < 5){
            pass.setError("Password should have a minimum of 5 characters!");
            pass.requestFocus();
            return;
        }

        if(!password.equals(confirmpassword)){
            confpassword.setError("Passwords do not match!");
            pass.requestFocus();
            return;
        }

        //saving to database (firebase)

        mAuth.createUserWithEmailAndPassword(userEmail,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(userName, userEmail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Register.this, "Failed to register, Try Again!", Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                        } else{
                            Toast.makeText(Register.this, "Failed to register, Try Again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


//        if (user.isEmpty() || password.isEmpty() || confirmpassword.isEmpty() || userEmail.isEmpty()) {
//            Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//        } else if (password.equals(confirmpassword)) {
//
//            Toast.makeText(Register.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
//
//            Intent R = new Intent(Register.this, Login.class);
//            startActivity(R);
//
//        }
//        //Switch to login screen
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent R = new Intent(Register.this, Login.class);
//                startActivity(R);
//
//                finish();
//            }
//        });
    }
}






//        regbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String user = name.getText().toString().trim();
//                String password = pass.getText().toString().trim();
//                String confirmpassword = confpassword.getText().toString().trim();
//                String number = num.getText().toString().trim();
//
//                if(user.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()||number.isEmpty()){
//                    Toast.makeText(Register.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                }
//                else if (password.equals(confirmpassword)) {
//
//                        Toast.makeText(Register.this, "You have successfully registered", Toast.LENGTH_SHORT).show();
//
//                        Intent R = new Intent(Register.this, Login.class);
//                        startActivity(R);
//                        //finish();
//                    }
//
//                } else {
//
//                    Toast.makeText(Register.this, "Password does not match", Toast.LENGTH_SHORT).show();
//
//                }
//
//            }

//        });