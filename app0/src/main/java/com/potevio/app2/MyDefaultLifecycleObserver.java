package com.potevio.app2;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * Java8的方式
 */
public class MyDefaultLifecycleObserver implements DefaultLifecycleObserver {
    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        System.out.println("MyDefaultLifecycleObserver.onCreate");
    }
    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        System.out.println("MyDefaultLifecycleObserver.onResume");
    }
}
