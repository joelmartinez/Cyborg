package com.cyborg;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.app.ActionBar.Tab;

/**
 * Class found at this stackoverflow thread:
 * http://stackoverflow.com/a/9468041/5416
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private final FragmentActivity mActivity;
    private final String mTag;
    private final Class<T> mClass;
    private final Bundle mArgs;
    private Fragment mFragment;

    public TabListener(FragmentActivity activity, String tag, Class<T> clz, Bundle args) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
        mArgs = args;
        FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();


        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state.  If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        mFragment = mActivity.getSupportFragmentManager().findFragmentByTag(mTag);
        if (mFragment != null && !mFragment.isDetached()) {
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
    	if (ft == null) {
    		ft = mActivity.getSupportFragmentManager().beginTransaction();
    	}

        if (mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
            ft.add(android.R.id.content, mFragment, mTag);
            ft.commit();
        } else {
            ft.attach(mFragment);
            ft.commit();
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    	if (ft == null) {
    		ft = mActivity.getSupportFragmentManager().beginTransaction();
    	}

        if (mFragment != null) {
            ft.detach(mFragment);
            ft.commitAllowingStateLoss();
        }           
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }


}