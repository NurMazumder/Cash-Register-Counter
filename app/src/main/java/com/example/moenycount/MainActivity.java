package com.example.moenycount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText etEmail,etPass;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.tvReg);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        
        if(user !=null){
            finish();
            startActivity(new Intent(MainActivity.this,counter.class));
        }

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tvReg:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.btnLogin:
                  userLogin();
                  break;


        }
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (email.isEmpty()){
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError(("Please enter valid Email!"));
            etEmail.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            etPass.setError("Password Required!");
            etPass.requestFocus();
            return;
        }
        if(pass.length() < 6){
            etPass.setError("Password must be at least 6 characters!");
            etPass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, counter.class));
                }
                else{
                    Toast.makeText(MainActivity.this, "Failed, Try again!", Toast.LENGTH_LONG);
                }
            }
        });
    }
}
