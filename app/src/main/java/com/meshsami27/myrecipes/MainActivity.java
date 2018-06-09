package com.meshsami27.myrecipes;

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

    @BindView(R.id.findCategoriesButton)
    Button mFindCategoriesButton;
    @BindView(R.id.locationTextView)
    EditText mLocationTextView;
    @BindView(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mFindCategoriesButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (v == mFindCategoriesButton) {
                    String location = mLocationTextView.getText().toString();
                    Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                    intent.putExtra("location", location);
                    startActivity(intent);

                }
            }
        });
    }


}