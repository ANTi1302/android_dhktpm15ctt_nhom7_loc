package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity8 extends AppCompatActivity {
    private ImageView img1;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        img1 = (ImageView) findViewById(R.id.img_back);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity8.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn1 = (Button) findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity8.this,"Đặt món thành công",Toast.LENGTH_SHORT).show();
            }
        });

    }

}