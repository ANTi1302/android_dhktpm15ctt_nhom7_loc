package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity9 extends AppCompatActivity {
    private ImageView img1, img2,img3,img4;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        img1 = (ImageView) findViewById(R.id.imageView18);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        textView = (TextView) findViewById(R.id.textView37);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this, MainActivity10.class);
                startActivity(intent);
            }
        });
        img2 = (ImageView) findViewById(R.id.imageView17);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        img3 = (ImageView) findViewById(R.id.imageView19);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this, MainActivity11.class);
                startActivity(intent);
            }
        });
        img4 = (ImageView) findViewById(R.id.imageView20);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity9.this, MainActivity9.class);
                startActivity(intent);
            }
        });
    }
}