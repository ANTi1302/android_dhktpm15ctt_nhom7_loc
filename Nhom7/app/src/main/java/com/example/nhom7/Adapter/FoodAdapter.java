package com.example.nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom7.Model.Food;
import com.example.nhom7.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> ListFood;
    private ArrayList<Food> arr;
    private TextView tenMon,gia,decr_food;
    private ImageView hinh;



    public FoodAdapter(Context context, int layout, List<Food> listFood) {
        this.context = context;
        this.layout = layout;
        ListFood = listFood;

    }

    @Override
    public int getCount() {
        return  ListFood.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);
        tenMon=(TextView) view.findViewById(R.id.name_food1);
        hinh = (ImageView) view.findViewById(R.id.imd_food1);
        gia=(TextView) view.findViewById(R.id.price_food1);
        decr_food=(TextView) view.findViewById(R.id.decr_food1);
        final Food food = ListFood.get(position);
        tenMon.setText(food.getName());
        gia.setText(food.getPrice());
        decr_food.setText(food.getDecription());
        Picasso.with(context).load(food.getImage()).into(hinh);

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.item_animation_from_left);

        animation.setDuration(1500);
        view.startAnimation(animation);

        return view;
    }


    public void sortFood(String a) {

        a=a.toLowerCase();
        int k=0;
        for (int i=0; i<arr.size();i++){
            Food f = arr.get(i);
            String ten = f.getName().toLowerCase();
            if (ten.indexOf(a)>=0){
                arr.set(i, arr.get(k));
                arr.set(k,f);
                k++;
            }
            notifyDataSetChanged();

        }
    }
    public void setFilteredList(ArrayList<Food> filteredList) {
        arr = filteredList;
        notifyDataSetChanged();
    }
}
