package com.duongnv.bikerental;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.historyx,
            R.drawable.accountx
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager= (ViewPager) findViewById(R.id.viewpager);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), null);
        adapter.addFragment(new HistoryFragment(), null);
        adapter.addFragment(new AccountFragment(), null);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    tabLayout.getTabAt(0).setIcon(R.drawable.home);
                    tabLayout.getTabAt(1).setIcon(R.drawable.historyx);
                    tabLayout.getTabAt(2).setIcon(R.drawable.accountx);

                }else if(tab.getPosition() == 1){
                    tabLayout.getTabAt(0).setIcon(R.drawable.homex);
                    tabLayout.getTabAt(1).setIcon(R.drawable.history);
                    tabLayout.getTabAt(2).setIcon(R.drawable.accountx);

                }
                else if(tab.getPosition() == 2){
                    tabLayout.getTabAt(0).setIcon(R.drawable.homex);
                    tabLayout.getTabAt(1).setIcon(R.drawable.historyx);
                    tabLayout.getTabAt(2).setIcon(R.drawable.account);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void ClickLogout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this,"Logout Success!", Toast.LENGTH_SHORT).show();
        finish();
    }






}
