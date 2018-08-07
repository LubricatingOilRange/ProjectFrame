package com.paypal.android.p2pmobile.ui.activity;

import com.paypal.android.p2pmobile.R;
import com.paypal.android.p2pmobile.module.manager.FacialFragmentManager;
import com.paypal.android.p2pmobile.ui.fragment.FacialPersonalFragment;

public class FacialActivity extends BaseFacialActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_facial;
    }

    @Override
    protected void onCreateInit() {

        FacialFragmentManager.init(this,R.id.fl_facial_container);

        FacialFragmentManager.getInstance().replaceFragment(new FacialPersonalFragment(),true);
    }

    @Override
    public void onBackPressed() {
        FacialFragmentManager.getInstance().setSkipBackStack(true);
        FacialFragmentManager.getInstance().onFragmentBackPressed();
    }
}
