package com.frame.projectframe.http.helper;


import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.BaseResponse;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by ruibing.han on 2017/10/20.
 */

public interface HttpHelper {

    /**
     * 登录
     *
     * @return
     */
    Flowable<BaseResponse<UserCommand>> login(Map<String, String> parameterMap);
}
