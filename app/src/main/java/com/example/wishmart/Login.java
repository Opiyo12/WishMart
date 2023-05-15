package com.example.wishmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText email, password;

    private Button login, register;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference mRef;


    AlertDialog.Builder exit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login_button);
        register= findViewById(R.id.register_button);
        email= findViewById(R.id.email);
        password=findViewById(R.id.password);
        mAuth= FirebaseAuth.getInstance();
        db= FirebaseDatabase.getInstance();
        mRef= db.getReference().child("Registered Users");
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        LoginUser();

                }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Login.this, Register.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void LoginUser() {
        String useremail= email.getText().toString().trim();
        String pwd= password.getText().toString().trim();
        //validating user input

        if(useremail.isEmpty()){
            email.setError("Email Adress is Empty");
            email.requestFocus();
            return;
        }
        else if( pwd.isEmpty()){
            password.setError("Password is Empty");
            password.requestFocus();
            return;
        }
       else if (pwd.length() < 6) {
        password.setError("Password mistmached");
        password.requestFocus();
        return;
    }
        else if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(useremail,pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if user is logged in
                      if (task.isSuccessful()){
                            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                            mRef=FirebaseDatabase.getInstance().getReference().child("Registered Users").child(user.getUid());
                            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String role=snapshot.child("role").getValue(String.class);
                                    if(role==null){
                                        Toast.makeText(Login.this, "User not found", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                    else if(role.equals("admin")){
                                        progressDialog.dismiss();
                                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Login.this, Administrator.class));
                                        finish();
                                    }else if(role.equals("users")){
                                        progressDialog.dismiss();
                                        Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Login.this, Home.class));
                                        finish();
                                    }else{
                                        Toast.makeText(Login.this, "Login Failed, Please check your Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                      }else{
                          Toast.makeText(Login.this, "Login Failed, Please check your Credentials", Toast.LENGTH_LONG).show();
                          progressDialog.dismiss();
                      }
                    }
                });





    }

//    public  void exitDialog(){
//        exit=new AlertDialog.Builder(Login.this);
//        exit.setTitle("Exiting...");
//        exit.setMessage("Are you sure you want to Exit?");
//        exit.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//
//        exit.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//               startActivity(new Intent(Login.this, Login.class));
//            }
//        });
//
//        exit.create().show();
//    }
}