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

public class Login extends AppCompatActivity {

    TextView btn;
    Button btnLogin;
    EditText email;
    EditText pass;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.btn);
        btnLogin = findViewById(R.id.btnLogin);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.Password);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);

        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        btn.setOnClickListener(view -> {
            startActivity(new Intent(Login.this, Register.class));

        });
    }
    private void loginUser(){
        String userEmail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

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

        mAuth.signInWithEmailAndPassword(userEmail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //goes to next page?
                    startActivity(new Intent (Login.this, MainActivity.class));

                }else{
                    Toast.makeText(Login.this, "Failed to login, Please check credentials", Toast.LENGTH_LONG).show();
                }
            }
        });



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