package com.meshsami27.myrecipes;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRecipesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mRecipeAdapter;

    public MyRecipesArrayAdapter(Context mContext, int resource, String[] mRecipeAdapter) {
        super(mContext, resource);
        this.mRecipeAdapter = mRecipeAdapter;

    }

    @Override
    public Object getItem(int position) {
        String RecipeAdapter = mRecipeAdapter[position];
        return String.format("%s", RecipeAdapter);
    }

    @Override
    public int getCount() { return mRecipeAdapter.length; }

}
