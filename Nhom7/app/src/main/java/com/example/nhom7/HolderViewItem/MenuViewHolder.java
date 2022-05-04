package com.example.nhom7.HolderViewItem;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom7.Interface.ItemClickListen;
import com.example.nhom7.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
   public TextView txtMenuName;
  public   ImageView imgaView;
    private ItemClickListen itemClickListen;
    public MenuViewHolder(View itemView) {
        super(itemView);
        txtMenuName=itemView.findViewById(R.id.textView29);
        imgaView=itemView.findViewById(R.id.imageView13);

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
