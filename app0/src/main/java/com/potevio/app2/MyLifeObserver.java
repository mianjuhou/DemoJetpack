package com.potevio.app2;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * 使用Java7的方式
 */
public class MyLifeObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(){
        System.out.println("MyLifeObserver.onCreate");
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(){
        System.out.println("MyLifeObserver.onResume");
    }
}
