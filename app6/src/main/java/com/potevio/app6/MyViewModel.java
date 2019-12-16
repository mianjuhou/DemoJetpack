package com.potevio.app6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<String> nameLiveData = new MutableLiveData<>();

    public LiveData<String> getName() {
        return nameLiveData;
    }

    public void changeName(String name) {
        nameLiveData.postValue(name);
    }
}
