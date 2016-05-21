package com.tttony3.doubanmovie.ui;

import android.support.v4.app.Fragment;
import android.util.Log;

/**
 * Created by tttony3 on 2016/5/21.
 */
public abstract class LazyFragment extends Fragment {
    private String TAG = "LazyFragment";
    protected boolean isVisible = false;
    protected boolean isFirtsVisible = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
            if (isFirtsVisible) {
                onFirstVisible();
                isFirtsVisible = false;
            }

        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public abstract void onFirstVisible();

    protected void onInvisible() {
        Log.v(TAG, "onInvisible");
    }

    private void onVisible() {
        Log.v(TAG, "onVisible");
        lazyLoad();
    }

    protected abstract void lazyLoad();
}
