package com.frame.projectframe.http.helper;


import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.ParamThreeResponse;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by ruibing.han on 2017/10/20.
 */

public interface HttpHelper {

    /**
     * 登录
     *
     * @return
     */
    Flowable<ParamThreeResponse<UserCommand>> login(Map<String, String> parameterMap);

    /**
     * 单文件上传
     */
    Flowable<String> upSingleLoad(Map<String, RequestBody> parameterMap, MultipartBody body);
}
