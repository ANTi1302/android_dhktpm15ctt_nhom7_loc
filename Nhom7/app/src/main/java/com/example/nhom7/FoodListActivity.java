package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom7.HolderViewItem.FoodViewOfListHolder;
import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.Model.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodListActivity extends AppCompatActivity {
    private ImageView img1, img2, img3, img4;
    private TextView category_name;
    private FirebaseDatabase database;
    private DatabaseReference food;
    String category="";
    String name_category="";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Food, FoodViewOfListHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        //Init Firebase
        database=FirebaseDatabase.getInstance();
        food=database.getReference("Food");

        //get id for view
        category_name= findViewById(R.id.textView12);

        recyclerView=findViewById(R.id.recycler_listfood);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Get intent here
        if (getIntent()!=null){
            category=getIntent().getStringExtra("CategoryId");
            name_category=getIntent().getStringExtra("CategoryName");
            category_name.setText(name_category);

        }if (!category.isEmpty()&&category!=null){
            loadListFood(category);
        }

        //set activity view
        img1 = (ImageView) findViewById(R.id.imageView7);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        img2 = (ImageView) findViewById(R.id.imageView9);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListActivity.this, MainActivity11.class);
                startActivity(intent);
            }
        });
        img3 = (ImageView) findViewById(R.id.imageView5);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodListActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadListFood(String category) {
        adapter=new FirebaseRecyclerAdapter<Food, FoodViewOfListHolder>(Food.class,R.layout.item_food,
                FoodViewOfListHolder.class,food.orderByChild("MenuId").equalTo(category)) { //like select *from where categoryId=menuId
            @Override
            protected void populateViewHolder(FoodViewOfListHolder foodViewHolder, Food food, int i) {

                foodViewHolder.txtFoodName.setText(food.getName());
                foodViewHolder.txtDecr.setText(food.getDecription());
                foodViewHolder.txtPrice.setText(food.getPrice());
                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(foodViewHolder.imageFoodView);

                final Food local=food;
                foodViewHolder.setItemClickListen(new ItemClickListen() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodListActivity.this, "" + local.getName(), Toast.LENGTH_LONG).show();
                        Intent foodList=new Intent(FoodListActivity.this,FoodDetailActivity.class);
                        foodList.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }
}