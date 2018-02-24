package com.frame.projectframe.http.helper.file_down;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class DownLoadProgressInterceptor implements Interceptor {
    private DownLoadListener mDownLoadListener;

    DownLoadProgressInterceptor(DownLoadListener downLoadListener) {
        this.mDownLoadListener = downLoadListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body(), mDownLoadListener))
                .build();
    }
}
