package com.frame.projectframe.ui.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;

import com.frame.projectframe.R;
import com.frame.projectframe.app.AppViewHolder;

/**
 * Created by Haoz on 2017/4/6 0006.
 */

public class DialogFragmentHelper {


    // ---------------------------------------------退出登录- 登录页面---------------------------------------------------------
    public static final String EXIST_LOGIN = "test_login";

    public static void showTestDialog(FragmentManager manage) {
        final CommonDialogFragment commonDialogFragment = CommonDialogFragment.newInstance(new CommonDialogFragment.OnDialogCallBack() {
            @Override
            public Dialog getDialog(final Activity activity) {
                //Dialog参数可以从外部传进来，这需要个人喜好设置
                return new CommonDialog.Builder(activity)
                        .setBackGroundLevel(1f)//背景透明（0-1）
//                      .setWidthAndHeight(ScreenUtil.getScreenWidth(context) * 2 / 3, ScreenUtil.getScreenHeight(context) / 3)//dialog的宽高
                        .setWidthAndHeight(0, 0)//dialog的宽高
//                      .setXAndY(-100, 300)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                        .setXAndY(300, 200)//设置dialog坐标点(基于Gravity之后的偏移量，默认为中心点)
                        .setOutsideTouchable(true)//设置dialog外部点击是否消失
//                      .setAnimationStyle(R.style.anim_dialog)//设置dialog显示消失动画
                        .setGravity(Gravity.CENTER)//设置显示在activity的左边
                        .setView(R.layout.layout_toast, new CommonDialogFragment.OnHandleViewCallBack() {
                            @Override
                            public void onHandleView(final CommonDialog mCommonDialog, AppViewHolder holder) {
                                holder.setText(R.id.tv_toast_msg, "你为甚要点击我");
                            }
                        })
                        .create(R.style.style_dialog);//主题创建
            }
        });
        commonDialogFragment.show(manage, EXIST_LOGIN);
    }
}