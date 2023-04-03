package com.example.wishmart;

import static android.app.Activity.RESULT_OK;

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
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class AdminAddProductFragment extends Fragment {
  TextInputLayout productName, productPrice, discount,description, description_details;
  Spinner selection;
  ImageView productImage, image1,image2, image3;
  Button btnAdd;
  Uri ImageUri ,ImgUri1;
 private static int PICK_IMAGE_REQUEST = 1;
    private static int PICK_IMAGE_REQUEST2 = 2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_admin_add_product,container,false);
       productName= view.findViewById(R.id.product_name);
        productPrice=view.findViewById(R.id.product_price);
        discount=view.findViewById(R.id.discount);
        description=view.findViewById(R.id.product_description);
        description_details= view.findViewById(R.id.descriptionDetail);
        selection= view.findViewById(R.id.product_category);
        productImage= view.findViewById(R.id.product_image);
        image1= view.findViewById(R.id.product_sample1);
        image2= view.findViewById(R.id.product_sample2);
        image3= view.findViewById(R.id.product_sample3);
        btnAdd= view.findViewById(R.id.register_button);

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void openFileChooser() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            ImageUri = data.getData();
           // productImage =productImage.findViewById(R.id.product_image);
            // Set a unique URI for each ImageView
            Uri uri1 = ImageUri;
          //  Uri uri2 = Uri.parse("file://" + getRealPathFromURI(ImageUri));
            Uri uri3 = Uri.parse("file://" + getRealPathFromURI(ImageUri));
            Uri uri4 = Uri.parse("file://" + getRealPathFromURI(ImageUri));

            // Load the image into each ImageView using its unique URI
            Picasso.get().load(uri1).into(productImage);
           // Picasso.get().load(uri2).into(image1);
            Picasso.get().load(uri3).into(image2);
            Picasso.get().load(uri4).into(image3);

        } else if (requestCode == PICK_IMAGE_REQUEST2    && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            ImgUri1 = data.getData();
            // productImage =productImage.findViewById(R.id.product_image);
            // Set a unique URI for each ImageView
            //Uri uri2 = ImgUri1;
            Picasso.get().load(ImgUri1).into(image1);
        }
    }
    // get path metho to allow different uri path
    private String getRealPathFromURI(Uri contentUri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContext().getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}