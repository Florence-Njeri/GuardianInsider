package com.example.admin.guardianinsyder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    Context context;
    public NewsPagerAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new TechNewsFragment();
        }
        else if(position==1){
            return new LifestyleNewsFragment();
        }
        else if(position==2){
            return new FoodNewsFragment();
        }
        else
            return new SportsFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if(position==0){
            return context.getString(R.string.tech);
        }
        else if(position==1){
            return  context.getString(R.string.lifestyle);
        }
        else if(position==2){
            return  context.getString(R.string.food);
        }
        else
            return context.getString(R.string.sports);
    }
}
