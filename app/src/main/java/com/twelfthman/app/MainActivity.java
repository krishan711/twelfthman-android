package com.twelfthman.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.astuetz.PagerSlidingTabStrip;
import com.twelfthman.app.chant.ChantFragment;

public class MainActivity extends ActionBarActivity
{
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.v_pager)
    ViewPager vPager;

    @InjectView(R.id.v_tabs)
    PagerSlidingTabStrip vTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        vPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        vTabs.setViewPager(vPager);
    }

    class PagerAdapter extends FragmentPagerAdapter
    {
        private final String[] TITLES = {"CHANTS", "CHAT"};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ChantFragment();
                case 1:
                    return new ChantFragment();
            }

            return null;
        }
    }

}
