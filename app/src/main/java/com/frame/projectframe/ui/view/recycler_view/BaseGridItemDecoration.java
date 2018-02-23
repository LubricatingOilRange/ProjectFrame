package com.frame.projectframe.ui.view.recycler_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by ruibing.han on 2018/2/23.
 */

//网格布局的分割线
public class BaseGridItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int leftRight;
    private int topBottom;

    public BaseGridItemDecoration(Context context, int leftRight, int topBottom, @ColorInt int color) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
        if (color != 0) {
            mDivider = new ColorDrawable(color);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();

        if (mDivider == null || layoutManager.getChildCount() == 0) {
            return;
        }
        //判断总的数量是否可以整除 4
        int spanCount = layoutManager.getSpanCount();

        int left;
        int right;
        int top;
        int bottom;

        final int childCount = parent.getChildCount();
        if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {

            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //得到它在总数里面的位置
                final int position = parent.getChildAdapterPosition(child);
                //获取它所占有的比重
                final int spanSize = lookup.getSpanSize(position);
                //获取每排的位置
                final int spanIndex = lookup.getSpanIndex(position, layoutManager.getSpanCount());
                //将带有颜色的分割线处于中间位置
                final float centerLeft = (layoutManager.getLeftDecorationWidth(child) + 1 - leftRight) / 2;
                final float centerTop = (layoutManager.getBottomDecorationHeight(child) + 1 - topBottom) / 2;
                //判断是否为第一排
                boolean isFirst = position + spanSize <= layoutManager.getSpanCount();
                //画上边的，第一排不需要上边的,只需要在最左边的那项的时候画一次就好
                if (!isFirst && spanIndex == 0) {
                    left = layoutManager.getLeftDecorationWidth(child);
                    right = parent.getWidth() - layoutManager.getLeftDecorationWidth(child);

                    top = (int) (child.getTop() - centerTop) - topBottom;
                    bottom = top + topBottom;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                //最右边的一排不需要右边的
                boolean isRight = spanIndex + spanSize == spanCount;
                if (!isRight) {
                    //计算右边的
                    left = (int) (child.getRight() + centerLeft);
                    right = left + leftRight;
                    top = child.getTop();

                    if (position + spanSize - 1 >= spanCount) {
                        top -= centerTop;
                    }
                    bottom = (int) (child.getBottom() + centerTop);
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        } else {
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                //得到它在总数里面的位置
                final int position = parent.getChildAdapterPosition(child);
                //获取它所占有的比重
                final int spanSize = lookup.getSpanSize(position);
                //获取每排的位置
                final int spanIndex = lookup.getSpanIndex(position, layoutManager.getSpanCount());
                //将带有颜色的分割线处于中间位置
                final float centerLeft = (layoutManager.getRightDecorationWidth(child) + 1 - leftRight) / 2;
                final float centerTop = (layoutManager.getTopDecorationHeight(child) + 1 - topBottom) / 2;
                //判断是否为第一列
                boolean isFirst = position + spanSize <= layoutManager.getSpanCount();
                //画左边的，第一排不需要左边的,只需要在最上边的那项的时候画一次就好
                if (!isFirst && spanIndex == 0) {
                    left = (int) (child.getLeft() - centerLeft) - leftRight;
                    right = left + leftRight;

                    top = layoutManager.getRightDecorationWidth(child);
                    bottom = parent.getHeight() - layoutManager.getTopDecorationHeight(child);
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
                //最下的一排不需要下边的
                boolean isRight = spanIndex + spanSize == spanCount;
                if (!isRight) {
                    //计算右边的
                    left = child.getLeft();
                    if (position + spanSize - 1 >= spanCount) {
                        left -= centerLeft;
                    }
                    right = (int) (child.getRight() + centerTop);

                    top = (int) (child.getBottom() + centerLeft);
                    bottom = top + leftRight;
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) manager;
            final GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            final int childPosition = parent.getChildAdapterPosition(view);
            final int spanCount = layoutManager.getSpanCount();

            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {
                if (childPosition + lp.getSpanSize() - 1 < spanCount) {//第一排的需要上面
                    outRect.top = topBottom;
                }
                if (lp.getSpanIndex() + lp.getSpanSize() == spanCount) {//最边上的需要右边,这里需要考虑到一个合并项的问题
                    outRect.right = leftRight;
                }
                outRect.bottom = topBottom;
                outRect.left = leftRight;
            } else {
                if (childPosition + lp.getSpanSize() - 1 < spanCount) {//第一排的需要left
                    outRect.left = leftRight;
                }
                if (lp.getSpanIndex() + lp.getSpanSize() == spanCount) {//最边上的需要bottom
                    outRect.bottom = topBottom;
                }
                outRect.right = leftRight;
                outRect.top = topBottom;
            }
        } else {//StaggeredGridLayoutManager  交错式网格布局
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) manager;
            final StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            final int childPosition = parent.getChildAdapterPosition(view);
            final int spanCount = layoutManager.getSpanCount();
            final int count = lp.isFullSpan() ? layoutManager.getSpanCount() : 1;

            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {

                if (childPosition + count - 1 < spanCount) {//第一排的需要上面
                    outRect.top = topBottom;
                }
                if (lp.getSpanIndex() + count == spanCount) {//最边上的需要右边,这里需要考虑到一个合并项的问题
                    outRect.right = leftRight;
                }
                outRect.bottom = topBottom;
                outRect.left = leftRight;

            } else {
                if (childPosition + count - 1 < spanCount) {//第一排的需要left
                    outRect.left = leftRight;
                }
                if (lp.getSpanIndex() + count == spanCount) {//最边上的需要bottom
                    outRect.bottom = topBottom;
                }
                outRect.right = leftRight;
                outRect.top = topBottom;
            }
        }
    }
}
