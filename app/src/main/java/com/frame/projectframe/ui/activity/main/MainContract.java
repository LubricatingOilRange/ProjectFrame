package com.frame.projectframe.ui.activity.main;

import com.frame.projectframe.base.mvp.BasePresenter;
import com.frame.projectframe.base.mvp.BaseView;

/**
 * Created by ruibing.han on 2017/12/18.
 */

public interface MainContract {

    interface View extends BaseView {

        void showData();
    }

    interface Presenter extends BasePresenter<View> {

        void getData();
    }
}
