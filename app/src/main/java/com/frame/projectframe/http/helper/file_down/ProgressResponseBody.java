package com.frame.projectframe.http.helper.file_down;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 下载文件
 * Created by lxf on 2017/3/3.
 */
public class ProgressResponseBody extends ResponseBody {

    private ResponseBody responseBody;
    private DownLoadListener mDownLoadListener;

    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, DownLoadListener downLoadListener) {
        this.responseBody = responseBody;
        this.mDownLoadListener = downLoadListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                //实时发送当前已读取的字节和总字节
                mDownLoadListener.downLoading(bytesReaded, contentLength());
                return bytesRead;
            }
        };
    }
}