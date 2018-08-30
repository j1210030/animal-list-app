package com.android.accenture.knowme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ykashiwagi on 6/26/17.
 */

public class JsonParser  {

    public JSONArray getParsedJSONArray(MainActivity mainActivity){
        // 1.Read JSON file from assets folder and convert to Java string type
        String jsondata = "";

        try {
            InputStream is = mainActivity.getResources().getAssets().open("animals.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            while (true){
                String str = br.readLine();

                if(str == null){
                    break;
                }else{
                    jsondata = jsondata + str;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2.Convert Java string type to JSONArray
        JSONArray listItemsArray = null;

        try {
            JSONObject rootObject = new JSONObject(jsondata);
            JSONObject animals = rootObject.getJSONObject("animals");
            listItemsArray = animals.getJSONArray("listItems");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listItemsArray;

    }

    public ArrayList<Animal> getConvertedAnimalList(JSONArray animalsArray) {
        // 3.Convert JSONArray to ArrayList<Animal>
        ArrayList<Animal> animalList = new ArrayList<>();

        if (animalsArray != null) {
            for (int i=0;i<animalsArray.length();i++){

                try {
                    Animal animal = new Animal();

                    animal.setThumbnail(animalsArray.getJSONObject(i).getString("thumbnail"));
                    animal.setName(animalsArray.getJSONObject(i).getString("name"));
                    animal.setScientific_name(animalsArray.getJSONObject(i).getString("scientific_name"));
                    animal.setHabitat(animalsArray.getJSONObject(i).getString("habitat"));
                    animal.setFoodtype(animalsArray.getJSONObject(i).getString("foodtype"));

                    animalList.add(animal);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return animalList;
    }
}
