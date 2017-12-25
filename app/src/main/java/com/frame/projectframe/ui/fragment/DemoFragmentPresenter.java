package com.frame.projectframe.ui.fragment;

import com.frame.projectframe.http.helper.RetrofitHelper;
import com.frame.projectframe.module.rxjava.RxPresenter;

import javax.inject.Inject;

/**
 * Created by ruibing.han on 2017/12/25.
 */

public class DemoFragmentPresenter extends RxPresenter<DemoFragmentContract.View> implements DemoFragmentContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public DemoFragmentPresenter(RetrofitHelper mRetrofitHelper) {
        this.mRetrofitHelper = mRetrofitHelper;
    }

    @Override
    public void getData() {
    }
}
