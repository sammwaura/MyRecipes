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

public class IngredientsActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView mListView;

    private String[] ingredients = new String[] {""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapter = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ingredient = ((TextView) view).getText().toString();
                Toast.makeText(IngredientsActivity.this, ingredient, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();

    }
}
