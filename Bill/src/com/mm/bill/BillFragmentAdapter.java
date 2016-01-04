package com.mm.bill;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BillFragmentAdapter extends FragmentPagerAdapter {

    public static final String[] CONTENT = new String[] { "A", "B" };

    public BillFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BillPagerData.getInstance().mFragments.get(position);
    }

    @Override
    public int getCount() {
        return BillPagerData.getInstance().mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return BillFragmentAdapter.CONTENT[position];
    }

    public static class BillPagerData {

        private static BillPagerData mBillPagerData;

        public ArrayList<BillFragment> mFragments;
        public ArrayList<String> mTabList;

        public BillPagerData() {
        }

        public static BillPagerData getInstance() {
            if (mBillPagerData == null) {
                synchronized (BillPagerData.class) {
                    if (mBillPagerData == null) {
                        mBillPagerData = new BillPagerData();
                    }
                }
            }
            return mBillPagerData;
        }

        public void initBillPagerData(ArrayList<BillFragment> fragments, ArrayList<String> tabList) {
            mFragments = fragments;
            mTabList = tabList;
        }

        public BillFragment getBillDetailFragment(String tabType) {
            if (null == mFragments || null == mTabList) {
                return null;
            }

            return mFragments.get(mTabList.indexOf(tabType));
        }
    }
}