package com.frame.projectframe.module.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

@Entity
public class platform {
    @Id
    private Long chapterId;
    @NotNull
    private long bookId;        //书ID，服务器的书有值本地书为空
    private int isVip;      //(0:免费，1：付费)
    private int orderId;       //排序
    private long wordsCount;
    private double salePrice;
    @Transient
    private String content;      //章节内容

    @Transient
    private String txtUrl;  //下载地址
    private int source;     //0:缓存，1：下载，2：本地
    private String memberId;
    private boolean beforeLogin;
    private String path;
    public String chapterName;

    @Keep()
    public platform(Long chapterId, long bookId, int isVip, int orderId,
                    long wordsCount, double salePrice, int source, String memberId,
                    boolean beforeLogin, String path, String chapterName) {
        this.chapterId = chapterId;
        this.bookId = bookId;
        this.isVip = isVip;
        this.orderId = orderId;
        this.wordsCount = wordsCount;
        this.salePrice = salePrice;
        this.source = source;
        this.memberId = memberId;
        this.beforeLogin = beforeLogin;
        this.path = path;
        this.chapterName = chapterName;
    }

    @Keep()
    public platform() {
    }


    public Long getChapterId() {
        return this.chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public int getIsVip() {
        return this.isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public long getWordsCount() {
        return this.wordsCount;
    }

    public void setWordsCount(long wordsCount) {
        this.wordsCount = wordsCount;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }



    /*
    * 重新生成实体时，先注释以下代码
    * */

    public String getContent() {
        return content;
    }

    public platform setContent(String content) {
        this.content = content;
        return this;
    }

    public String getTxtUrl() {
        return txtUrl;
    }

    public platform setTxtUrl(String txtUrl) {
        this.txtUrl = txtUrl;
        return this;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return " platform{" +
                "chapterId=" + chapterId +
                ", bookId=" + bookId +
                ", isVip=" + isVip +
                ", orderId=" + orderId +
                ", wordsCount=" + wordsCount +
                ", salePrice=" + salePrice +
                ", content='" + content + '\'' +
                ", txtUrl='" + txtUrl + '\'' +
                ", path='" + path + '\'' +
                ", chapterName='" + chapterName + '\'' +
                '}';
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public boolean getBeforeLogin() {
        return this.beforeLogin;
    }

    public void setBeforeLogin(boolean beforeLogin) {
        this.beforeLogin = beforeLogin;
    }
}

