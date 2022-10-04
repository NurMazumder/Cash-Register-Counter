package com.example.moenycount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private EditText etEmail2, etPass2, etUserName;
    private TextView banner2, registerUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner2 = (TextView) findViewById(R.id.tvBanner2);
        banner2.setOnClickListener(this);

        registerUser = (TextView) findViewById(R.id.btnRegister);
        registerUser.setOnClickListener(this);

        etEmail2 = (EditText) findViewById(R.id.etEmail2);
        etPass2 = (EditText) findViewById(R.id.etEmail2);
        etUserName = (EditText) findViewById(R.id.etUserName);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvBanner2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnRegister:
                RegisterUser();
                break;
        }
    }

    private void RegisterUser() {
        String email = etEmail2.getText().toString().trim();
        String pass = etPass2.getText().toString().trim();
        String username = etUserName.getText().toString().trim();

        if(email.isEmpty()){
            etEmail2.setError("Email is required!");
            etEmail2.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            etPass2.setError("Password is required!");
            etPass2.requestFocus();
            return;
        }
        if(username.isEmpty()){
            etUserName.setError("Username is required!");
            etUserName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail2.setError("Please Provide Valid Email!");
            etEmail2.requestFocus();
            return;
        }
        if(pass.length() < 6){
            etPass2.setError("Password must be at least 6 characters!");
            etPass2.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(email, username);
                            Task<Void> database = FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this,"User has been registered!", Toast.LENGTH_LONG).show();
                                            }
                                            else{
                                                Toast.makeText(Register.this,"User failed to registered,try again!", Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });
                        }
                        else{
                            Toast.makeText(Register.this,"User failed to registered,try again!", Toast.LENGTH_LONG).show();

                        }
                    }
                });

    }
}