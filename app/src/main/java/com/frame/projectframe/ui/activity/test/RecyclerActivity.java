package com.frame.projectframe.ui.activity.test;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.frame.projectframe.R;
import com.frame.projectframe.base.activity.BaseActivity;
import com.frame.projectframe.http.helper.file_down.DownLoadFileBuilder;
import com.frame.projectframe.http.helper.file_down.DownLoadListener;
import com.frame.projectframe.ui.view.recycler_view.BaseGridItemDecoration;
import com.frame.projectframe.ui.view.recycler_view.BaseLinearItemDecoration;
import com.frame.projectframe.ui.view.recycler_view.BaseRVAdapter;
import com.frame.projectframe.ui.view.recycler_view.BaseRViewHolder;

import butterknife.BindView;
import okhttp3.ResponseBody;

public class RecyclerActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BaseRVAdapter<String> adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recycler;
    }

    @Override
    public void onViewCreate() {
        adapter = new BaseRVAdapter<String>(R.layout.item_test_recycler, null) {
            @Override
            protected void convert(BaseRViewHolder holder, String s) {

            }
        };

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        BaseGridItemDecoration decoration = new BaseGridItemDecoration(this, 20, 10, ContextCompat.getColor(this, R.color.color_0079BE));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEventAndData() {
        for (int i = 0; i < 18; i++) {
            adapter.add("手榴弹位置：" + i);
        }
        adapter.notifyDataSetChanged();
    }

    private void downLoading() {
        DownLoadFileBuilder.singleFileDownLoad(new DownLoadListener<ResponseBody>() {
            @Override
            public void downLoadStart() {//开始下载

            }

            @Override
            public void downLoading(long bytesWritten, long contentLength) {

            }

            @Override
            public void downLoadSuccess(ResponseBody responseBody) {

            }

            @Override
            public void downLoadFail(String error) {

            }

            @Override
            public void downLoadComplete() {

            }

            @Override
            public void downLoadSaving() {

            }
        });
    }
}
