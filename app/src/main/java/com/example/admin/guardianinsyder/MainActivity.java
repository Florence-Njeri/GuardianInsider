package com.example.admin.guardianinsyder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hook up the adapter to populate the adapter view
        ViewPager viewPager=findViewById(R.id.view_pager);
        NewsPagerAdapter newsPagerAdapter=new NewsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(newsPagerAdapter);

        //Find the tabLayout by ID
        TabLayout tabLayout=findViewById(R.id.tabs);
        //Connect the tabLayout to the view pager
        tabLayout.setupWithViewPager(viewPager);


    }
}
