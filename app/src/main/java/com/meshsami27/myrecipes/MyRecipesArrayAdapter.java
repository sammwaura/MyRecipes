package com.meshsami27.myrecipes;

import android.content.Context;
import android.widget.ArrayAdapter;

public class MyRecipesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mCategories;

    public MyRecipesArrayAdapter(Context mContext, int resource, String[] mCategories) {
        super(mContext, resource);
        this.mCategories = mCategories;

    }

    @Override
    public Object getItem(int position) {
        String categories = mCategories[position];
        return String.format("%s \nServes great: %s", categories);
    }

    @Override
    public int getCount() { return mCategories.length; }

}
