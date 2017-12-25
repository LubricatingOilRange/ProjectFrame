package com.frame.projectframe.module.rxjava;

import com.frame.projectframe.app.Constant;
import com.frame.projectframe.base.mvp.BaseView;
import com.frame.projectframe.http.exception.AppException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by codeest on 2017/2/23.
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;
    private boolean isShowErrorState;

    protected CommonSubscriber(BaseView view) {
        this(view, true);
    }

    protected CommonSubscriber(BaseView view, boolean isShowErrorState) {
        this.mView = view;
        this.isShowErrorState = isShowErrorState;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (isShowErrorState) {
            if (e instanceof AppException) {
                mView.showErrorMsg((AppException) e);
            } else {
                mView.showErrorMsg(new AppException(e.getMessage(), Constant.DEFAULT_CODE));//设置默认Code
            }
        }
    }

}
