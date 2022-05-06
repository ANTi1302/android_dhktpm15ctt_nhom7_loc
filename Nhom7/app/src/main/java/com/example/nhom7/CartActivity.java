package com.example.nhom7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom7.DB.Database;
import com.example.nhom7.HolderViewItem.CartAdapter;
import com.example.nhom7.HolderViewItem.FoodViewOfListHolder;
import com.example.nhom7.Model.Food;
import com.example.nhom7.Model.Order;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CartActivity extends AppCompatActivity {
    private ImageView img1;
    private Button btn1,btnDelete;
    private TextView txtTotalPrice;
    private Button btnOrder;

    private FirebaseDatabase database;
    private DatabaseReference request;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private List<Order> carts=new ArrayList<>();
    private CartAdapter adapter;
    private String foodId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //Init Firebase
        database=FirebaseDatabase.getInstance();
        request=database.getReference("Requests");


        img1 = (ImageView) findViewById(R.id.img_back);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn1 = findViewById(R.id.btnOrder);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this,"Đặt món thành công",Toast.LENGTH_SHORT).show();
            }
        });



        //get id for view
        txtTotalPrice= findViewById(R.id.price_order);
        btnOrder= findViewById(R.id.btnOrder);
        btnDelete=findViewById(R.id.btnREmoveOrder);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Database(getBaseContext()).deleteToCart();
                Toast.makeText(CartActivity.this,"Xóa đơn hàng thành công",Toast.LENGTH_SHORT).show();
                loadListFood();
            }
        });

        recyclerView=findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadListFood();
    }

    private void loadListFood() {

        carts=new Database(this).getCart();
        adapter= new CartAdapter(carts,this);
        recyclerView.setAdapter(adapter);

        //Category total price
        int total = 0;
        for (Order order:carts)
            total+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getQuality()))+55;
        Locale local=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(local);
        txtTotalPrice.setText(fmt.format(total));
    }

}