package com.example.nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom7.Model.Food;
import com.example.nhom7.R;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter1 extends RecyclerView.Adapter<FoodAdapter1.FoodViewHo> implements Filterable {
    private List<Food> foodList;
    private List<Food> mfoodLisFull;

    public FoodAdapter1(List<Food> foodList) {
        this.foodList = foodList;
        this.mfoodLisFull= new ArrayList<>(foodList);
    }
    public void setFilteredList(List<Food> filteredList){
        this.foodList=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam,parent,false);
        return new FoodViewHo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHo holder, int position) {

        Food food=foodList.get(position);
        if(food==null){
            return;
        }
        holder.hinh.setImageResource(Integer.parseInt(food.getImage().toString()));
        holder.tenMon.setText(food.getName());
        holder.gia.setText(food.getPrice());
        holder.decr_food.setText(food.getDecription());



    }

    @Override
    public int getItemCount() {
        if (foodList!=null){
            return foodList.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Food> filteredList = new ArrayList<>();


                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(mfoodLisFull);
                } else {
                    String strSearch= constraint.toString().toLowerCase().trim();

                    for (Food food: mfoodLisFull ){
                        if(food.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            filteredList.add(food);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values=filteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                foodList.clear();
                foodList.addAll((List)results.values);
                notifyDataSetChanged();

            }
        };
    }


    public class FoodViewHo extends RecyclerView.ViewHolder{
        private Context context;
        private int layout;
        private ArrayList<Food> arr;
        private TextView tenMon,gia,decr_food;
        private ImageView hinh;
        public FoodViewHo(@NonNull View itemView) {
            super(itemView);
            tenMon=(TextView) itemView.findViewById(R.id.txtName);
            hinh = (ImageView) itemView.findViewById(R.id.image_food);
            decr_food=(TextView) itemView.findViewById(R.id.textView11);
        }
    }




}
