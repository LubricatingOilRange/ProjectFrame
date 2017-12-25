package com.frame.projectframe.ui.activity.login;

import com.frame.projectframe.http.helper.RetrofitHelper;
import com.frame.projectframe.module.rxjava.RxPresenter;

import javax.inject.Inject;

/**
 * Created by ruibing.han on 2017/10/20.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private RetrofitHelper mRetrofitHelper;

    @Inject
    public LoginPresenter(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    @Override
    public void getData() {
//        addSubscribe(mRetrofitHelper.login(null)
//                .compose(RxUtil.<BaseResponse<UserCommand>>rxSchedulerHelper())
//                .compose(RxUtil.<UserCommand>handleBaseResult())
//                .subscribeWith(new CommonSubscriber<UserCommand>(mView) {
//                    @Override
//                    public void onNext(UserCommand userCommand) {
//                        mView.showData(userCommand);
//                    }
//                }));
        mView.showData(null);
    }

}
