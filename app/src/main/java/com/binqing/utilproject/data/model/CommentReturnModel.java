package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.CommentReturnObject;
import com.binqing.utilproject.data.object.JDCommentObject;
import com.binqing.utilproject.data.object.TBCommentObject;
import com.binqing.utilproject.data.object.TMCommentObject;

import java.util.List;

public class CommentReturnModel {

    @Member(order = 0)
    public int maxPage;

    @Member(order = 1)
    public List<JDCommentModel> jdCommentModels;

    @Member(order = 2)
    public List<TBCommentModel> tbCommentModels;

    @Member(order = 3)
    public List<TMCommentModel> tmCommentModels;

    public CommentReturnObject toObject() {
        CommentReturnObject object = new CommentReturnObject();
        object.setMaxPage(maxPage);
        object.setJDCommentObjects(JDCommentObject.fromModels(jdCommentModels));
        object.setTBCommentObjects(TBCommentObject.fromModels(tbCommentModels));
        object.setTMCommentObjects(TMCommentObject.fromModels(tmCommentModels));
        return object;
    }
}
