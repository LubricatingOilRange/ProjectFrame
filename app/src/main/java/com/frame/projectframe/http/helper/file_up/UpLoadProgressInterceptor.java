package com.frame.projectframe.http.helper.file_up;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ruibing.han on 2018/2/23.
 */

public class UpLoadProgressInterceptor implements Interceptor {
    private UploadListener mUploadListener;

    public UpLoadProgressInterceptor(UploadListener uploadListener) {
        mUploadListener = uploadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (null == request.body()) {
            return chain.proceed(request);
        }

        Request build = request.newBuilder()
                .method(request.method(),
                        new CountingRequestBody(request.body(),
                                mUploadListener))
                .build();
        return chain.proceed(build);
    }
}
