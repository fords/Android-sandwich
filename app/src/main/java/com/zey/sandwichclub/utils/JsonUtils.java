package com.zey.sandwichclub.utils;

import com.zey.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    private static final String NAME_KEY = "name";
    private static final String MAIN_NAME_KEY = "mainName";
    private static final String ALSO_KNOWN_AS_NAME_KEY = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN_KEY = "placeOfOrigin";
    private static final String DESCRIPTION_KEY = "description";
    private static final String IMAGE_KEY = "image";
    private static final String INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json)
    {
        String name = "";
        String placeOfOrigin = "";
        String description = "";
        String image = "";
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject names = sandwich.getJSONObject(NAME_KEY);

            name = parseSandwichName(names);
            alsoKnownAs = parseSandwichAlsoKnownAs(names);
            placeOfOrigin = parseSandwichPlaceOfOrigin(sandwich);
            description = parseSandwichDescription(sandwich);
            image = parseSandwichImage(sandwich);
            ingredients = parseSandwichIngredients(sandwich);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Sandwich(name, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static String parseSandwichName(JSONObject names) {
        String mainName = "";
        try {
            mainName = names.getString(MAIN_NAME_KEY);
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return mainName;
    }

    private static List<String> parseSandwichAlsoKnownAs(JSONObject names) {
        List<String> alsoKnownAs = new ArrayList<>();
        JSONArray jsonArray;
        try{
            jsonArray = names.getJSONArray(ALSO_KNOWN_AS_NAME_KEY);
            if( jsonArray != null) {
                for( int i = 0; i < jsonArray.length(); i++) {
                    alsoKnownAs.add(jsonArray.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return alsoKnownAs;
    }

    private static String parseSandwichPlaceOfOrigin(JSONObject sandwich) {
        String placeOfOrigin = "";
        try {
            placeOfOrigin = sandwich.getString(PLACE_OF_ORIGIN_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return placeOfOrigin;
    }

    private static String parseSandwichDescription(JSONObject sandwich) {
        String description = "";
        try {
            description = sandwich.getString(DESCRIPTION_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return description;
    }

    private static String parseSandwichImage(JSONObject sandwich) {
        String image = "";
        try {
            image = sandwich.getString(IMAGE_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }

    private static List<String> parseSandwichIngredients(JSONObject sandwich) {
        List<String> ingredients = new ArrayList<>();
        JSONArray jsonArray;
        try{
            jsonArray =  sandwich.getJSONArray(INGREDIENTS_KEY);
            if(jsonArray != null) {
                for( int i = 0; i < jsonArray.length(); i++) {
                    ingredients.add(jsonArray.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredients;
    }
}
