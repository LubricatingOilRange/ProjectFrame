package com.frame.projectframe.http.helper.file_down;

/*
 * Created by ruibing.han on 2018/2/23.
 */

public interface DownLoadListener<T> {
    /**
     * 开始下载
     */
    void downLoadStart();

    /**
     * 下载中
     */
    void downLoading(long bytesWritten, long contentLength);

    /**
     * 下载成功
     *
     * @param t
     */
    void downLoadSuccess(T t);

    /**
     * 下载失败
     *
     * @param error
     */
    void downLoadFail(String error);

    /**
     * 下载完成
     */
    void downLoadComplete();

    /**
     * 下载完成-保存中
     */
    void downLoadSaving();
}
