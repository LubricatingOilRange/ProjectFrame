package com.frame.projectframe.http.helper.file_down;

import com.frame.projectframe.module.rxjava.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;

/**
 * 下载文件
 * Created by lxf on 2017/3/3.
 */
public class FileResponseBody extends ResponseBody {

    private Response originalResponse;//原结果

    public FileResponseBody(Response originalResponse) {
        this.originalResponse = originalResponse;
    }

    //返回内容类型
    @Override
    public MediaType contentType() {
        return originalResponse.body().contentType();
    }

    //返回内容长度，没有则返回-1
    @Override
    public long contentLength() {
        return originalResponse.body().contentLength();
    }

    //返回缓存源，类似于io中的BufferedReader
    @Override
    public BufferedSource source() {
        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
            long bytesReadTemp = 0;

            //返回读取到的长度
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReadTemp += bytesRead == -1 ? 0 : bytesRead;
                return bytesRead;
            }
        });
    }
}