package com.android.accenture.knowme;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.R.attr.bitmap;

/**
 * Created by ykashiwagi on 7/12/17.
 */

public class FirebaseStorageRequester {
    FirebaseStorage storage;
    FirebaseStorageRequester(){
        storage = FirebaseStorage.getInstance();
    }

    public ImageView getImageViewFromFirebaseStorage(String string, Context customAdapter, ImageView imageView){

        storage = FirebaseStorage.getInstance();

        // Create a reference to the file you want to download
        StorageReference storageRef = storage.getReferenceFromUrl("gs://animalsfb-599.appspot.com");
        StorageReference cowRef = storageRef.child("cow.jpg");

        // Load the image using Glide
        Glide.with(customAdapter)
                .using(new FirebaseImageLoader())
                .load(cowRef)
                .into(imageView);

        return imageView;

    }

}
