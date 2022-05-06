package com.example.nhom7;

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


import com.example.nhom7.HolderViewItem.CountryData;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.text.BreakIterator;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    private Button btn1;
    private TextView tvt1;
    private EditText editTextPhone;
    private Spinner spinner;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();
        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //  DatabaseReference myRef = database.getReference("Use");
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);;
        btn1 = (Button) findViewById(R.id.btnDangnhap);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editTextPhone.getText().toString();

                String mobile = editTextPhone.getText().toString().trim();
                if(TextUtils.isEmpty(editTextPhone.getText().toString())|| mobile.length() < 9){
                    editTextPhone.setError("Enter a valid mobile");
                    editTextPhone.requestFocus();
                    return;
                }

                String phoneNumber = "+"+ code + number;



                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
        tvt1 = (TextView) findViewById(R.id.tvDangky);
        tvt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
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