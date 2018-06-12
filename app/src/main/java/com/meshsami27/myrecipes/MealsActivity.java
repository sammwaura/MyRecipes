package com.meshsami27.myrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealsActivity extends AppCompatActivity {
    @BindView(R.id.listView)ListView mListView;

    private String[] meals = new String[] {""};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        ButterKnife.bind(this);

        MyRecipesArrayAdapter adapter = new MyRecipesArrayAdapter(this, android.R.layout.simple_list_item_1, meals);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String meals = ((TextView) view).getText().toString();
                Toast.makeText(MealsActivity.this, meals, Toast.LENGTH_LONG).show();

            }

        });

        Intent intent = getIntent();
    }
}
