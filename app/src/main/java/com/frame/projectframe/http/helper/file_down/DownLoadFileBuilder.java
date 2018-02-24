package com.frame.projectframe.http.helper.file_down;

import com.frame.projectframe.module.bean.DownInfo;
import com.frame.projectframe.module.rxjava.RxUtil;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;

/**
 * Created by ruibing.han on 2018/2/24.
 */

public class DownLoadFileBuilder {

    private static CompositeDisposable mCompositeDisposable;

    /*
     * 单文件下载
     * @param loadListener
     */
    public static void singleFileDownLoad(final DownLoadListener<ResponseBody> loadListener) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(DownLoadNetWorkConfig.getRetrofit(loadListener)
                .create(DownLoadFileService.class)
                .downLoadFile()
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        //保存到文件中 在onNext之前执行
                        loadListener.downLoadSaving();
                    }
                })
                .compose(RxUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        loadListener.downLoadStart();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        loadListener.downLoadSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable t) {
                        loadListener.downLoadFail(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        loadListener.downLoadComplete();
                    }
                })
        );
    }

    /*
     * 单文件断点续传
     * @param loadListener
     */
    public static void singleFileBreakpointDownLoad(DownInfo info, final DownLoadListener<ResponseBody> loadListener) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(DownLoadNetWorkConfig.getRetrofit(loadListener)
                .create(DownLoadFileService.class)
                .downLoadFile()//"bytes=" + info.getReadLength() + "-",info.getUrl()
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        //保存到文件中 在onNext之前执行
                        loadListener.downLoadSaving();
                    }
                })
                .compose(RxUtil.<ResponseBody>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        loadListener.downLoadStart();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        loadListener.downLoadSuccess(responseBody);
                    }

                    @Override
                    public void onError(Throwable t) {
                        loadListener.downLoadFail(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        loadListener.downLoadComplete();
                    }
                })
        );
    }

    //取消下载
    public static void cancelSubscriber() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
