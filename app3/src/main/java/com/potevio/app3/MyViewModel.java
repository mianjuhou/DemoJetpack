package com.potevio.app3;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyViewModel() {
        System.out.println("MyViewModel构造函数");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        System.out.println("MyViewModel.onCleared");
    }
}
