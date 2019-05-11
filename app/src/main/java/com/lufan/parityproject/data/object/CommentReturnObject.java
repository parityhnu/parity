package com.lufan.parityproject.data.object;

import java.util.List;

public class CommentReturnObject {

    private int mMaxPage;

    private List<JDCommentObject> mJDCommentObjects;

    private List<TBCommentObject> mTBCommentObjects;

    private List<TMCommentObject> mTMCommentObjects;

    public int getMaxPage() {
        return mMaxPage;
    }

    public void setMaxPage(int maxPage) {
        mMaxPage = maxPage;
    }

    public List<JDCommentObject> getJDCommentObjects() {
        return mJDCommentObjects;
    }

    public void setJDCommentObjects(List<JDCommentObject> JDCommentObjects) {
        mJDCommentObjects = JDCommentObjects;
    }

    public List<TBCommentObject> getTBCommentObjects() {
        return mTBCommentObjects;
    }

    public void setTBCommentObjects(List<TBCommentObject> TBCommentObjects) {
        mTBCommentObjects = TBCommentObjects;
    }

    public List<TMCommentObject> getTMCommentObjects() {
        return mTMCommentObjects;
    }

    public void setTMCommentObjects(List<TMCommentObject> TMCommentObjects) {
        mTMCommentObjects = TMCommentObjects;
    }
}
