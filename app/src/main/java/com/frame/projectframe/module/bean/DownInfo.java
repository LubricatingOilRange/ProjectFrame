package com.frame.projectframe.module.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;

//下载的信息
@Entity
public class DownInfo {
    @Id(autoincrement = true)
    private Long Id;
    /*存储位置*/
    private String savePath;
    /*下载url*/
    private String downUrl;
    /*基础url*/
    private String baseUrl;
    /*文件总长度*/
    private long contentLength;
    /*已经下载长度*/
    private long downedLength;

    @Keep
    public DownInfo() {
    }

    @Keep
    public DownInfo(String savePath, String downUrl, String baseUrl, long contentLength, long downedLength) {
        this.savePath = savePath;
        this.downUrl = downUrl;
        this.baseUrl = baseUrl;
        this.contentLength = contentLength;
        this.downedLength = downedLength;
    }

    @Keep
    public DownInfo(Long id, String savePath, String downUrl, String baseUrl, long contentLength, long downedLength) {
        this(savePath, downUrl, baseUrl, contentLength, downedLength);
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getDownedLength() {
        return downedLength;
    }

    public void setDownedLength(long downedLength) {
        this.downedLength = downedLength;
    }
}
