package com.lufan.parityproject.data.model;

import com.lufan.parityproject.data.annotation.Member;
import com.lufan.parityproject.data.object.CommentReturnObject;
import com.lufan.parityproject.data.object.JDCommentObject;
import com.lufan.parityproject.data.object.TBCommentObject;
import com.lufan.parityproject.data.object.TMCommentObject;

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
