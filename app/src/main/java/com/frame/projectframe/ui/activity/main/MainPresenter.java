package com.frame.projectframe.ui.activity.main;

import com.frame.projectframe.http.helper.RetrofitHelper;
import com.frame.projectframe.module.rxjava.RxPresenter;

import javax.inject.Inject;

/**
 * Created by ruibing.han on 2017/12/18.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public MainPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
        mView.showData();
    }
}
