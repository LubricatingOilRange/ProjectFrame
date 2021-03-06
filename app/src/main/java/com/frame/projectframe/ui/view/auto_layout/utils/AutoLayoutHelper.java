/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frame.projectframe.ui.view.auto_layout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.frame.projectframe.R;
import com.frame.projectframe.ui.view.auto_layout.AutoLayoutInfo;
import com.frame.projectframe.ui.view.auto_layout.attr.HeightAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MarginAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MarginBottomAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MarginLeftAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MarginRightAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MarginTopAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MaxHeightAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MaxWidthAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MinHeightAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.MinWidthAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.PaddingAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.PaddingBottomAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.PaddingLeftAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.PaddingRightAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.PaddingTopAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.TextSizeAttr;
import com.frame.projectframe.ui.view.auto_layout.attr.WidthAttr;
import com.frame.projectframe.ui.view.auto_layout.config.AutoLayoutConfig;
import com.frame.projectframe.util.DimenUtil;
import com.orhanobut.logger.Logger;

public class AutoLayoutHelper {
    private final ViewGroup mHost;

    private static final int[] LL = new int[]
            {
                    android.R.attr.textSize,

                    //Padding
                    android.R.attr.padding,
                    android.R.attr.paddingLeft,
                    android.R.attr.paddingTop,
                    android.R.attr.paddingRight,
                    android.R.attr.paddingBottom,

                    //width--height
                    android.R.attr.layout_width,
                    android.R.attr.layout_height,

                    //Margin
                    android.R.attr.layout_margin,
                    android.R.attr.layout_marginLeft,
                    android.R.attr.layout_marginTop,
                    android.R.attr.layout_marginRight,
                    android.R.attr.layout_marginBottom,

                    //Max——Min
                    android.R.attr.maxWidth,
                    android.R.attr.maxHeight,
                    android.R.attr.minWidth,
                    android.R.attr.minHeight,


            };

    private static final int INDEX_TEXT_SIZE = 0;

    //padding
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;

    //width--height
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;

    //Margin
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;

    //Max——Min
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;


    /**
     * move to other place?
     */
    private static AutoLayoutConfig mAutoLayoutConifg;

    public AutoLayoutHelper(ViewGroup host) {
        mHost = host;

        if (mAutoLayoutConifg == null) {
            initAutoLayoutConfig(host);
        }

    }

    private void initAutoLayoutConfig(ViewGroup host) {
        mAutoLayoutConifg = AutoLayoutConfig.getInstance();
        mAutoLayoutConifg.init(host.getContext());
    }


    public void adjustChildren() {
        AutoLayoutConfig.getInstance().checkParams();

        for (int i = 0, n = mHost.getChildCount(); i < n; i++) {
            View view = mHost.getChildAt(i);
            ViewGroup.LayoutParams params = view.getLayoutParams();

            if (params instanceof AutoLayoutParams) {
                AutoLayoutInfo info =
                        ((AutoLayoutParams) params).getAutoLayoutInfo();
                if (info != null) {
                    info.fillAttrs(view);
                }
            }
        }

    }

    public static AutoLayoutInfo getAutoLayoutInfo(Context context,
                                                   AttributeSet attrs) {

        AutoLayoutInfo info = new AutoLayoutInfo();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.AutoLayout_Layout);
        int baseWidth = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_base_width, 0);
        int baseHeight = a.getInt(R.styleable.AutoLayout_Layout_layout_auto_base_height, 0);
        a.recycle();

        TypedArray array = context.obtainStyledAttributes(attrs, LL);

        int n = array.getIndexCount();


        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);
//            String val = array.getString(index);
//            if (!isPxVal(val)) continue;

            if (!DimenUtil.isPxVal(array.peekValue(index))) continue;

            int pxVal = 0;
            try {
                pxVal = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore)//not dimension
            {
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal, baseWidth, baseHeight));
                    break;

                //padding
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal, baseWidth, baseHeight));
                    break;

                //width-height
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal, baseWidth, baseHeight));
                    break;

                //margin
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal, baseWidth, baseHeight));

                    //Max-Min
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal, baseWidth, baseHeight));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal, baseWidth, baseHeight));
                    break;
            }
        }
        array.recycle();
        Logger.i(" getAutoLayoutInfo " + info.toString());
        return info;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }
}
