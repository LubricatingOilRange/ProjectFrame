package com.frame.projectframe.http.helper.file_up;

import com.frame.projectframe.http.response.ParamOneResponse;
import com.frame.projectframe.http.response.ParamTwoResponse;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * 上传文件
 * Created by JokAr on 2017/3/6.
 */
public interface UploadFileService {

    // 接口名称，注意替换

    /**
     * 单文件上传
     */
    @Multipart
    @POST("Ui/Image/uploadUnlimited.html")
    Flowable<ParamTwoResponse<UploadEntities>> singleFileUpLoad(@Part MultipartBody.Part file);

    /**
     * 多文件上传
     */
    @Multipart
    @POST("Ui/Image/uploadUnlimited.html")
    Flowable<String> multiFileUpLoad(@PartMap Map<String, RequestBody> parameterMap, @Part("imgs") MultipartBody body);
}
