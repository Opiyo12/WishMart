package com.example.wishmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class productAdapter extends RecyclerView.Adapter<productAdapter.myViewHolder> {
    Context context;
    ArrayList<productModel> list;

    public productAdapter(Context context, ArrayList<productModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View v= LayoutInflater.from(parent.getContext()).
             inflate(R.layout.product_design, parent, false);

        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
    productModel model= list.get(position);
    holder.product_name.setText(model.getProductName());
    holder.price.setText(model.getPrice());
    holder.description.setText(model.getDescription());
    holder.discount.setText(model.getDiscount());
    holder.description_details.setText(model.getDescription());
    int imageUri ,imageUri1,imageUri2,imageUri3;
    imageUri= model.getProductImage();
    imageUri1= model.getImageSample1();
    imageUri2= model.getGetImageSample2();
    imageUri3= model.getGetImageSample3();

    Picasso.get().load(imageUri).into(holder.imageView);
    Picasso.get().load(imageUri1).into(holder.image1);
    Picasso.get().load(imageUri2).into(holder.image2);
    Picasso.get().load(imageUri3).into(holder.image3);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {
        TextView product_name, price, description, discount, description_details,category;
        ImageView imageView, image1, image2, image3;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
          product_name= itemView.findViewById(R.id.product_name);
          price= itemView.findViewById(R.id.product_price);
          description= itemView.findViewById(R.id.product_description);
          discount= itemView.findViewById(R.id.product_discount);
          description_details= itemView.findViewById(R.id.descriptionDetail);
          imageView=itemView.findViewById(R.id.product_image);
          image1= itemView.findViewById(R.id.product_sample1);
          image2= itemView.findViewById(R.id.product_sample2);
          image3= itemView.findViewById(R.id.product_sample3);
          category= itemView.findViewById(R.id.product_category);

        }
    }

}
