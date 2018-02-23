package com.frame.projectframe.http.helper.file_down;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by ruibing.han on 2018/2/23.
 */

//文件下载
public interface DownLoadFileService {

    @Streaming//注明为流文件，防止retrofit将大文件读入内存
    @GET
    Flowable<ResponseBody> downLoadFile();//通过@Url覆盖baseurl
}
