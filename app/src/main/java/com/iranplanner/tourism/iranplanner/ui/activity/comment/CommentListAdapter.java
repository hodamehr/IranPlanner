package com.iranplanner.tourism.iranplanner.ui.activity.comment;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entity.ResultComment;

/**
 * Created by Hoda on 10/01/2017.
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.ViewHolder> {

    Context context;
    int rowLayout;
    DataTransferInterface dtInterface;

    LayoutInflater inflater;

    List<ResultComment> resultComments;


    public CommentListAdapter(Activity a, DataTransferInterface dtInterface, List<ResultComment> resultComments, Context context, int rowLayout) {
        this.resultComments = resultComments;
        this.context = context;
        this.rowLayout = rowLayout;
        Activity activity = a;
        this.dtInterface = dtInterface;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public CommentListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.from(viewGroup.getContext()).inflate(R.layout.fragment_comment_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentListAdapter.ViewHolder viewHolder, int i) {
        viewHolder.commentText.setText(resultComments.get(i).getCommentBody());
        viewHolder.commentSenderName.setText(resultComments.get(i).getUserFname());
        viewHolder.commentSentTime.setText( Utils.timeElapsedFromDate(getDate(resultComments.get(i).getCommentDate()))+" پیش");
    }

    private Long getDate(String commentTimeString){
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    formatter.setLenient(false);

    Date commentDate = null;
    try {
        commentDate = formatter.parse(commentTimeString);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return commentDate.getTime();
}
    @Override
    public int getItemCount() {
        return resultComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView commentText, commentSenderName,commentSentTime;
        RelativeLayout commentButtonHolder;

        public ViewHolder(View view) {
            super(view);
            commentText = (TextView) view.findViewById(R.id.commentText);
            commentSenderName = (TextView) view.findViewById(R.id.commentSenderName);
            commentSentTime = (TextView) view.findViewById(R.id.commentSentTime);
        }
    }

}


