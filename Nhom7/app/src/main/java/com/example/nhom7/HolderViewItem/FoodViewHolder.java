package com.example.nhom7.HolderViewItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtFoodName,txtDecr,txtPrice;
    public ImageView imageFoodView;
    private ItemClickListen itemClickListen;
    public FoodViewHolder( View itemView) {
        super(itemView);
        txtFoodName=itemView.findViewById(R.id.txtName);
        imageFoodView=itemView.findViewById(R.id.image_food);

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
