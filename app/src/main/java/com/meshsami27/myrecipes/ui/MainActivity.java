//package com.meshsami27.myrecipes.ui;
//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//
//import com.meshsami27.myrecipes.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class MainActivity extends AppCompatActivity  {
//
//
//    @BindView(R.id.categoryEditText)
//    EditText mCategoryEditText;
//    @BindView(R.id.findCategoryButton)
//    Button mFindCategoryButton;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//
//
//        mFindCategoryButton.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                String category = mCategoryEditText.getText().toString();
//                Intent intent = new Intent(MainActivity.this, MealListActivity.class);
//                intent.putExtra("category", category);
//                startActivity(intent);
//            }
//        });
//
//
//    }
//}