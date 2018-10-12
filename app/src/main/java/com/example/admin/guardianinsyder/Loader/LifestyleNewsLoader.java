package com.example.admin.guardianinsyder.Loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.admin.guardianinsyder.News;
import com.example.admin.guardianinsyder.Query.FoodQueryUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LifestyleNewsLoader extends AsyncTaskLoader <ArrayList <News>> {
    //Loader constructor
    private String url;

    public LifestyleNewsLoader(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList <News> loadInBackground() {
        if (url == null) {
            return null;
        }
        return FoodQueryUtils.fetchJsonData(url);
    }
}
