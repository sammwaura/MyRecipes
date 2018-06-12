package com.meshsami27.myrecipes;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MealDBService {
    private static OkHttpClient client = new OkHttpClient();

    public static void findCategory(Callback callback){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEALDB_BASE_URL).newBuilder();

        String url = urlBuilder.build().toString();
    }

    public static void findMeals(Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEALDB_BASE_URL).newBuilder();

        String url = urlBuilder.build().toString();

    }

    public static void findIngredients(Callback callback) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MEALDB_BASE_URL).newBuilder();

        String url = urlBuilder.build().toString();



        Request request = new Request.Builder()
                .url(url)

                .build();

            Call call = client.newCall(request);

            call.enqueue(callback);

    }

}
