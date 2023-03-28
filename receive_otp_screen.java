package com.example.community;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class receive_otp_screen extends AppCompatActivity {

    EditText et1, et2, et3, et4, et5,et6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_otp_screen);



        final ProgressBar progressBar = findViewById(R.id.progress_sumit);
        et1 = findViewById(R.id.editTextPhone_1);
        et2 = findViewById(R.id.editTextPhone_2);
        et3 = findViewById(R.id.editTextPhone_3);
        et4 = findViewById(R.id.editTextPhone_4);
        et5 = findViewById(R.id.editTextPhone_5);
        et6 = findViewById(R.id.editTextPhone_6);
        final Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {
            if(!et1.getText().toString().trim().isEmpty() && !et2.getText().toString().trim().isEmpty() && !et3.getText().toString().trim().isEmpty() && !et4.getText().toString().trim().isEmpty() && !et5.getText().toString().trim().isEmpty() && !et6.getText().toString().trim().isEmpty()){
                String entercodeotp =
                                et1.getText().toString() +
                                et2.getText().toString() +
                                et3.getText().toString() +
                                et4.getText().toString() +
                                et5.getText().toString() +
                                et6.getText().toString();

                if(getIntent().getStringExtra("backendotp") != null){
                    progressBar.setVisibility(View.VISIBLE);
                    button.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            getIntent().getStringExtra("backendotp"), entercodeotp

                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    button.setVisibility(View.VISIBLE);

                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(receive_otp_screen.this, enter_name.class);
                                        startActivity(intent);
                                        Toast.makeText(receive_otp_screen.this, "login sucess", Toast.LENGTH_SHORT).show();

                                        SharedPreferences sharedPreferences = getSharedPreferences("mypref", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("name", "true");
                                        editor.apply();
                                    }else{
                                        Toast.makeText(receive_otp_screen.this, "enter correct otp", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                }else {
                    Toast.makeText(this, "please check internet connection", Toast.LENGTH_SHORT).show();
                }

            }else {
                Toast.makeText(receive_otp_screen.this, "please enter otp", Toast.LENGTH_SHORT).show();
            }
        });
        moveOTP();

    }


    public void moveOTP(){
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().isEmpty()){
                    et2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().isEmpty()){
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().isEmpty()){
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().isEmpty()){
                    et5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (!s.toString().isEmpty()){
                    et6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}