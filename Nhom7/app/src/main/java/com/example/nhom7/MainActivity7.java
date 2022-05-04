package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity7 extends AppCompatActivity {
    private ImageView img1;
    private ImageButton imbt1;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity7.this, FoodListActivity.class);
                startActivity(intent);
            }
        });
        imbt1 = (ImageButton) findViewById(R.id.imageButton8);
        imbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity7.this, FoodListActivity.class);
                startActivity(intent);
            }
        });
        btn1 = (Button) findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity7.this,"Đặt món thành công",Toast.LENGTH_SHORT).show();
            }
        });

    }

}