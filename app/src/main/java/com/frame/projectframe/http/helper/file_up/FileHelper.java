package com.frame.projectframe.http.helper.file_up;

import com.frame.projectframe.module.rxjava.RxUtil;

import org.reactivestreams.Publisher;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;

/**
 * Created by ruibing.han on 2018/2/9.
 */

public class FileHelper {

    public void upLoad(List<String> imgList) {
        final Map<String, RequestBody> map = new HashMap<>();//除了图片可能还有其他一些参数需要上传
        map.put("userName", RequestBody.create(MediaType.parse("from-data"), "your name"));
        map.put("password", RequestBody.create(MediaType.parse("from-data"), "your password"));

        final MultipartBody.Builder body = new MultipartBody.Builder();

        Flowable.fromIterable(imgList)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String imgPath) throws Exception {
                        //注意，imgData是后台给你的对应的字段
                        body.addFormDataPart("imgData", "avatar.png", RequestBody.create(MultipartBody.FORM, new File(imgPath)));
                        return imgPath;//调用图片压缩，返回压缩后路径tmp_path
                    }
                })
                .flatMap(new Function<String, Publisher<String>>() {//Publisher<String> 后台返回的数据 String
                    @Override
                    public Publisher<String> apply(String s) throws Exception {
                        return null;
                    }
                })
                .compose(RxUtil.rxSchedulerHelper());
    }
}
