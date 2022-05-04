package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nhom7.HolderViewItem.FoodViewHolder;
import com.example.nhom7.HolderViewItem.MenuViewHolder;
import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.Model.Category;
import com.example.nhom7.Model.Food;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {
    private ImageView img1, img2, img3, img4;

    private FirebaseDatabase database;
    private DatabaseReference category,food;
    private RecyclerView recycler_menu,recycler_food;
    private RecyclerView.LayoutManager gridLayoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter_menu;
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Init Firebase
        database=FirebaseDatabase.getInstance();
        category=database.getReference("Category");
        food=database.getReference("Food");


        img1 = (ImageView) findViewById(R.id.imageView8);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        img2 = (ImageView) findViewById(R.id.imgTim);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity11.class);
                startActivity(intent);
            }
        });
        img3 = (ImageView) findViewById(R.id.imageView11);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity9.class);
                startActivity(intent);
            }
        });
        img4 = (ImageView) findViewById(R.id.imageView3);
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });


        //Load menu
        recycler_menu=findViewById(R.id.eycycler_menu);
        recycler_menu.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,LinearLayoutManager.HORIZONTAL,false);
        recycler_menu.setHorizontalScrollBarEnabled(false);
        recycler_menu.setLayoutManager(gridLayoutManager);

        loadMenu();

        //Load food
        recycler_food=findViewById(R.id.recycler_food);
        recycler_food.setHasFixedSize(true);
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),1,LinearLayoutManager.HORIZONTAL,false);
        recycler_food.setHorizontalScrollBarEnabled(false);
        recycler_food.setLayoutManager(gridLayoutManager);

        loadFood();
    }

    private void loadFood() {
        adapter_food=new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,R.layout.iteam,FoodViewHolder.class,food) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                foodViewHolder.txtFoodName.setText(food.getName());

                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(foodViewHolder.imageFoodView);
                final Food clickItem = food;

                foodViewHolder.setItemClickListen(new ItemClickListen() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(HomeActivity.this, "" + clickItem.getName(), Toast.LENGTH_LONG).show();
                        Intent foodList=new Intent(HomeActivity.this,FoodDetailActivity.class);
                        foodList.putExtra("FoodId",adapter_food.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }
            };
            recycler_food.setAdapter(adapter_food);
        }


    private void loadMenu() {
        adapter_menu=new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.item_menu,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category model, int position) {
                viewHolder.txtMenuName.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.imgaView);
                final Category clickItem=model;
                viewHolder.setItemClickListen(new ItemClickListen() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(HomeActivity.this,""+clickItem.getName(),Toast.LENGTH_LONG).show();
                        Intent foodList=new Intent(HomeActivity.this,FoodListActivity.class);
                        Intent foodListDetail=new Intent(HomeActivity.this,FoodDetailActivity.class);
                        foodList.putExtra("CategoryId",adapter_menu.getRef(position).getKey());
                        startActivity(foodList);
                    }
                });
            }
        };
        recycler_menu.setAdapter(adapter_menu);
    }
}