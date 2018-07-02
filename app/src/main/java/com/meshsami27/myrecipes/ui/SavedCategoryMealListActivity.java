package com.meshsami27.myrecipes.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.meshsami27.myrecipes.models.Meal;
import com.meshsami27.myrecipes.Constants;
import com.meshsami27.myrecipes.R;
import com.meshsami27.myrecipes.adapters.FirebaseCategoryMealViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedCategoryMealListActivity extends AppCompatActivity {
    private DatabaseReference mMealCategoryReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meal);
        ButterKnife.bind(this);

        mMealCategoryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORIES);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        mMealCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORIES);
                .child(uid);

        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Meal, FirebaseCategoryMealViewHolder>
                (Meal.class, R.layout.meal_list_item, FirebaseCategoryMealViewHolder.class, mMealCategoryReference) {

            @Override
            protected void populateViewHolder(FirebaseCategoryMealViewHolder viewHolder,
              Meal model, int position){
                viewHolder.bindMeal(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
