package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity10 extends AppCompatActivity {
    private ImageView img1, img2,img3,img4;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        img1 = (ImageView) findViewById(R.id.imageView18);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity10.this, AccountActivity.class);
                startActivity(intent);
            }
        });
        img2 = (ImageView) findViewById(R.id.imageView17);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity10.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        img3 = (ImageView) findViewById(R.id.imageView19);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity10.this, MainActivity11.class);
                startActivity(intent);
            }
        });
        img4 = (ImageView) findViewById(R.id.imageView20);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity10.this, AccountActivity.class);
                startActivity(intent);
            }
        });
        btn1 = (Button) findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity10.this,"Cập nhật tài khoản thành công",Toast.LENGTH_SHORT).show();
            }
        });
    }
}