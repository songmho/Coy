package com.asc.coy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by songmho on 2015-10-12.
 */
public class ViewPager_Adapter extends FragmentPagerAdapter {

    int MAX_COUNT=7;
    Fragment cur_fragment;

    public ViewPager_Adapter(FragmentManager supportFragmentManger) {
        super(supportFragmentManger);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "공연예술";
            case 1:
                return "사회활동";
            case 2:
                return "생활체육";
            case 3:
                return "예술전시";
            case 4:
                return "음악연주";
            case 5:
                return "종교활동";
            case 6:
                return "학술연구";

        }
        return "  ";
    }


    @Override
    public Fragment getItem(int position) {
        if(position<0 || MAX_COUNT<=position)
            return null;
        switch (position){
            case 0:
                cur_fragment=new List_Fragment();

                break;
            case 1:
                cur_fragment=new List_Fragment();

                break;


        }
        return new  List_Fragment();
    }

    @Override
    public int getCount() {
        return MAX_COUNT;
    }
}
