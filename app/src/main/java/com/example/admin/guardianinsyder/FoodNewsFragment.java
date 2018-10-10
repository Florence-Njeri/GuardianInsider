package com.example.admin.guardianinsyder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodNewsFragment extends Fragment {
   // public final String FOOD_NEWS_URL="https://content.guardianapis.com/search?q=food&api-key=test";
ArrayList<News> foodNewsList;


    public FoodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //ArrayList to contain the news list
        foodNewsList=new ArrayList<>();

        //Inflate the layout for tis fragment
        View rootView=inflater.inflate(R.layout.fragment_layout, container, false);

       //Find the RecyclerView to be populated by the adapter
        RecyclerView foodRecyclerView=rootView.findViewById(R.id.my_recycler_view);
        //The layout manager
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        foodRecyclerView.setLayoutManager(layoutManager);
        foodRecyclerView.setHasFixedSize(true);
        NewsAdapter newsAdapter=new NewsAdapter(getActivity(),foodNewsList);
        foodRecyclerView.setAdapter(newsAdapter);

        return rootView;
    }

}
