package com.example.admin.guardianinsyder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.example.admin.guardianinsyder.News;

import java.util.ArrayList;

public class NewsLoader extends AsyncTaskLoader <ArrayList <News>> {
    //Loader constructor
    private String url;

    public NewsLoader(@NonNull Context context, String url) {
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
        return QueryUtils.fetchJsonData(url);
    }
}
