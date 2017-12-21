package com.frame.projectframe.ui.view.recycler_view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by ruibing.han on 2017/11/23.
 */

public class BaseRViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews = new SparseArray<>();

    private View mContentView;

    public BaseRViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mContentView = itemView;
    }

    public View getContentView() {
        return this.mContentView;
    }

    //-------------------------------text ---------------------------------------
    public BaseRViewHolder setText(@IdRes int viewId, CharSequence value) {
        TextView view = this.getView(viewId);
        view.setText(value);
        return this;
    }

    public BaseRViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = this.getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public BaseRViewHolder setTextSize(@IdRes int viewId, float size) {
        TextView view = this.getView(viewId);
        view.setTextSize(size);
        return this;
    }

    //----------------------------------image_color_background-------------------------
    public BaseRViewHolder setImageResource(@IdRes int viewId, @DrawableRes int imageResId) {
        ImageView view = this.getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public BaseRViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int color) {
        View view = this.getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseRViewHolder setBackgroundRes(@IdRes int viewId, @DrawableRes int backgroundRes) {
        View view = this.getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public BaseRViewHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable) {
        ImageView view = this.getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public BaseRViewHolder setImageBitmap(@IdRes int viewId, @NonNull Bitmap bitmap) {
        ImageView view = this.getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
// ----------------------------------------visible InVisible,Gone --------------------------

    public BaseRViewHolder setVisible(@IdRes int viewId) {
        View view = this.getView(viewId);
        view.setVisibility(View.VISIBLE);
        return this;
    }

    public BaseRViewHolder setINVisible(@IdRes int viewId) {
        View view = this.getView(viewId);
        view.setVisibility(View.INVISIBLE);
        return this;
    }

    public BaseRViewHolder setGone(@IdRes int viewId) {
        View view = this.getView(viewId);
        view.setVisibility(View.GONE);
        return this;
    }

    // -----------------------------------------Progress------------------------------------
    public BaseRViewHolder setProgress(@IdRes int viewId, int progress) {
        ProgressBar view = this.getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public BaseRViewHolder setMax(@IdRes int viewId, int max) {
        ProgressBar view = this.getView(viewId);
        view.setMax(max);
        return this;
    }

    //------------------------------------OnClickListener------------------------------------
    public BaseRViewHolder setOnClickListener(@IdRes int viewId, final OnRVItemChildClickListener listener) {
        View view = this.getView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemChildClick(v, BaseRViewHolder.this.getLayoutPosition());
                }
            }
        });
        return this;
    }

    public BaseRViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener) {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    //----------------------------------------------------获取View-----------------------------------
    private <T extends View> T getView(@IdRes int viewId) {
        View view = this.mViews.get(viewId);
        if (view == null) {
            view = this.mContentView.findViewById(viewId);
            this.mViews.put(viewId, view);
        }
        return (T) view;
    }
}
