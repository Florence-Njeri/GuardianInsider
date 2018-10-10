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
public class TechNewsFragment extends Fragment {


    ArrayList<News> techNewsList;
    public TechNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for tis fragment
        View rootView=inflater.inflate(R.layout.fragment_layout, container, false);

        //Find the RecyclerView to be populated by the adapter
        RecyclerView techNewsRecyclerView=rootView.findViewById(R.id.my_recycler_view);
        //The layout manager
        techNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        techNewsRecyclerView.setHasFixedSize(true);
        NewsAdapter newsAdapter=new NewsAdapter(getActivity(),techNewsList);
        techNewsRecyclerView.setAdapter(newsAdapter);

        return rootView;
    }


}
