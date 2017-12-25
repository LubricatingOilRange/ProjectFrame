package com.frame.projectframe.ui.fragment;

import com.frame.projectframe.base.mvp.BasePresenter;
import com.frame.projectframe.base.mvp.BaseView;

/**
 * Created by ruibing.han on 2017/12/25.
 */

public interface DemoFragmentContract {

    interface View extends BaseView {
        void showData();
    }

    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
