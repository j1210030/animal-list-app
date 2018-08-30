package com.android.accenture.knowme;


import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;

/**
 * Created by ykashiwagi on 7/12/17.
 */

public class FirebaseDatabaseRequester {
    FirebaseDatabase database;
    private DatabaseReference mDatabase;
    ArrayList<Animal> animalList;

    FirebaseDatabaseRequester(){
        database = FirebaseDatabase.getInstance();
        // Create a reference to the file you want to download
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void syncFirebaseDatabaseAnimalList(){

        database = FirebaseDatabase.getInstance();

        // Create a reference to the file you want to download
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // mDatabase.child("animals").child("listItems").getDatabase();

        mDatabase.child("animals").child("listItems").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot DataSnapshot){
                GenericTypeIndicator<ArrayList<Animal>> t = new GenericTypeIndicator<ArrayList<Animal>>() {};
                animalList = DataSnapshot.getValue(t);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }


        });

    }

    public ArrayList<Animal> getAnimalList(){
        return animalList;
    }

    public ArrayList<Animal> convertJsonToAnimalList(JSONArray animalsArray){
        ArrayList<Animal> animalList = new ArrayList<>();
        return animalList;
    }


    public JSONArray convertDataSnapshotToJson(DataSnapshot DataSnapshot){
        JSONArray listItemsArray = null;
        return listItemsArray;

    }
}
