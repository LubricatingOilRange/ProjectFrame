package com.frame.projectframe.http.helper;


import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.ParamThreeResponse;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Multipart;

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

}
