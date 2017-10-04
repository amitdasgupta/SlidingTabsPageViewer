package com.example.amitdasgupta.intershalaproto;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

public class MainActivity  extends FragmentActivity implements
        ActionBar.TabListener {
    CollectionPagerAdapter mCollectionPagerAdapter;
    ViewPager mViewPager;
    Toolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCollectionPagerAdapter = new CollectionPagerAdapter(
                getSupportFragmentManager());
      toolbar=(Toolbar)findViewById(R.id.toolbar);
        final ActionBar actionBar = getActionBar();
       // actionBar.setHomeButtonEnabled(false);
        actionBar.setIcon(R.drawable.pikachu);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCollectionPagerAdapter);
        mViewPager
                .setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

                    @Override

                    public void onPageSelected(int position) {
                        actionBar.setSelectedNavigationItem(position);
                    }

                });
        for (int i = 0; i < mCollectionPagerAdapter.getCount(); i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(mCollectionPagerAdapter.getPageTitle(i))
                    .setTabListener(this));
        }

    }

    public void onTabUnselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {

    }

    public void onTabSelected(ActionBar.Tab tab,
                              FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());

    }

    public void onTabReselected(ActionBar.Tab tab,
                                FragmentTransaction fragmentTransaction) {

    }

    public class CollectionPagerAdapter extends FragmentPagerAdapter {

        final int NUM_ITEMS = 4; // number of tabs

        public CollectionPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override

        public Fragment getItem(int i) {
            Fragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putInt(TabFragment.ARG_OBJECT, i);
            fragment.setArguments(args);
            return fragment;

        }

        @Override
        public int getCount() {

            return NUM_ITEMS;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            String tabLabel = null;
            switch (position) {
                case 0:
                    tabLabel = getString(R.string.label1);
                    break;
                case 1:
                    tabLabel = getString(R.string.label2);
                    break;
                case 2:
                    tabLabel = getString(R.string.label3);
                    break;
                case 3:
                    tabLabel= getString(R.string.label4);

            }

            return tabLabel;

        }
    }

    public static class TabFragment extends Fragment {
        public static final String ARG_OBJECT = "object";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            Bundle args = getArguments();
            int position = args.getInt(ARG_OBJECT);
            int tabLayout = 0;
            switch (position) {
                case 0:
                    tabLayout = R.layout.tab1;
                    break;
                case 1:
                    tabLayout = R.layout.tab2;
                    break;
                case 2:
                    tabLayout = R.layout.tab3;
                    break;
                case 3:
                    tabLayout=R.layout.tab4;
            }

            View rootView = inflater.inflate(tabLayout, container, false);
            return rootView;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

}