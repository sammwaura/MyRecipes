package com.meshsami27.myrecipes.ui;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.meshsami27.myrecipes.R;
import com.meshsami27.myrecipes.adapters.MyRecipesListAdapter;
import com.meshsami27.myrecipes.models.Recipe;
import com.meshsami27.myrecipes.services.MealDBService;
import com.squareup.picasso.Picasso;


import java.io.IOException;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailRecipeActivity extends AppCompatActivity {
    @BindView(R.id.mealNameTextView)
    TextView mMealNameTextView;
    @BindView(R.id.categoryTextView)
    TextView mCategoryTextView;
    @BindView(R.id.instructionsTextView)
    TextView mInstrutionsTextView;
    @BindView(R.id.imageView)
    ImageView mImageView;

    private MyRecipesListAdapter mAdapter;

    Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_recipe);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        String id = intent.getStringExtra("id");

        getRecipe(id);
    }



    private void getRecipe(String id) {


        final MealDBService mealDBService = new MealDBService();

        mealDBService.filterMealRecipe(id, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mRecipe = mealDBService.processRecipeResults(response);

                DetailRecipeActivity.this.runOnUiThread(() -> {
                    mMealNameTextView.setText(mRecipe.getStrMeal());
                    mCategoryTextView.setText(mRecipe.getStrCategory());
                    mInstrutionsTextView.setText(mRecipe.getStrInstructions());
                    Picasso.with(DetailRecipeActivity.this).load(mRecipe.getStrMealThumb()).into(mImageView);
                });
            }
        });
    }
}
