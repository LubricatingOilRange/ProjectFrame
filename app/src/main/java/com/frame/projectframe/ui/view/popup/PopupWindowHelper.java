package com.frame.projectframe.ui.view.popup;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import com.frame.projectframe.R;
import com.frame.projectframe.app.AppViewHolder;
import com.frame.projectframe.app.MyApplication;

/**
 * Created by ruibing.han on 2017/11/7.
 */

public class PopupWindowHelper {
    private static final int GRAVITY = Gravity.CENTER;

    /**
     * 默认显示中间
     *
     * @param parent
     * @param context
     * @param commonPopupWindow
     */
    public static void showNormal(Context context, View parent, CommonPopupWindow commonPopupWindow) {
        if (commonPopupWindow == null) {
            commonPopupWindow = new CommonPopupWindow.Builder(context)
                    .setBackGroundLevel(0.5f)//设置背景透明度
                    .setOutsideTouchable(true)//设置点击窗口外部是否消失
                    .setWidthAndHeight(MyApplication.SCREEN_WIDTH * 2 / 3, MyApplication.SCREEN_HEIGHT / 3)//设置PopupWindow的宽高
//                  .setAnimationStyle(R.style.anim_dialog)//设置动画的样式ID
                    .setView(R.layout.layout_toast, new CommonPopupWindow.OnHandleViewCallBack() {
                        @Override
                        public void onHandleView(CommonPopupWindow commonPopupWindow, AppViewHolder holder) {
                            holder.setText(R.id.tv_toast_msg, "你为甚要点击我");
                        }
                    })
                    .create();
        }
        commonPopupWindow.showNormal(parent, GRAVITY);//设置显示在parent的左边
    }
}
