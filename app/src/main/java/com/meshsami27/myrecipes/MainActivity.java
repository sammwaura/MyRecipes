package com.meshsami27.myrecipes;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.findSearchButton)
    Button mFindSearchButton;
    @BindView(R.id.findCategoryButton)
    TextView mFindCategoryButton;
    @BindView(R.id.findMealsButton)
    TextView mFindMealsButton;
    @BindView(R.id.findIngredientsButton)
    TextView mFindIngredientsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mFindSearchButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (v == mFindSearchButton) {
                    Intent intent = getIntent();
                    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                        String query = intent.getStringExtra(SearchManager.QUERY);
                        doMySearch(query);
                    }
                }
            }
        });

        mFindCategoryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == mFindCategoryButton) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                }
            }
        });

        mFindMealsButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v == mFindMealsButton) {
                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
                startActivity(intent);
            }
        }
        });

        mFindIngredientsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == mFindIngredientsButton) {
                    Intent intent = new Intent(MainActivity.this, IngredientsActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void doMySearch(String query) {
    }


}