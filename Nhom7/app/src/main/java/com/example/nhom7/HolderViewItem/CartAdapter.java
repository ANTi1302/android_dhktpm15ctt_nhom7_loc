package com.example.nhom7.HolderViewItem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.Model.Order;
import com.example.nhom7.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtCartName,txtCartPrice,txtCount;
    private ItemClickListen itemClickListen;
    public CartViewHolder(View itemView) {
        super(itemView);
        txtCartName=itemView.findViewById(R.id.txtNameCart);
        txtCartPrice=itemView.findViewById(R.id.txtPriceCart);
        txtCount=itemView.findViewById(R.id.txtCountCart);

        itemView.setOnClickListener(this);
    }

    public void setTxtCartName(TextView txtCartName) {
        this.txtCartName = txtCartName;
    }

    @Override
    public void onClick(View view) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Order> listData= new ArrayList<>();
    private Context context;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public CartViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View itemview=inflater.inflate(R.layout.giohang,parent,false);
        return new CartViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        Locale local=new Locale("en","US");
        NumberFormat fmt=NumberFormat.getCurrencyInstance(local);
        int price=(Integer.parseInt(listData.get(position).getPrice()))* (Integer.parseInt(listData.get(position).getQuality()));
        holder.txtCartPrice.setText(fmt.format(price));

        holder.txtCartName.setText(listData.get(position).getProductName());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
