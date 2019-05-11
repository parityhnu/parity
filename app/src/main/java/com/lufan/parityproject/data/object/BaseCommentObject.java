package com.lufan.parityproject.data.object;

import java.util.List;

public class BaseCommentObject implements Comparable<BaseCommentObject> {
    private String mGid;

    private int mIndex;

    private long mCtime;

    private String mRateContent;

    private List<String> mPics;

    private String mDisplayUserNick;

    private String mContent;

    public String getGid() {
        return mGid;
    }

    public void setGid(String gid) {
        mGid = gid;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public long getCtime() {
        return mCtime;
    }

    public void setCtime(long ctime) {
        mCtime = ctime;
    }

    public String getRateContent() {
        return mRateContent;
    }

    public void setRateContent(String rateContent) {
        mRateContent = rateContent;
    }

    public List<String> getPics() {
        return mPics;
    }

    public void setPics(List<String> pics) {
        mPics = pics;
    }

    public String getDisplayUserNick() {
        return mDisplayUserNick;
    }

    public void setDisplayUserNick(String displayUserNick) {
        mDisplayUserNick = displayUserNick;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    @Override
    public int compareTo(BaseCommentObject o) {
        return (int) (this.mCtime - o.mCtime);
    }
}