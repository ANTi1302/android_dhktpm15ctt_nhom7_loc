package com.example.nhom7;

import static com.google.firebase.auth.PhoneAuthProvider.getCredential;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nhom7.HolderViewItem.CountryData;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    private Button btn1;
    private TextView tvt1;
    private EditText editTextPhone;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private boolean otpSent=false;
    private String myCodeCountry="+84";
    private String id="";
    private PhoneAuthOptions options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
//        spinner = findViewById(R.id.spinnerCountries);
//        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //  DatabaseReference myRef = database.getReference("Use");
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);;
        btn1 = (Button) findViewById(R.id.btnDangnhap);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

//                String number = editTextPhone.getText().toString();
//

                if(otpSent){
                    editTextPhone.setError("Enter a valid mobile");
                    editTextPhone.requestFocus();
                    return;
                }else {
                    final  String getmoblie=editTextPhone.getText().toString();
////                    sendVerifi(phoneNumber);
//                     options=PhoneAuthOptions.newBuilder(mAuth)
//                            .setPhoneNumber(myCodeCountry+getmoblie)
//                            .setTimeout(60L,TimeUnit.SECONDS)
//                            .setActivity(MainActivity2.this).setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                @Override
//                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                }
//
//                                @Override
//                                public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                }
//
//                                @Override
//                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                    super.onCodeSent(s, forceResendingToken);
//
//                                    id=s;
//                                    otpSent=true;
//                                }
//                            }).build();
//                    PhoneAuthProvider.verifyPhoneNumber(options);
                    Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                    intent.putExtra("phoneNumber", getmoblie);
                    startActivity(intent);

                }
//                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
//                intent.putExtra("phoneNumber", String.valueOf(options));
//                startActivity(intent);
//                    final  String getmoblie=editTextPhone.getText().toString();



            }


        });
        tvt1 = (TextView) findViewById(R.id.tvDangky);
        tvt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Register.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }


}