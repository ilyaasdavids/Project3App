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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    TextView btn1;
    Button regbtn;
    EditText name;
    EditText email;
    EditText cell;
    EditText pass;


    //FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn1 = findViewById(R.id.btn1);
        regbtn = findViewById(R.id.Reg);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        cell = findViewById(R.id.num);
        pass = findViewById(R.id.Pass);


        //initialize mAuth variable
        // mAuth = FirebaseAuth.getInstance();


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_layout);


        regbtn.setOnClickListener(view -> {
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            createUser();
        });

        //Switch to login screen
        btn1.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, Login.class));
        });
    }


private void createUser(){

    //get all values
    String userName = name.getText().toString().trim();
    String userEmail = email.getText().toString().trim();
    String cellNo = cell.getText().toString().trim();
    String password = pass.getText().toString().trim();

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

    if(cellNo.isEmpty()){
        cell.setError("Cell number is required!");
        pass.requestFocus();
        return;
    }

    if(cellNo.length() < 10 ||cellNo.length() > 10){
        cell.setError("Please enter a valid cell number!");
        pass.requestFocus();
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
    User userDomain = new User(userName,userEmail,cellNo,password);

    reference.child(cellNo).setValue(userDomain);
    Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();


}
}
    //the email auth way
//    private void createUser() {
//        String userName = name.getText().toString().trim();
//        String userEmail = email.getText().toString().trim();
//        String password = pass.getText().toString().trim();
//        String confirmpassword = confpassword.getText().toString().trim();
//
//        if(userName.isEmpty()){
//            name.setError("Name is required!");
//            name.requestFocus();
//            return;
//        }
//
//        if(userEmail.isEmpty()){
//            email.setError("Email is required!");
//            email.requestFocus();
//            return;
//        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
//            email.setError("Please enter a valid email!");
//            email.requestFocus();
//            return;
//        }
//        if (password.isEmpty()){
//            pass.setError("Password is required!");
//            pass.requestFocus();
//            return;
//        }
//        if(password.length() < 5){
//            pass.setError("Password should have a minimum of 5 characters!");
//            pass.requestFocus();
//            return;
//        }
//
//        if(!password.equals(confirmpassword)){
//            confpassword.setError("Passwords do not match!");
//            pass.requestFocus();
//            return;
//        }
//
//        //saving to database (firebase)
//
//        mAuth.createUserWithEmailAndPassword(userEmail,password)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            User user = new User(userName, userEmail);
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
//
//                                    } else {
//                                        Toast.makeText(Register.this, "Failed to register, Try Again!", Toast.LENGTH_LONG).show();
//                                    }
//                                }
//
//                            });
//                        } else{
//                            Toast.makeText(Register.this, "Failed to register, Try Again!", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
//    }
//}






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