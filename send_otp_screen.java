package com.example.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class send_otp_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp_screen);


        ProgressBar progressBar = findViewById(R.id.progress_send);
        EditText editText = findViewById(R.id.editTextPhone);
        Button button= findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!editText.getText().toString().trim().isEmpty()){
                    if (editText.getText().toString().length() != 10){
                        Toast.makeText(send_otp_screen.this, "please enter correct number", Toast.LENGTH_SHORT).show();
                    }else{
                        progressBar.setVisibility(View.VISIBLE);
                        button.setVisibility(View.INVISIBLE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + editText.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                send_otp_screen.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);

                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);

                                        Toast.makeText(send_otp_screen.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Intent intent = new Intent(send_otp_screen.this, receive_otp_screen.class);
                                        intent.putExtra("backendotp", s);
                                        startActivity(intent);
                                        progressBar.setVisibility(View.GONE);
                                        button.setVisibility(View.VISIBLE);

                                    }
                                }

                        );

                    }
                }else{
                    Toast.makeText(send_otp_screen.this, "please enter phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}