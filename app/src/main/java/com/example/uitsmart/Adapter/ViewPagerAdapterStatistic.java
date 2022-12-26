package com.example.uitsmart.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.uitsmart.View.HumidityStatisticFragment;
import com.example.uitsmart.View.TempStatisticFragment;
import com.example.uitsmart.View.WindStatisticFragment;


public class ViewPagerAdapterStatistic extends FragmentStatePagerAdapter {

    public ViewPagerAdapterStatistic(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    // trả về
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TempStatisticFragment();
            case 1:
                return new HumidityStatisticFragment();
            case 2:
                return new WindStatisticFragment();
            default:
                return new TempStatisticFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
