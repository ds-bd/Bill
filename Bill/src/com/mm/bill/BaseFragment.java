package com.mm.bill;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements NoLeakHandlerInterface {

    protected FragmentActivity mFragmentActivity;
    protected Context mContext;

    // views
    protected ViewGroup mViewGroup;

    protected int mId = -1;
    private boolean mIsForeground = false;
    private String mFragmentTitle = "";

    protected NoLeakHandler mNoLeakhandler;
    protected Handler mHandler;

    public interface OnLoadFinishListener {
        public void onLoadFinish(BaseFragment fragment);
    }

    public String getFragmentTitle() {
        return mFragmentTitle;
    }

    public void setFragmentTitle(String title) {
        mFragmentTitle = title;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentActivity = getActivity();
        mContext = getActivity().getBaseContext();
        mNoLeakhandler = new NoLeakHandler(this);
        mHandler = mNoLeakhandler.handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViewGroup != null) {
            if (mViewGroup.getParent() != null) {
                ((ViewGroup) mViewGroup.getParent()).removeView(mViewGroup);
            }
        }
        if (!TextUtils.isEmpty(mFragmentTitle)) {
            mFragmentActivity.setTitle(mFragmentTitle);
        }
        return mViewGroup;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsForeground = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsForeground = false;
    }

    public Context getContext() {
        return mContext;
    }

    public Activity getFragmentActivity() {
        return mFragmentActivity;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    public void release() {
        if (mViewGroup != null) {
            if (mViewGroup.getParent() != null) {
                ((ViewGroup) mViewGroup.getParent()).removeView(mViewGroup);
            }
            mViewGroup.removeAllViews();
            mViewGroup = null;
        }
    }

    public boolean isForeground() {
        return mIsForeground;
    }

    public boolean isValid() {
        return isAdded() && mContext != null;
    }

    /**
     * Override this method to receiver msg
     */
    public void handleMessage(Message msg) {
    }

}
