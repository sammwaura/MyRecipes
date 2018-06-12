package com.meshsami27.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;

    private String[] category = new String[] {"Beef, Chicken, Dessert, Lamb, Miscillaneous, Pasta, Pork, Seafood, Side, Starter, Vegan, Vegeterian"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapter = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, category);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String category = ((TextView) view).getText().toString();
            Toast.makeText(CategoryActivity.this, category, Toast.LENGTH_LONG).show();
        }
        });

        Intent intent = getIntent();

    }
}
