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
import android.widget.Toast;

import com.example.nhom7.DB.Database;
import com.example.nhom7.HolderViewItem.FoodViewOfListHolder;
import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.Model.Food;
import com.example.nhom7.Model.Order;
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
    Button btnAddFood;
    ImageButton btnSub,btnRemove,btnAddCart;
    private int mLesson = 1;
    private String foodId="";
    private String cate="";
    Food currentfood;
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
        btnAddCart=findViewById(R.id.addFood);

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        currentfood.getName(),
                        txtCount.getText().toString(),
                        currentfood.getPrice(),
                        currentfood.getDiscount()
                ));
                Toast.makeText(FoodDetailActivity.this,"Added to Cart",Toast.LENGTH_LONG).show();

            }
        });
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


        }if (!foodId.isEmpty()&&foodId!=null){
            loadListFoodDetail(foodId);
        }


    }

    private void loadListFoodDetail(String foodId) {
        food.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                 currentfood=snapshot.getValue(Food.class);

                //Set Image
                Picasso.with(getBaseContext()).load(currentfood.getImage()).into(imgFood);
                txtPriceDetail.setText(currentfood.getPrice());
                txtPrice.setText(currentfood.getPrice());
                txtName.setText(currentfood.getName());
                //Lấy đc id nhưng chưa biết map qua category như nào :(
                txtCate.setText(currentfood.getMenuId());

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