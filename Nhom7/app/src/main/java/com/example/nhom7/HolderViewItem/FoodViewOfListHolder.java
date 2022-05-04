package com.example.nhom7.HolderViewItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.R;

public class FoodViewOfListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtFoodName,txtDecr,txtPrice;
    public ImageView imageFoodView;
    private ItemClickListen itemClickListen;
    public FoodViewOfListHolder(View itemView) {
        super(itemView);
        txtFoodName=itemView.findViewById(R.id.name_food);
        txtDecr=itemView.findViewById(R.id.decr_food);
        txtPrice=itemView.findViewById(R.id.price_food);
        imageFoodView=itemView.findViewById(R.id.imd_food);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListen(ItemClickListen itemClickListen) {
        this.itemClickListen = itemClickListen;
    }

    @Override
    public void onClick(View view) {
        itemClickListen.OnClick(view,getAdapterPosition(),false);
    }
}
