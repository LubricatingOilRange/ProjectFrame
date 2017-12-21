package com.frame.projectframe.ui.activity.main;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseMvpActivity;
import com.frame.projectframe.http.exception.AppException;
import com.frame.projectframe.module.rxjava.RxBus;
import com.frame.projectframe.util.ToastUtil;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

//主页面
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private Disposable mSubscription;

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
    }

    //初始化数据
    @Override
    public void onEventAndData() {
        mPresenter.getData();

        //接收WelcomeActivity传递的消息（创建一个默认的订阅者返回一个订阅事件）
        mSubscription = RxBus.getDefault().toDefaultFlowable(String.class, new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                ToastUtil.shortShow("来自页面：" + s);
            }
        });
    }

    //获取成功后的回调方法
    @Override
    public void showData() {

    }

    //回去数据失败返回错误信息
    @Override
    public void showErrorMsg(AppException e) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        RxBus.getDefault().post("MainActivity");//当页面返回的时候给WelcomeActivity页面回馈信息
    }

    @Override
    protected void onStop() {
        super.onStop();
        //取消RxBus的订阅事件
        if (mSubscription != null) {
            mSubscription.dispose();
        }
    }
}