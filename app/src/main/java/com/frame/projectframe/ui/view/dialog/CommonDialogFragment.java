package com.frame.projectframe.ui.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.frame.projectframe.app.AppViewHolder;

/**
 * Created by 神马都是浮云 on 2017-09-02.
 */
public class CommonDialogFragment extends DialogFragment {

    /**
     * 回调获得需要显示的dialog
     */
    private OnDialogCallBack mOnDialogCallBack;

    public interface OnDialogCallBack {
        Dialog getDialog(Activity activity);
    }

    /**
     * 处理布局的回掉接口
     */
    public interface OnHandleViewCallBack {
        void onHandleView(CommonDialog mCommonDialog, AppViewHolder holder);
    }


    public static CommonDialogFragment newInstance(OnDialogCallBack call) {
        CommonDialogFragment instance = new CommonDialogFragment();
        instance.mOnDialogCallBack = call;
        return instance;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (null == mOnDialogCallBack) {
            return super.onCreateDialog(savedInstanceState);
        } else {
            return mOnDialogCallBack.getDialog(getActivity());
        }
    }
}
