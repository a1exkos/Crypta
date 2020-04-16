package com.example.crypta.Activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import com.example.crypta.Model.DataBase.CoinNames;
import com.example.crypta.Model.DataBase.ShPrefManager;
import com.example.crypta.Presenter.MainPresenter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainLifecycle implements LifecycleObserver {

    private static final String TAG = "MainLifecycle";
    private Context context;
    private MainPresenter presenter;

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE() {
        Log.i(TAG,"ON_CREATE");
        ShPrefManager.INSTANCE.init(context);
        CoinNames.INSTANCE.init();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME() {
        Log.i(TAG,"ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START() {
        Log.i(TAG,"ON_START");
        presenter.startParse();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE() {
        Log.i(TAG,"ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP() {
        Log.i(TAG,"ON_STOP");
        presenter.stopParse();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY() {
        Log.i(TAG,"ON_DESTROY");
        presenter.unbind();
    }
}
