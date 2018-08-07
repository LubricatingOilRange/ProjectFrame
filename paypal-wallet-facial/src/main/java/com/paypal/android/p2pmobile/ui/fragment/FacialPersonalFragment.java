package com.paypal.android.p2pmobile.ui.fragment;

import android.view.View;

import com.paypal.android.p2pmobile.R;
import com.paypal.android.p2pmobile.module.manager.FacialFragmentManager;
import com.paypal.android.p2pmobile.ui.activity.BaseFacialActivity;

/*
 * Created by Ruibing.han on 2018/8/7.
 */

public class FacialPersonalFragment extends BaseFacialFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void onViewCreatedInit( View view) {
        view.findViewById(R.id.bt_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacialFragmentManager.getInstance().replaceFragment(new FacialIdentityFragment(),true);
            }
        });

        view.findViewById(R.id.tv_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FacialFragmentManager.getInstance().replaceFragment(new FacialSkipFragment(),true);
            }
        });
    }

    @Override
    protected void onStartInit() {

    }
}
