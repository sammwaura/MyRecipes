package com.meshsami27.myrecipes.ui;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.meshsami27.myrecipes.Constants;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.meshsami27.myrecipes.R;



import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedCategoryReference;

    private ValueEventListener mSearchedCategoryReferenceListener;

    @BindView(R.id.categoryEditText)
    EditText mCategoryEditText;
    @BindView(R.id.findCategoryButton)
    Button mFindCategoryButton;
    @BindView(R.id.savedCategoryMealButton)
    Button mSavedCategoryMealButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_CATEGORY);
        mSearchedCategoryReferenceListener = mSearchedCategoryReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {//something changed!
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    String category = categorySnapshot.getValue().toString();
                    Log.d("Category updated", "category:" + category);//log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //update UI here if error occurred.
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindCategoryButton.setOnClickListener(this);
        mSavedCategoryMealButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                display welcome message
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else  {

                }
            }
        };

    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v == mFindCategoryButton) {
            String category = mCategoryEditText.getText().toString();

            saveCategoryToFirebase(category);

            Intent intent = new Intent(MainActivity.this, MealListActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
        if (v == mSavedCategoryMealButton) {
            Intent intent = new Intent(MainActivity.this, SavedCategoryMealListActivity.class);
            startActivity(intent);
        }
    }

        public void saveCategoryToFirebase(String Category) {
            mSearchedCategoryReference.push().setValue(Category);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedCategoryReference.removeEventListener(mSearchedCategoryReferenceListener);
    }




//            private void addToSharedPreferences(String category) {
//                mEditor.putString(Constants.PREFERENCES_CATEGORY_KEY, category).apply();

//            }


}

