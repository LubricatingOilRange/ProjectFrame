package com.frame.projectframe.ui.activity.login;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseMvpActivity;
import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.exception.AppException;
import com.frame.projectframe.util.ToastUtil;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    //初始化控件
    @Override
    public void onViewCreate() {
        super.onViewCreate();
    }

    //初始化数据
    @Override
    public void onEventAndData() {
        mPresenter.getData();
    }

    //获取成功后的回调方法
    @Override
    public void showData(UserCommand userCommand) {
        ToastUtil.shortShow("获取成功");
    }

    //回去数据失败返回错误信息
    @Override
    public void showErrorMsg(AppException e) {

        ToastUtil.shortShow(e.getMessage());
    }
}
