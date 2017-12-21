package com.frame.projectframe.ui.view.recycler_view;

import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruibing.han on 2017/11/23.
 */

public abstract class BaseRVAdapter<T> extends Adapter<BaseRViewHolder> {
    private List<T> mData;
    private int mLayoutId;

    public BaseRVAdapter(@LayoutRes int layoutId, List<T> data) {
        this.mLayoutId = layoutId;
        this.mData = data != null ? mData = data : new ArrayList<T>();
    }

    /**
     * 单个数据的添加
     *
     * @param t
     */
    public void add(T t) {
        this.mData.add(t);
    }

    /**
     * 单个数据的获取
     */
    public T get(int position) {
        return this.mData.get(position);
    }

    /**
     * 在原来的数据的基础上继续添加数据
     *
     * @param mData
     */
    public void addData(@NonNull List<T> mData) {
        this.mData.addAll(mData);
        this.notifyDataSetChanged();
    }

    /**
     * 设置数据全是新数据
     *
     * @param data
     */
    public void addNewData(@NonNull List<T> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    /**
     * 添加单个数据到某个位置上
     *
     * @param position
     * @param t
     */
    public void add(@IntRange(from = 0, to = Integer.MAX_VALUE) int position, T t) {
        this.mData.add(position, t);
        this.notifyItemInserted(position);
    }

    /**
     * 移除某个位置上的数据
     *
     * @param position
     */
    public void remove(@IntRange(from = 0, to = Integer.MAX_VALUE) int position) {
        this.mData.remove(position);
        this.notifyItemRemoved(position);
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    public List<T> getData() {
        return this.mData;
    }

    /**
     * 获取某个位置的数据
     *
     * @return
     */
    public T getItemData(@IntRange(from = 0, to = Integer.MAX_VALUE) int position) {
        return this.mData.get(position);
    }


    @Override
    public BaseRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseRViewHolder holder;
        switch (viewType) {
            default:
                final View mView = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
                holder = new BaseRViewHolder(mView);
                mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnRVItemClickListener != null) {
                            mOnRVItemClickListener.onItemClick(mView, holder.getLayoutPosition());
                        }
                    }
                });
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            default:
                convert(holder, this.mData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.mData.size();
    }

    protected abstract void convert(BaseRViewHolder holder, T t);

    //条目点击事件
    private OnRVItemClickListener mOnRVItemClickListener;

    public void setOnRVItemClickListener(OnRVItemClickListener onRVItemClickListener) {
        this.mOnRVItemClickListener = onRVItemClickListener;
    }
}
