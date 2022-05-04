package com.example.nhom7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom7.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference food;
    TextView txtName,txtPriceDetail,txtCate,txtCount,txtPrice;
    ImageView imgFood, imgBack;
    Button btnAddFood,btnAddCart;
    ImageButton btnSub,btnRemove;
    private int mLesson = 1;
    private String foodId="";
    private String cate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //get id
        btnSub= findViewById(R.id.sub);
        btnRemove= findViewById(R.id.remove);
        txtCount=findViewById(R.id.count);
        txtName=findViewById(R.id.name_food_detail);
        txtPriceDetail=findViewById(R.id.price_food_detail);
        txtPrice=findViewById(R.id.price);
        txtCate=findViewById(R.id.category_name_detail);
        imgFood=findViewById(R.id.img_fooddetail);
        imgBack=findViewById(R.id.imgBackDetail);
        btnAddCart=findViewById(R.id.addCart);

         //Xu ly btnSub, btnRemove
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLesson++;
                setSolanClick();
                btnSub.setBackgroundColor(Color.GRAY);
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLesson<1){
                    mLesson=1;
                }else
                mLesson--;
                setSolanClick();
                btnRemove.setBackgroundColor(Color.GRAY);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodDetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        //Init Firebase
        database= FirebaseDatabase.getInstance();
        food=database.getReference("Food");

        //Get intent here
        if (getIntent()!=null){
            foodId=getIntent().getStringExtra("FoodId");
            cate =getIntent().getStringExtra("CategoryName");
            txtCate.setText(cate);

        }if (!foodId.isEmpty()&&foodId!=null){
            loadListFoodDetail(foodId);
        }
    }

    private void loadListFoodDetail(String foodId) {
        food.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Food food=snapshot.getValue(Food.class);

                //Set Image
                Picasso.with(getBaseContext()).load(food.getImage()).into(imgFood);
                txtPriceDetail.setText(food.getPrice());
                txtPrice.setText(food.getPrice());
                txtName.setText(food.getName());

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    public  void setSolanClick(){
        txtCount.setText( mLesson+"");
    }
}