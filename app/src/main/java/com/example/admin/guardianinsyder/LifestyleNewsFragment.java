package com.example.admin.guardianinsyder;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifestyleNewsFragment extends Fragment implements LoaderManager.LoaderCallbacks <ArrayList <News>> {


    public static final String LOG_TAG = FoodNewsFragment.class.getName();

    public  LifestyleNewsFragment () {
        // Required empty public constructor
    }

    TextView emptyNewsList;
    ProgressBar progressBar;
    NewsAdapter newsAdapter;
    RecyclerView foodRecyclerView;
    public final String FOOD_NEWS_URL = "https://content.guardianapis.com/search";
    ArrayList <News> foodNewsList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ArrayList to contain the news list
        foodNewsList = new ArrayList <>();

        //Inflate the layout for tis fragment
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

        emptyNewsList = rootView.findViewById(R.id.empty_news_list);
        progressBar = rootView.findViewById(R.id.progress_bar);

        //Find the RecyclerView to be populated by the adapter
        foodRecyclerView = rootView.findViewById(R.id.my_recycler_view);

        //The layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        foodRecyclerView.setLayoutManager(layoutManager);
        foodRecyclerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getActivity(), foodNewsList);
        foodRecyclerView.setAdapter(newsAdapter);

        //Check network availability if available initialize the loader manager
        Objects.requireNonNull(getContext());
        ConnectivityManager checkConnection = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        assert checkConnection != null;
        NetworkInfo activeNetwork = checkConnection.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            getLoaderManager().initLoader(3, null, this);
        } else {
            progressBar.setVisibility(GONE);
            emptyNewsList.setText(R.string.no_internet);
        }
        return rootView;
    }


    //Create and return a new loader
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Loader <ArrayList <News>> onCreateLoader(int i, @Nullable Bundle bundle) {
        Uri newsUri=Uri.parse(FOOD_NEWS_URL);
        Uri.Builder uriBuilder =newsUri.buildUpon();//Append the given segment to the path
        uriBuilder .appendQueryParameter("order-by","newest");//Encode key and value and pass them to the query string
        uriBuilder .appendQueryParameter("show-tags","contributor");
        uriBuilder .appendQueryParameter("q","lifestyle");
        uriBuilder .appendQueryParameter("api-key","62fb0645-db89-4dc3-9899-fcc0fe9c998c");


//Initialize a Loader object thus creating a new Loader and return the full uri
        return new NewsLoader(Objects.requireNonNull(getContext()),uriBuilder.toString());
    }

    //Receive data and update the UI
    @Override
    public void onLoadFinished(@NonNull Loader <ArrayList <News>> loader, ArrayList <News> newsArrayList) {
        //Clear previously update list if any
        foodNewsList.clear();

        progressBar.setVisibility(GONE);


        if (newsArrayList != null) {
            foodNewsList.addAll(newsArrayList);
            newsAdapter.notifyDataSetChanged();
        }
        else{
            emptyNewsList.setText(R.string.no_news_found);

        }
    }

    //Reset previously initialized loader
    @Override
    public void onLoaderReset(@NonNull Loader <ArrayList <News>> loader) {

    }
}
