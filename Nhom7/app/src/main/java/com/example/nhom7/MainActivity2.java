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


import com.example.nhom7.Common.Common;
import com.example.nhom7.HolderViewItem.CountryData;
import com.example.nhom7.Model.User;
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
    private EditText editTextPhone,txtPass;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private boolean otpSent=false;
    private String myCodeCountry="+84";
    private String id="";
    private User user;

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
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        txtPass = (EditText) findViewById(R.id.txtPassss);


        btn1 = (Button) findViewById(R.id.btnDangnhap);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();
                String number = editTextPhone.getText().toString().trim();
                String pass = txtPass.getText().toString();
                user=new User(phone,number,pass);
                if(TextUtils.isEmpty(phone)||number.length()<10){
                    Toast.makeText(MainActivity2.this, "Vui lòng điền đầy đủ, SĐT 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity2.this, HomeActivity.class);
                Common.currentUser= user;
                intent.putExtra("mobile", phone);
                startActivity(intent);





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


   // @Override
   // protected void onStart() {
   //     super.onStart();
    //    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
    //        Intent intent = new Intent(this, HomeActivity.class);
     //       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

     //       startActivity(intent);
     //   }
  //  }


}