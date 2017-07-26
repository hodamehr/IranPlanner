package com.iranplanner.tourism.iranplanner.ui.activity.comment;


import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.CommentSend;
import entity.ResultCommentList;

/**
 * Created by Hoda
 */
public abstract class CommentContract extends Presenter<CommentContract.View> {
    public interface View {
        void showComments(ResultCommentList resultCommentList);

        void sendCommentMessage(ResultCommentList resultCommentList);

        void showError(String message);

        void commentResult(String message);

        void showComplete();
    }

    public abstract void getCommentList(
            String action,
            String nid,
            String ntype,
            String offset);

    public abstract void callInsertComment(CommentSend commentSend, String cid , String andId);

}
