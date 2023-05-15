package com.example.wishmart;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AdminViewProductFragment extends Fragment {
    TextInputEditText itemName, price, discount,description, details, category;
    AppCompatButton Uploadbutton;
    RelativeLayout PickImagebutton;
    ViewPager viewPager;
    Uri ImageUri;
    ArrayList<Uri> ChooseImageList;
    ArrayList<String> UrlsList;
    FirebaseFirestore firestore;
    StorageReference storagereference;
    FirebaseStorage mStorage;

    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_view_product, container,false);

        itemName= view.findViewById(R.id.product_name);
        price= view.findViewById(R.id.product_price);
        discount= view.findViewById(R.id.product_discount);
        description= view.findViewById(R.id.product_description);
        details= view.findViewById(R.id.ItemDetails);
        category= view.findViewById(R.id.category);
        PickImagebutton= view.findViewById(R.id.ChooseImage);
        viewPager= view.findViewById(R.id.viewPager);
//
//        //progressDialog= new ProgressDialog(this);
//        progressDialog.setTitle("Uploading Data");
//        progressDialog.setMessage("Please wait While Uploaing Your data...");

        // firebase Instance
        firestore = FirebaseFirestore.getInstance();
        mStorage = FirebaseStorage.getInstance();
        storagereference = mStorage.getReference();
        Uploadbutton = view.findViewById(R.id.UploadBtn);
        ChooseImageList= new ArrayList<>();
        UrlsList = new ArrayList<>();

        PickImagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //CheckPermission();
            }
        });




     return view;
    }

//    private void CheckPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(fragment_admin_view_product.this,
//                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(fragment_admin_view_product.this, new
//                        String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
//            } else {
//                PickImageFromgallry();
//            }
//
//        } else {
//            PickImageFromgallry();
//        }
//    }

}