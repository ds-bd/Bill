package com.mm.bill;

import java.util.ArrayList;

import com.mm.bill.BillFragmentAdapter.BillPagerData;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

public class MainActivity extends BaseFragmentActivity {

    BillFragmentAdapter mAdapter;
    ViewPager mPager;
    PagerTabStrip mPagerTabStrip;

    private ArrayList<String> mTabList;
    private ArrayList<BillFragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        init();
    }

    private void init() {
        mTabList = new ArrayList<String>();
        mFragmentList = new ArrayList<BillFragment>();

        for (int i = 0; i < BillFragmentAdapter.CONTENT.length; i++) {
            mTabList.add(BillFragmentAdapter.CONTENT[i]);

            BillFragment fragment = new BillFragment();
            mFragmentList.add(fragment);
        }
        BillPagerData.getInstance().initBillPagerData(mFragmentList, mTabList);

        mAdapter = new BillFragmentAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
    }

    private void setupView() {
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);
        
        mPagerTabStrip.setTabIndicatorColorResource(R.color.tab_indicator);
        mPagerTabStrip.setTextColor(getResources().getColor(R.color.tab_text));
    }
}
