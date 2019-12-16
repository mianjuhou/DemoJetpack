package com.potevio.app10.http;

import com.potevio.app10.http.param.BaseEntity;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class ResultObserver<T> implements SingleObserver<BaseEntity<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(BaseEntity<T> entity) {
        onCompleted();
        String status = entity.getStatus();
        String errorMsg = entity.getErrorMsg();
        onSucceed(entity.getData());
    }

    @Override
    public void onError(Throwable e) {
        onCompleted();
        _onError(e.getMessage());
    }

    public void onCompleted() {
    }

    public abstract void onSucceed(T data);

    public abstract void _onError(String errorMsg);
}
