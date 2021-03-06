package com.frame.projectframe.http.helper.file_up;

import com.frame.projectframe.http.response.ParamTwoResponse;
import com.frame.projectframe.module.rxjava.RxUtil;
import com.frame.projectframe.util.BitmapUtil;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/*
 * Created by ruibing.han on 2018/2/23.
 */

public class UpLoadFileBuilder {

    static CompositeDisposable mCompositeDisposable;

    /*
     * 单文件上传
     *
     * @param filePath 上传的文件路径
     */
    public static void singleFileUpLoad(String filePath, final UploadListener<UploadEntities> callBack) {
        File file = new File(filePath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(UploadNetWorkConfig.getRetrofit(callBack).create(UploadFileService.class)
                .singleFileUpLoad(body)
                .compose(RxUtil.<ParamTwoResponse<UploadEntities>>rxSchedulerHelper())
                .compose(RxUtil.<UploadEntities>handleTwoParamResult())
                .subscribeWith(new ResourceSubscriber<UploadEntities>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        callBack.uploadStart();
                    }

                    @Override
                    public void onNext(UploadEntities uploadEntities) {
                        callBack.uploadSuccess(uploadEntities);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.uploadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        callBack.uploadComplete();
                    }
                })
        );
    }

    /*
     * 多文件上传
     *
     * @param fileList 上传的文件路径列表
     */
    public static void multiFileUpLoad(List<String> fileList, final UploadListener<String> callBack) {
        final Map<String, RequestBody> map = new HashMap<>();//添加额外的参数
        map.put("userName", RequestBody.create(MediaType.parse("form-data"), "小明"));
        map.put("password", RequestBody.create(MediaType.parse("form-data"), "123456"));
        map.put("userId", RequestBody.create(MediaType.parse("form-data"), "35234"));

        final MultipartBody.Builder builder = new MultipartBody.Builder();

        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(Flowable.fromIterable(fileList)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String url) throws Exception {
                        //调用图片压缩，返回压缩后路径path
                        String path = BitmapUtil.saveBitmap(url, "newIcon");
                        //注意，FileData是后台给你的对应的字段
                        builder.addFormDataPart("FileData", "avatar.png", RequestBody.create(MultipartBody.FORM, new File(path)));
                        return path;
                    }
                })
                .flatMap(new Function<String, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(String s) throws Exception {
                        return UploadNetWorkConfig.getRetrofit(callBack).create(UploadFileService.class)
                                .multiFileUpLoad(map, builder.build());
                    }
                })
                .compose(RxUtil.<String>rxSchedulerHelper())
                .subscribeWith(new ResourceSubscriber<String>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        callBack.uploadStart();
                    }

                    @Override
                    public void onNext(String s) {
                        callBack.uploadSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.uploadFail(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        callBack.uploadComplete();
                    }
                })
        );
    }

    //取消上传
    public void cancelSubscriber() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
