package com.meshsami27.myrecipes.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meshsami27.myrecipes.models.Meal;

import com.meshsami27.myrecipes.Constants;
import com.meshsami27.myrecipes.R;
import com.meshsami27.myrecipes.ui.MainActivity;
import com.meshsami27.myrecipes.ui.MealListActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FirebaseCategoryMealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;


    public FirebaseCategoryMealViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMeal(Meal meal) {
        ImageView mMealImageView = (ImageView) mView.findViewById(R.id.mealImageView);
        TextView mMealNameTextView = (TextView) mView.findViewById(R.id.mealNameTextView);
        Picasso.with(mContext).load(meal.getStrMealThumb()).into(mMealImageView);
    }

    @Override
    public void onClick(View view){
        final List<Meal> meals = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    meals.add(snapshot.getValue(Meal.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MealListActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("meal", Parcels.wrap(meals));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
