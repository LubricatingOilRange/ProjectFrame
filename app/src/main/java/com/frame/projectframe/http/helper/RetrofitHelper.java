package com.frame.projectframe.http.helper;

import com.frame.projectframe.http.api.AppService;
import com.frame.projectframe.http.api.OtherService;
import com.frame.projectframe.http.bean.UserCommand;
import com.frame.projectframe.http.response.ParamThreeResponse;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by ruibing.han on 2017/10/20.
 */

public class RetrofitHelper implements HttpHelper {

    private AppService mAppService;
    private OtherService mOtherService;

    @Inject
    public RetrofitHelper(AppService appService, OtherService otherService) {
        this.mAppService = appService;
        this.mOtherService = otherService;
    }

    /**
     * 登录 - 获取数据
     *
     * @return
     */
    @Override
    public Flowable<ParamThreeResponse<UserCommand>> login(Map<String, String> parameterMap) {
        return mAppService.login(parameterMap);
    }

}
