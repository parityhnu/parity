package com.binqing.utilproject.data.model;

import com.binqing.utilproject.data.annotation.Member;
import com.binqing.utilproject.data.object.CommentReturnObject;
import com.binqing.utilproject.data.object.JDCommentObject;
import com.binqing.utilproject.data.object.TBCommentObject;
import com.binqing.utilproject.data.object.TMCommentObject;

import java.util.List;

public class CommentReturnModel {

    @Member(order = 0)
    private int maxPage;

    @Member(order = 1)
    private List<JDCommentModel> jdCommentModels;

    @Member(order = 2)
    private List<TBCommentModel> tbCommentModels;

    @Member(order = 3)
    private List<TMCommentModel> tmCommentModels;

    public CommentReturnObject toObject() {
        CommentReturnObject object = new CommentReturnObject();
        object.setMaxPage(maxPage);
        object.setJDCommentObjects(JDCommentObject.fromModels(jdCommentModels));
        object.setTBCommentObjects(TBCommentObject.fromModels(tbCommentModels));
        object.setTMCommentObjects(TMCommentObject.fromModels(tmCommentModels));
        return object;
    }
}
