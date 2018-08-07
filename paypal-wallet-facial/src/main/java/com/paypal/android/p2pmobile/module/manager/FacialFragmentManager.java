package com.paypal.android.p2pmobile.module.manager;
/*
 * Created by Ruibing.han on 2018/8/7.
 */

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.paypal.android.p2pmobile.ui.activity.BaseFacialActivity;

public class FacialFragmentManager {
    private static FacialFragmentManager mManager;
    private BaseFacialActivity mActivity;
    private int mContainerId = -1;

    public FacialFragmentManager(BaseFacialActivity activity, int containerId) {
        this.mActivity = activity;
        this.mContainerId = containerId;
    }


    public static void init(BaseFacialActivity activity,int containerId) {
        if (mManager == null) {
            synchronized (FragmentManager.class) {
                if (mManager == null) {
                    mManager = new FacialFragmentManager(activity,containerId);
                }
            }
        }
    }

    public static FacialFragmentManager getInstance() {
        if (mManager  == null) {
            throw new ExceptionInInitializerError("please init FragmentManager");
        }
        return mManager;
    }

    private boolean isSkipBackStack = true;//当前是否执行跳跃回退 返回

    //返回跳跃 （返回到指定的Fragment）
    public void setSkipBackStack(boolean skipBackStack) {
        isSkipBackStack = skipBackStack;
    }
    //-------------------------------------replace 实现Fragment的显示-------------------------------

    /**
     * 通过Replace将Fragment添加容器中 模拟栈 实现回退效果
     *
     * @param isAddBackStack (是否添加到回退栈)
     * @param nextFragment     (切换的下一个页面)
     */
    public void replaceFragment(Fragment nextFragment,boolean isAddBackStack ) {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();

        transaction.replace(mContainerId, nextFragment, nextFragment.getClass().getSimpleName());
        if (isAddBackStack) {
            transaction.addToBackStack(nextFragment.getClass().getSimpleName());//添加到回退栈
        }

        transaction.commitAllowingStateLoss();
    }


    //-----------------------------------------popBackStack--------------------------------------
    private void popBackStack(String tag, int flags) {
        mActivity.getSupportFragmentManager().popBackStack(tag, flags);
    }

    //普通的一层一层 回退
    public void onFragmentBackPressed() {
        onFragmentBackPressed(null, 0);
    }

    /**
     * 如果tag为null，flags为0时，弹出回退栈中最上层的那个fragment（普通的一层一层回退）
     * 如果tag为null ，flags为1时，跳过回退栈中所有fragment(包括本身)，--返回后成一个空白页面
     * 如果tag不为null，tag所对应的fragment，flags为0时，弹出该fragment（不包括本身）以上的Fragment
     * 如果tag不为null，tag所对应的fragment，flags为1时，弹出该fragment（包括本身）以上的fragment。
     */
    private void onFragmentBackPressed(String tag, int flags) {
        FragmentManager manager = mActivity.getSupportFragmentManager();

        if (TextUtils.isEmpty(tag) && flags == 0) {//普通的一层一层回退
            if (manager.getBackStackEntryCount() < 2) {
                mActivity.finish();
            } else {
                popBackStack(tag, flags);
            }
        } else if (TextUtils.isEmpty(tag) && flags == 1) {//清栈了（释放栈中的Fragment）
            mActivity.finish();
            ;//一般用来退出当前页面
        } else if (!TextUtils.isEmpty(tag) && flags == 0) {//返回到指定的页面,弹出当前fragment(不包括本身)栈上面的fragment
            popBackStack(tag, flags);
        } else if (!TextUtils.isEmpty(tag) && flags == 1) {//返回到指定的页面,弹出当前fragment(包括本身)栈上面的fragment
            if (manager.getBackStackEntryCount() > 0) {
                String name = manager.getBackStackEntryAt(0).getName();

                if (name.equals(tag)) {
                    //如果当前返回到指定的fragment 是第一个Fragment  因为是flag == 1 就直接finish
                    mActivity.finish();
                } else {
                    if (isSkipBackStack) {
                        popBackStack(tag, flags);
                        isSkipBackStack = false;
                    } else {
                        onFragmentBackPressed();//不进行跳跃返回  逐步返回
                    }
                }
            }
        }
    }
}
