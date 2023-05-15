package com.example.wishmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Register extends AppCompatActivity {
    EditText name_create, password_create, phone_create, email_create;
    Button register;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    TextView login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name_create = findViewById(R.id.username);
        password_create = findViewById(R.id.password);
        phone_create = findViewById(R.id.phone);
        email_create = findViewById(R.id.email);
        register = findViewById(R.id.register_button);
        login= findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Registering...");
        progressDialog.setMessage("Registration in Progress");

         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 userLogin();
             }

             private void userLogin() {
                 Intent i= new Intent(getApplicationContext(), Login.class);
                 startActivity(i);
                 finish();
             }
         });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.register_button:
                        registerUser();
                        break;
                    case R.id.forgot_password:
                        break;
                }
            }


            private void registerUser() {
                String name = name_create.getText().toString().trim();
                String password = password_create.getText().toString().trim();
                String phone = phone_create.getText().toString().trim();
                String email = email_create.getText().toString().trim();
                String role="users";
                // validating user input
                if (name.isEmpty()) {
                    name_create.setError("Name Field is Empty");
                    name_create.requestFocus();
                    return;
                } else if (password.isEmpty()) {
                    password_create.setError("Password Field is Empty");
                    password_create.requestFocus();
                    return;
                } else if (phone.isEmpty()) {
                    phone_create.setError("Phone Field is Empty");
                    phone_create.requestFocus();
                    return;
                } else if (email.isEmpty()) {
                    email_create.setError("email Field is Empty");
                    email_create.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_create.setError("Please provide valid email");
                    email_create.requestFocus();
                    return;
                } else if (password.length() < 6) {
                    password_create.setError("Min password length is 6 characters");
                    password_create.requestFocus();
                    return;
                }
               progressDialog.show();
              mAuth.createUserWithEmailAndPassword(email,password)
                      .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              // creating user registration object for firebase submission
                              UserRegistration user= new UserRegistration(name,password, phone, email,role);
                              // creating the table name for keeping registered users in firebase
                              FirebaseDatabase.getInstance().getReference("Registered Users")
                                      .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                      .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                progressDialog.dismiss();
                                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                //redirecting the user to login interface
                                               startActivity(new Intent(Register.this, Login.class));

                                            }
                                            else {
                                                Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                                                progressDialog.dismiss();
                                            }
                                          }
                                      });



                          } else {
                              Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_LONG).show();
                             progressDialog.dismiss();
                          }

                          }
                      });

            }
        });


    }


}