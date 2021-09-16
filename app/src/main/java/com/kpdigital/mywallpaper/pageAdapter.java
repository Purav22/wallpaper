package com.kpdigital.mywallpaper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pageAdapter extends FragmentPagerAdapter {
    int numCount;

    public pageAdapter( FragmentManager fm, int numCount) {
        super(fm);
        this.numCount = numCount;
    }

    public pageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                home t = new home();
                return t;
            case 1:
                top n = new top();
                return n;
            case 2:
                car c = new car();
                return c;
            case 3:
                nature na = new nature();
                return na;
            case 4:
                food fd = new food();
                return fd;
            case 5:
                travel a = new travel();
                return a;
            case 6:
                allCategory ac = new allCategory();
                return ac;

        }
        return null;
    }

    @Override
    public int getCount() {
        return numCount;
    }
}
