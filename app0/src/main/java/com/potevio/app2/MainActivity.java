package com.potevio.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Observable;

/**
 * LifeCycle的使用示例
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyLifeObserver());
        getLifecycle().addObserver(new MyDefaultLifecycleObserver());
    }
}
