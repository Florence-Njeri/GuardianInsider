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
public class SportsFragment extends Fragment {
    ArrayList<News> sportsNewsList;

    public SportsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for tis fragment
        View rootView=inflater.inflate(R.layout.fragment_layout, container, false);

        //Find the RecyclerView to be populated by the adapter
        RecyclerView sportsRecyclerView=rootView.findViewById(R.id.my_recycler_view);

        //The layout manager
        sportsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sportsRecyclerView.setHasFixedSize(true);
        NewsAdapter newsAdapter=new NewsAdapter(getActivity(),sportsNewsList);
        sportsRecyclerView.setAdapter(newsAdapter);
        return rootView;
    }

}
