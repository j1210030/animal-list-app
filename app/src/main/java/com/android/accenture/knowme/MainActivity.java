package com.android.accenture.knowme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.animalsfb.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.JSONArray;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView animalListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabaseRequester firebaseDatabaseRequester = new FirebaseDatabaseRequester();

        FirebaseStorageRequester firebaseStorageRequester = new FirebaseStorageRequester();

        FirebaseNotificationReceiver firebaseNotificationReceiver = new FirebaseNotificationReceiver();

        // get ListView
        animalListView = (ListView) findViewById(R.id.animal_listView);

        // Parse processing
        JsonParser jsonParser = new JsonParser();
        JSONArray animalsArray = jsonParser.getParsedJSONArray(this);

        // Convert JSONArray to ArrayList<Animal>
        ArrayList<Animal> objects = jsonParser.getConvertedAnimalList(animalsArray);
        // ArrayList<Animal> objects = firebaseDatabaseRequester.getAnimalList();

        CustomAdapter customAdapater = new CustomAdapter(this, 0, objects);
        animalListView.setAdapter(customAdapater);


    }

}