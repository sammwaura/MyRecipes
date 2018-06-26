package com.meshsami27.myrecipes.ui;

import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.meshsami27.myrecipes.Constants;
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
//    @BindView(R.id.ingredient1TextView)
//    TextView mIngredient1View;
//    @BindView(R.id.ingredient2TextView)
//    TextView mIngredient2View;
//    @BindView(R.id.ingredient3TextView)
//    TextView mIngredient3View;
//    @BindView(R.id.ingredient4TextView)
//    TextView mIngredient4View;
//    @BindView(R.id.ingredient5TextView)
//    TextView mIngredient5View;
//    @BindView(R.id.ingredient6TextView)
//    TextView mIngredient6View;
//    @BindView(R.id.ingredient7TextView)
//    TextView mIngredient7View;
//    @BindView(R.id.ingredient8TextView)
//    TextView mIngredient8View;
//    @BindView(R.id.ingredient9TextView)
//    TextView mIngredient9View;
//    @BindView(R.id.ingredient10TextView)
//    TextView mIngredient10View;
//    @BindView(R.id.ingredient11TextView)
//    TextView mIngredient11View;
//    @BindView(R.id.ingredient12TextView)
//    TextView mIngredient12View;
//    @BindView(R.id.ingredient13TextView)
//    TextView mIngredient13View;
//    @BindView(R.id.ingredient14TextView)
//    TextView mIngredient14View;
//    @BindView(R.id.ingredient15TextView)
//    TextView mIngredient15View;
//    @BindView(R.id.ingredient16TextView)
//    TextView mIngredient16View;
//    @BindView(R.id.ingredient17TextView)
//    TextView mIngredient17View;
//    @BindView(R.id.ingredient18TextView)
//    TextView mIngredient18View;
//    @BindView(R.id.ingredient19TextView)
//    TextView mIngredient19View;
//    @BindView(R.id.ingredient20TextView)
//    TextView mIngredient20View;

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
//                    mIngredient1View.setText(mRecipe.getStrIngredient1());
//                    mIngredient2View.setText(mRecipe.getStrIngredient2());
//                    mIngredient3View.setText(mRecipe.getStrIngredient3());
//                    mIngredient4View.setText(mRecipe.getStrIngredient4());
//                    mIngredient5View.setText(mRecipe.getStrIngredient5());
//                    mIngredient6View.setText(mRecipe.getStrIngredient6());
//                    mIngredient7View.setText(mRecipe.getStrIngredient7());
//                    mIngredient8View.setText(mRecipe.getStrIngredient8());
//                    mIngredient9View.setText(mRecipe.getStrIngredient9());
//                    mIngredient10View.setText(mRecipe.getStrIngredient10());
//                    mIngredient11View.setText(mRecipe.getStrIngredient11());
//                    mIngredient12View.setText(mRecipe.getStrIngredient12());
//                    mIngredient13View.setText(mRecipe.getStrIngredient13());
//                    mIngredient14View.setText(mRecipe.getStrIngredient14());
//                    mIngredient15View.setText(mRecipe.getStrIngredient15());
////                    mIngredient16View.setText(mRecipe.getStrIngredient16());
////                    mIngredient17View.setText(mRecipe.getStrIngredient17());
////                    mIngredient18View.setText(mRecipe.getStrIngredient18());
////                    mIngredient19View.setText(mRecipe.getStrIngredient19());
////                    mIngredient20View.setText(mRecipe.getStrIngredient20());
                    Picasso.with(DetailRecipeActivity.this).load(mRecipe.getStrMealThumb()).into(mImageView);

                    });
            }
        });
    }
}
