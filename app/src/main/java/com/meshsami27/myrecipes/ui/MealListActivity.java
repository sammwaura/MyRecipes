package com.meshsami27.myrecipes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;


import com.meshsami27.myrecipes.R;
import com.meshsami27.myrecipes.adapters.MyRecipesListAdapter;
import com.meshsami27.myrecipes.models.Meal;
import com.meshsami27.myrecipes.models.Recipe;
import com.meshsami27.myrecipes.services.MealDBService;
import com.squareup.picasso.Picasso;


import org.parceler.Parcels;

import android.content.SharedPreferences;
import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListActivity extends AppCompatActivity {

    public static final String TAG = MealListActivity.class.getSimpleName();

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MyRecipesListAdapter mAdapter;

    private List<Meal> mMeals = new ArrayList<>();

    Recipe mRecipe;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String  mRecentCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        String category = intent.getStringExtra("category");

        getMeals(category);

        mSharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this);
        mRecentCategory = mSharedPreferences.getString(com.meshsami27.myrecipes.Constants.PREFERENCES_CATEGORY_KEY, null);
        android.util.Log.d("Shared Pref Category", mRecentCategory);


    }

    private void getMeals(String meals) {

        final MealDBService mealDBService = new MealDBService();

        mealDBService.filterCategory(meals, new Callback() {

            @Override

            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mMeals = mealDBService.processMealResults(response);

                MealListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new MyRecipesListAdapter(getApplicationContext(), mMeals, mRecipe);

                        mRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);

                        mRecyclerView.setLayoutManager(layoutManager);

                        mRecyclerView.setHasFixedSize(true);

                    }
                });

            }

        });

    }

}



