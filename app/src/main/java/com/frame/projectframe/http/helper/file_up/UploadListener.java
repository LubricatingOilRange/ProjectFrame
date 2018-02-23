package com.frame.projectframe.http.helper.file_up;

/**
 * Created by ruibing.han on 2018/2/23.
 */

public interface UploadListener<T> {
    /**
     * 开始上传
     */
    void uploadStart();

    /**
     * 上传中
     */
    void uploading(long bytesWritten, long contentLength);

    /**
     * 上传成功
     *
     * @param t
     */
    void uploadSuccess(T t);

    /**
     * 上传失败
     *
     * @param error
     */
    void uploadFail(String error);

    /**
     * 上传完成
     */
    void uploadComplete();
}
