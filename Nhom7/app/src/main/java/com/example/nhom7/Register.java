package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom7.Common.Common;
import com.example.nhom7.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    private Button btn1;
    private EditText editTextPhone;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;
    private Spinner spinner;
    private FirebaseAuth mAuth;
    private  DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private User user;
    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn1 = (Button) findViewById(R.id.btnDangky);
        editTextTextPassword=findViewById(R.id.editTextTextPassword);
        editTextTextPersonName=findViewById(R.id.editTextTextPersonName);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase=database.getInstance().getReference().child("User");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass, name;
                pass=editTextTextPassword.getText().toString();
                name=editTextTextPersonName.getText().toString();

                editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                String number= editTextPhone.getText().toString();
                String mobile = editTextPhone.getText().toString().trim();
                if(TextUtils.isEmpty(number)|| mobile.length() < 9){
                    Toast.makeText(Register.this, "Vui lòng điền đầy đủ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userId)) {
                    createUser(name, pass,number);
                } else {
                    updateUser(name, pass,number);
                }
                //   String data = name;
                //  mDatabase.child("Name").setValue(data);
                //  String a = pass;
                ////  mDatabase.child("Pass").setValue(a);
                //  String b = number;
                // mDatabase.child("Phone").setValue(b);

                Intent intent = new Intent(Register.this, HomeActivity.class);
                Common.currentUser= user;
                // intent.putExtra("mobile", number);
                startActivity(intent);
                finish();
            }
        });
    }

    private void updateUser(String name, String pass, String number) {
        if (!TextUtils.isEmpty(name))
            mDatabase.child(userId).child("Name").setValue(name);

        if (!TextUtils.isEmpty(pass))
            mDatabase.child(userId).child("Pass").setValue(pass);
        if (!TextUtils.isEmpty(number))
            mDatabase.child(userId).child("Pass").setValue(number);
    }



    private void addUserChangeListener() {
        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null!");
                    return;
                }

                Log.e(TAG, "User data is changed!" + user.name + ", " + user.pass+ ", " + user.phone);



                // clear edit text
                editTextPhone.setText("");
                editTextTextPersonName.setText("");
                editTextTextPassword.setText("");


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }



    private void createUser(String name, String pass, String number) {
        if (TextUtils.isEmpty(userId)) {
            userId = mDatabase.push().getKey();
        }

         user = new User(name,pass,number);

        mDatabase.child(userId).setValue(user);

        addUserChangeListener();

    }
}