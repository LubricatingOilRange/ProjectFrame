package com.frame.projectframe.ui.fragment;

import com.frame.projectframe.R;
import com.frame.projectframe.base.fragment.BaseMvpFragment;
import com.frame.projectframe.http.exception.AppException;

/**
 * Created by ruibing.han on 2017/12/25.
 */

public class DemoFragment extends BaseMvpFragment<DemoFragmentPresenter> implements DemoFragmentContract.View {
    @Override
    public int getLayoutId() {
        return R.layout.layout_toast;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onViewCreateAndData() {
        super.onViewCreateAndData();
    }

    @Override
    public void showData() {

    }

    @Override
    public void showErrorMsg(AppException e) {

    }
}
