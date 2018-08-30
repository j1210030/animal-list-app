package com.android.accenture.knowme;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.animalsfb.R;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ykashiwagi on 6/21/17.
 */

public class CustomAdapter extends ArrayAdapter<Animal> {

    // animal image
    ImageView animalImage;

    // animal name
    TextView animalName;

    // habitat
    TextView habitat;

    // foodtype
    ImageView foodtype;

    private LayoutInflater layoutInflater_;

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<Animal> objects) {
        super(context, textViewResourceId, objects);
        layoutInflater_ = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animal item = getItem(position);

        if (null == convertView) {
            convertView = layoutInflater_.inflate(R.layout.custom_layout, null);
        }

        initView(convertView);

        // animal image
        FirebaseStorageRequester firebaseStorageRequester = new FirebaseStorageRequester();
        String string = null;
        animalImage = firebaseStorageRequester.getImageViewFromFirebaseStorage(string, this.getContext(), animalImage);

        // firebaseDatabaseRequester.getAnimalList;
        // animal name
        animalName.setText(item.getName() + "(" + item.getScientific_name() + ")");
        FirebaseDatabaseRequester firebaseDatabaseRequester = new FirebaseDatabaseRequester();
        firebaseDatabaseRequester.syncFirebaseDatabaseAnimalList();

        // habitat
        habitat.setText(item.getHabitat());

        // foodtype
        try {
            InputStream istream = convertView.getResources().getAssets().open(item.getFoodtype());
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            foodtype.setImageBitmap(bitmap);

        } catch (IOException e) {
            Log.d("Assets","Error");

        }

        return convertView;
    }

    public void initView(View convertView){
        // animal image
        animalImage = (ImageView)convertView.findViewById(R.id.animal_image);

        // animal name
        animalName = (TextView)convertView.findViewById(R.id.animal_name);

        // habitat
        habitat = (TextView)convertView.findViewById(R.id.habitat);

        // foodtype
        foodtype = (ImageView)convertView.findViewById(R.id.foodtype);
    }
}