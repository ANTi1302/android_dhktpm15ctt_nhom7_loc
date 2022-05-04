package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity11 extends AppCompatActivity {
    private ImageView img1, img2,img3,img4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        img1 = (ImageView) findViewById(R.id.imageView18);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity11.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        img2 = (ImageView) findViewById(R.id.imageView17);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity11.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        img3 = (ImageView) findViewById(R.id.imageView19);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity11.this, MainActivity11.class);
                startActivity(intent);
            }
        });
        img4 = (ImageView) findViewById(R.id.image_user);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity11.this, MainActivity9.class);
                startActivity(intent);
            }
        });
    }
}