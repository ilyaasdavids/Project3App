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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextView btn;
    Button btnLogin;
    EditText name;
    EditText pass;

//    FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String passwordFromDatabase;
    String usernameFromDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.btn);
        btnLogin = findViewById(R.id.btnLogin);
        name = findViewById(R.id.Name);
        pass = findViewById(R.id.Password);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

//        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {

            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            loginUser();
        });
        btn.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));

        });
    }
    private void loginUser(){
        String userName = name.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(userName.isEmpty()){
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }

        if(password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }

        if(password.length() < 5){
            pass.setError("Password should have a minimum of 5 characters!");
            pass.requestFocus();
            return;
        }

        //Validate login info

       // DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

       // Query checkUser = reference.orderByChild("name").equalTo(password);


        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    passwordFromDatabase = dataSnapshot.child("password").getValue().toString();
                    usernameFromDatabase = dataSnapshot.child("name").getValue().toString();

                    if(passwordFromDatabase.equals(password) && usernameFromDatabase.equals(userName)){
                        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       //startActivity(intent);
                        startActivity(new Intent(Login.this, Register.class));
                        System.out.println("");
                }
                    else  pass.setError("Password is incorrect");
                    pass.requestFocus();
                    return;


//                if(snapshot.exists()){
//
//
//                    String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);
//
//                    if(passwordFromDB.equals(password)){
//
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
//
//                    }else{
//                        pass.setError("Password is incorrect!");
//                        name.requestFocus();
//                        return;
//                    }
//
//                }else{
//                    name.setError("This username does not exist!");
//                    name.requestFocus();
//                    return;

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        mAuth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    //goes to next page?
//                    startActivity(new Intent (Login.this, MainActivity.class));
//
//                }else{
//                    Toast.makeText(Login.this, "Failed to login, Please check credentials", Toast.LENGTH_LONG).show();
//                }
//            }
//        });



//        if(userEmail.isEmpty()||password.isEmpty()){
//                    Toast.makeText(Login.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
//                }
//            else{
//                    Toast.makeText(Login.this, "Please enter correct login details", Toast.LENGTH_SHORT).show();
//                }
            ////???????????? remove above else statement maybe
//            else {
//                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
//                        @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(Login.this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
//                                Intent R = new Intent(Login.this, MainActivity.class);
//                            }else{
//                                Toast.makeText(Login.this, "Login Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//            }
//                })
//        }


    }
}



//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent R = new Intent(Login.this, Register.class);
//                startActivity(R);
//                finish();
//            }
//        });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String user = name.getText().toString().trim();
//                String pass = password.getText().toString().trim();
//
//                if(user.isEmpty()||pass.isEmpty()){
//                    Toast.makeText(Login.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
//                }
//
//                else{
//                    Toast.makeText(Login.this, "Please enter correct login details", Toast.LENGTH_SHORT).show();
//                }
//
//
//                //finish();
//            }
//        });
//    }
//
//}