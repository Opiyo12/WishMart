package com.example.wishmart;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class AdminAddProductFragment extends Fragment {
  TextInputLayout productName, productPrice, discount,description, description_details;
  Spinner selection;
  ImageView productImage;
   RelativeLayout Imagechoose;
  Uri ImageUri ;
  ProgressDialog progressDialog;
    private FirebaseDatabase mdatabase;
    private DatabaseReference mref;
    private FirebaseStorage mstorage;
 private static int PICK_IMAGE_REQUEST = 1;



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_add_product, container, false);

        productName= view.findViewById(R.id.ItemName);
        productPrice= view.findViewById(R.id.ItemPrice);
        discount= view.findViewById(R.id.Itemdiscount);
        description= view.findViewById(R.id.ItemDesc);
        description_details= view.findViewById(R.id.ItemDetails);
        selection= view.findViewById(R.id.product_category);
        productImage= view.findViewById(R.id.ItemImage);
        Imagechoose= view.findViewById(R.id.ChooseImage);

        //Firebase database initialisation
        mdatabase= FirebaseDatabase.getInstance();
        mref= mdatabase.getReference().child("Uploads");
        mstorage= FirebaseStorage.getInstance();






        return view;
    }

}