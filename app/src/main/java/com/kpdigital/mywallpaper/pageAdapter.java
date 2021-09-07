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
                trending t = new trending();
                return t;
            case 1:
                nature n = new nature();
                return n;
            case 2:
                background b = new background();
                return b;
            case 3:
                city c = new city();
                return c;
            case 4:
                car Car = new car();
                return Car;
            case 5:
                animal a = new animal();
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
