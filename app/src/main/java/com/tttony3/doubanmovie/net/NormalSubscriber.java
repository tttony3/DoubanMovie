package com.tttony3.doubanmovie.net;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.tttony3.doubanmovie.interfaces.ProgressCancelListener;
import com.tttony3.doubanmovie.interfaces.SubscriberOnNextListener;
import com.tttony3.doubanmovie.ui.ProgressDialogHandler;

import rx.Subscriber;

/**
 * Created by tttony3 on 2016/5/21.
 */
public class NormalSubscriber<T> extends Subscriber<T> {
    private String TAG = "ProgressSubscriber";
    private SubscriberOnNextListener mSubscriberOnNextListener;

    private Context context;

    public NormalSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }


    @Override
    public void onCompleted() {
        Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNext(T t) {
        mSubscriberOnNextListener.onNext(t);
    }

    @Override
    public void onStart() {
        Toast.makeText(context, "正在加载..", Toast.LENGTH_SHORT).show();
    }

}
