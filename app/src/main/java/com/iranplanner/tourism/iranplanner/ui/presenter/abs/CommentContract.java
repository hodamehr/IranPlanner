package com.iranplanner.tourism.iranplanner.ui.presenter.abs;


import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResultCommentList;
import entity.ResultItineraryList;

/**
 * Created by Hoda
 */
public abstract class CommentContract extends Presenter<CommentContract.View> {
    public interface View {
        void showComments(ResultCommentList resultCommentList);
        void showError(String message);

        void showComplete();
    }


//    public abstract void callInsertComment(@Body CommentSend request);

    public abstract void getCommentList(
            String action,
            String nid,
            String ntype,
            String offset);



}
