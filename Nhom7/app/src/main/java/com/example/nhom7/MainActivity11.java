package com.example.nhom7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom7.HolderViewItem.FoodViewHolder;
import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.Model.Food;
import com.example.nhom7.Adapter.FoodAdapter;
import com.example.nhom7.Adapter.FoodAdapter1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;



public class MainActivity11 extends AppCompatActivity {
    private ImageView img1, img2, img3, img4;

    //  private FoodAdapter foodAdapter;
    private FoodAdapter1 foodAdapter1;
    private RecyclerView recyclerView;
    private DatabaseReference food;
    private  FoodAdapter1 adapter;
    private RecyclerView.LayoutManager gridLayoutManager;

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter_food, adapter_food1;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    // String userID = user.getUid();

    private FirebaseDatabase database;
    ArrayList<String> suggestList = new ArrayList<>();
    private List<Food> foodList;


    private SearchView searchView;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main11);
        foodList = new ArrayList<Food>() ;
        database = FirebaseDatabase.getInstance();
        food = database.getReference("Food");
        //load food
        recyclerView = findViewById(R.id.recyview);


        recyclerView = findViewById(R.id.recyview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        loadFood();

        searchView = findViewById(R.id.searchview);
        searchView.clearFocus();

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
                Intent intent = new Intent(MainActivity11.this, AccountActivity.class);
                startActivity(intent);
            }


        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //   loadFoodsearch(query);
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //   loadFoodsearch(newText);
                // filterList(newText);
                firebaseSearch(newText);
                return false;
            }
        });

    }

    private void filterList(String newText) {
        List<Food> foodList2=new ArrayList<Food>();
        for (Food food: foodList){
            if(food.getName().toLowerCase().contains(newText.toLowerCase())){
                foodList2.add(food);
            }

        }
        if(foodList2.isEmpty()){
            Toast.makeText(this,"No data found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter.setFilteredList(foodList2);
        }
    }


    private void search(String s) {
        adapter_food = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.iteam, FoodViewHolder.class, food) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                for (Food food1 : foodList) {
                    if (food.getName().toLowerCase().contains(s.toLowerCase())) {
                        adapter_food1.toString();

                    }
                }
            }
        };


        recyclerView.setAdapter(adapter_food);

    }

    private void loadFood() {

        adapter_food = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.iteam, FoodViewHolder.class, food) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                foodViewHolder.txtFoodName.setText(food.getName());

                Picasso.with(getBaseContext()).load(food.getImage())
                        .into(foodViewHolder.imageFoodView);
                final Food clickItem = food;

                foodViewHolder.setItemClickListen(new ItemClickListen() {
                    @Override
                    public void OnClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(MainActivity11.this, "" + clickItem.getName(), Toast.LENGTH_LONG).show();
                        Intent foodList = new Intent(MainActivity11.this, FoodDetailActivity.class);
                        foodList.putExtra("FoodId", adapter_food.getRef(position).getKey());

                        startActivity(foodList);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter_food);

    }

    private void loadFoodsearch(String s) {

        adapter_food = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.iteam, FoodViewHolder.class, food) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                if (food.getName().toLowerCase().contains(s)) {
                    foodViewHolder.txtFoodName.setText(food.getName());
                    Picasso.with(getBaseContext()).load(food.getImage())
                            .into(foodViewHolder.imageFoodView);
                    final Food clickItem = food;

                    foodViewHolder.setItemClickListen(new ItemClickListen() {
                        @Override
                        public void OnClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(MainActivity11.this, "" + clickItem.getName(), Toast.LENGTH_LONG).show();
                            Intent foodList = new Intent(MainActivity11.this, FoodDetailActivity.class);
                            foodList.putExtra("FoodId", adapter_food.getRef(position).getKey());

                            startActivity(foodList);
                        }
                    });
                }


            }
        };
        recyclerView.setAdapter(adapter_food);

    }
    private void firebaseSearch(String search) {
        String quary= search.toLowerCase();
        Query firebaseSearchQuery = database.getReference().orderByChild("Name").startAt(quary+"\uf8ff");

        adapter_food = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.iteam, FoodViewHolder.class, food) {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                if (food.getName().toLowerCase().contains(quary)) {
                    foodViewHolder.txtFoodName.setText(food.getName());
                    Picasso.with(getBaseContext()).load(food.getImage())
                            .into(foodViewHolder.imageFoodView);
                    final Food clickItem = food;

                    foodViewHolder.setItemClickListen(new ItemClickListen() {
                        @Override
                        public void OnClick(View view, int position, boolean isLongClick) {
                            Toast.makeText(MainActivity11.this, "" + clickItem.getName(), Toast.LENGTH_LONG).show();
                            Intent foodList = new Intent(MainActivity11.this, FoodDetailActivity.class);
                            foodList.putExtra("FoodId", adapter_food.getRef(position).getKey());

                            startActivity(foodList);
                        }
                    });
                }


            }
        };
        recyclerView.setAdapter(adapter_food);
    }
}





