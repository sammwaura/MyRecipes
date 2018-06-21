package com.meshsami27.myrecipes.adapters;


import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import com.meshsami27.myrecipes.R;
import com.meshsami27.myrecipes.models.Meal;
import com.meshsami27.myrecipes.models.Recipe;

import com.meshsami27.myrecipes.ui.DetailRecipeActivity;
import com.squareup.picasso.Picasso;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MyRecipesListAdapter extends RecyclerView.Adapter<MyRecipesListAdapter.MyRecipesViewHolder> {

    private List<Meal> mMeals = new ArrayList<>();

    Recipe mRecipes;

    private Context mContext;

    public MyRecipesListAdapter(Context context, List<Meal> meals, Recipe recipe){

        mContext = context;

       mMeals  = meals;

       mRecipes = recipe;
    }

    @Override
    public MyRecipesListAdapter.MyRecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item, parent, false);

        MyRecipesViewHolder viewHolder = new MyRecipesViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyRecipesListAdapter.MyRecipesViewHolder holder, int position) {

        holder.bindMeal(mMeals.get(position));


         }

    @Override
    public int getItemCount() {
        return mMeals.size();


    }


    public class MyRecipesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.mealImageView)
        ImageView mMealImageView;

        @BindView(R.id.mealNameTextView)
        TextView mMealNameTextView;

        private Context mContext;

        public MyRecipesViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();

        }

        public void bindMeal(Meal meal){
            Picasso.with(mContext).load(meal.getStrMealThumb()).into(mMealImageView);
            mMealNameTextView.setText(meal.getStrMeal());
        }



        @Override
        public void onClick(View v) {
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, DetailRecipeActivity.class);

                intent.putExtra("position", itemPosition);

                intent.putExtra("id", Parcels.wrap(mRecipes));

                mContext.startActivity(intent);

            }
        }
        }





