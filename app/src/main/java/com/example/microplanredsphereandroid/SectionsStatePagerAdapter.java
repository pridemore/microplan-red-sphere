package com.example.microplanredsphereandroid;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitleList=new ArrayList<>();

    public SectionsStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

    public void addFragment(Fragment fragment,String title){
        mfragmentList.add(fragment);
        mfragmentTitleList.add(title);
    }
}
