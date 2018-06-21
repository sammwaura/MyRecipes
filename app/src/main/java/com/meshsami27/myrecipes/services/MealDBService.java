package com.meshsami27.myrecipes.services;

import com.meshsami27.myrecipes.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.meshsami27.myrecipes.models.Meal;
import com.meshsami27.myrecipes.models.Recipe;

import java.io.IOException;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MealDBService {

    private static OkHttpClient client = new OkHttpClient();

    public static void filterCategory(String category, Callback callback){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.CATEGORYDB_BASE_URL).newBuilder();

        urlBuilder.addQueryParameter(Constants.CATEGORYDB_QUERY_PARAMS, category);

        String url = urlBuilder.build().toString();


    Request request = new Request.Builder()
                .url(url)

                .build();

            Call call = client.newCall(request);

            call.enqueue(callback);

    }

    public static void filterMealRecipe(String id, Callback callback){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.RECIPEDB_BASE_URL).newBuilder();

        urlBuilder.addQueryParameter(Constants.RECIPEDB_QUERY_PARAMS, id);

        String url = urlBuilder.build().toString();


        Request request = new Request.Builder()
                .url(url)

                .build();

        Call call = client.newCall(request);

        call.enqueue(callback);

    }

    public List<Meal> processMealResults(Response response) {
        List<Meal> meals = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {

                JSONObject mealdbJSON = new JSONObject(jsonData);

                JSONArray mealsJSON = mealdbJSON.getJSONArray("meals");

//                Log.v("sfdfgghjhjghgjfhdg:", "arrray meals JSON");

                Type collectionType = new TypeToken<List<Meal>>() {}.getType();

                Gson gson= new GsonBuilder().create();

                meals = gson.fromJson(mealsJSON.toString(), collectionType);

            }

        } catch (JSONException | NullPointerException | IOException e) {
            e.printStackTrace();
        }

        return meals;
    }



    public List<Recipe> processRecipeResults(Response response) {
        List<Recipe> recipes = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if(response.isSuccessful()) {

                JSONObject mealdbJSON = new JSONObject(jsonData);

                JSONArray recipeJSON = mealdbJSON.getJSONArray("recipes");

                Type collectionType = new TypeToken<List<Recipe>>() {}.getType();

                Gson gson= new GsonBuilder().create();

                recipes = gson.fromJson(recipes.toString(), collectionType);

            }

        } catch (JSONException | NullPointerException | IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }
}


