package com.frame.projectframe.http.helper.file_up;

import com.google.gson.annotations.SerializedName;

public class UploadEntities {
    // 返回数据格式,根据自己服务器来改编
    @SerializedName("path")
    private String mPath;
    @SerializedName("savename")
    private String mSaveName;
    @SerializedName("name")
    private String mName;

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public String getSaveName() {
        return mSaveName;
    }

    public void setSaveName(String saveName) {
        mSaveName = saveName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "UploadEntities{" +
                "mPath='" + mPath + '\'' +
                ", mSaveName='" + mSaveName + '\'' +
                ", mName='" + mName + '\'' +
                '}';
    }
}
