package com.example.nhom7;

import static android.content.ContentValues.TAG;

import static com.google.firebase.auth.PhoneAuthProvider.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom7.Common.Common;
import com.example.nhom7.Model.User;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.concurrent.TimeUnit;

public class MainActivity4 extends AppCompatActivity {
    private Button btnXacNhan;
    private String verificationId;
    private FirebaseAuth mAuth;
    private EditText editText;
    private String userId;
    private DatabaseReference mDatabase;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        mAuth = FirebaseAuth.getInstance();
        editText = findViewById(R.id.editTextNumber);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
         mDatabase = database.getInstance().getReference().child("User");
        String phonenumber = getIntent().getStringExtra("phoneNumber");
        sendVerificationCode(phonenumber);

        btnXacNhan = (Button) findViewById(R.id.btnDangky2);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length() < 6) {

                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
                String pass, name;
                pass=null;
                name=null;


                createUser(name, pass,phonenumber);
                Common.currentUser= user;

//                    createUser(phonenumber);


            }
        });
    }

    private void  createUser(String name,String pass,String phonenumber) {
        if (TextUtils.isEmpty(userId)) {
            userId = mDatabase.push().getKey();
        }

        user = new User(name, pass,phonenumber);

        mDatabase.child(userId).setValue(user);


    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(MainActivity4.this, HomeActivity.class);
                            Common.currentUser= user;
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity4.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String phonenumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84" + phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        // getInstance().verifyPhoneNumber(
        //   phonenumber,
        //    60,
        //     TimeUnit.SECONDS,
        //     (Activity) TaskExecutors.MAIN_THREAD,
        //      mCallBack
        // );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent( String s,ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
            ForceResendingToken mResendToken = forceResendingToken;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);

            }
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(MainActivity4.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currenUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currenUser!=null){
            startActivity(new Intent(MainActivity4.this, MainActivity4.class));
            finish();
        }
    }
}