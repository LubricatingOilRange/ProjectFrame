package com.frame.projectframe.ui.activity.login;

import com.frame.projectframe.base.mvp.BasePresenter;
import com.frame.projectframe.base.mvp.BaseView;
import com.frame.projectframe.http.bean.UserCommand;

public interface LoginContract {
    interface View extends BaseView {

        void showData(UserCommand userCommand);
    }

    interface Presenter extends BasePresenter<View> {

        void getData();

    }
}
