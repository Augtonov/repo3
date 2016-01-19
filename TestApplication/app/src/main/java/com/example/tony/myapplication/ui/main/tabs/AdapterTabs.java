package com.example.tony.myapplication.ui.main.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by tony on 12/21/2015.
 */
public class AdapterTabs extends FragmentPagerAdapter {

     private int int_items = 3 ;


    public AdapterTabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0 :
                return new FragmentTab1();
            case 1:
                return new FragmentTab2();
            case 2:
                return new FragmentTab3();

        }
        return null;
    }

    @Override
    public int getCount() {
        return int_items;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0 :
                return "Tab 1";
            case 1 :
                return "Tab 2";
            case 2 :
                return "Tab 3";
        }
        return null;
    }
}
