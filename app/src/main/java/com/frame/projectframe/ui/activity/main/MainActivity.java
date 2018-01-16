package com.frame.projectframe.ui.activity.main;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseMvpActivity;
import com.frame.projectframe.http.exception.AppException;
import com.frame.projectframe.ui.activity.WelcomeActivity;
import com.frame.projectframe.util.StatusBarUtil;

//主页面
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    //初始化控件
    @Override
    public void onViewCreate() {
        super.onViewCreate();
        StatusBarUtil.setStatusBarDrawable(MainActivity.this, R.drawable.shape_three);

    }

    //初始化数据
    @Override
    public void onEventAndData() {
        mPresenter.getData();
    }

    //获取成功后的回调方法
    @Override
    public void showData() {

    }

    //回去数据失败返回错误信息
    @Override
    public void showErrorMsg(AppException e) {

    }
}