package com.asc.coy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by songmho on 2015-10-12.
 */
public class ViewPager_Adapter extends FragmentPagerAdapter {

    int MAX_COUNT=7;
    Fragment [] cur_fragmen =new Fragment[7];
    Bundle[] bundle=new Bundle[7];

    public ViewPager_Adapter(FragmentManager supportFragmentManger) {
        super(supportFragmentManger);

        cur_fragmen[0] = new List_Fragment();
        bundle[0]=new Bundle();
        bundle[0].putString("cur_sub","공연예술분과");
        cur_fragmen[0].setArguments(bundle[0]);


        cur_fragmen[1] = new List_Fragment();
        bundle[1]=new Bundle();
        bundle[1].putString("cur_sub", "사회활동분과");
        cur_fragmen[1].setArguments(bundle[1]);


        cur_fragmen[2] = new List_Fragment();
        bundle[2]=new Bundle();
        bundle[2].putString("cur_sub", "생활체육분과");
        cur_fragmen[2].setArguments(bundle[2]);


        cur_fragmen[3] = new List_Fragment();
        bundle[3]=new Bundle();
        bundle[3].putString("cur_sub", "예술전시분과");
        cur_fragmen[3].setArguments(bundle[3]);


        cur_fragmen[4] = new List_Fragment();
        bundle[4]=new Bundle();
        bundle[4].putString("cur_sub", "음악연주분과");
        cur_fragmen[4].setArguments(bundle[4]);


        cur_fragmen[5] = new List_Fragment();
        bundle[5]=new Bundle();
        bundle[5].putString("cur_sub", "종교활동분과");
        cur_fragmen[5].setArguments(bundle[5]);


        cur_fragmen[6] = new List_Fragment();
        bundle[6]=new Bundle();
        bundle[6].putString("cur_sub", "학술연구분과");
        cur_fragmen[6].setArguments(bundle[6]);
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

        return cur_fragmen[position];
    }

    @Override
    public int getCount() {
        return MAX_COUNT;
    }
}
