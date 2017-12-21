package com.frame.projectframe.ui.view.auto_layout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.frame.projectframe.ui.view.auto_layout.widget.AutoFrameLayout;
import com.frame.projectframe.ui.view.auto_layout.widget.AutoLinearLayout;
import com.frame.projectframe.ui.view.auto_layout.widget.AutoRelativeLayout;

/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {
    private static final String LAYOUT_LINEAR_LAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAME_LAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVE_LAYOUT = "RelativeLayout";


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (name.equals(LAYOUT_FRAME_LAYOUT)) {
            view = new AutoFrameLayout(context, attrs);
        }

        if (name.equals(LAYOUT_LINEAR_LAYOUT)) {
            view = new AutoLinearLayout(context, attrs);
        }

        if (name.equals(LAYOUT_RELATIVE_LAYOUT)) {
            view = new AutoRelativeLayout(context, attrs);
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}
