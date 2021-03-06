package com.iranplanner.tourism.iranplanner.ui.activity.comment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;

import com.iranplanner.tourism.iranplanner.di.model.App;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import entity.CommentSend;
import entity.ResultComment;
import entity.ResultCommentList;
import entity.ResultItinerary;
import entity.ResultItineraryAttraction;
import tools.Util;


/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class CommentListActivity extends StandardActivity implements DataTransferInterface, CommentContract.View {
    private CommentListAdapter adapter;
    LinearLayoutManager mLayoutManager;
    ImageView sendCommentBtn;
    EditText txtAddComment;
    ResultItinerary itineraryData;
    TextView commentTitle;
    boolean sending = false;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    String nextOffset;
    List<ResultComment> resultComments;
    RecyclerView recyclerView;
    ResultItineraryAttraction attraction;
    String fromWhere;
    DaggerCommentComponent.Builder builder;
    @Inject
    CommentPresenter commentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_itinerary_comment);
        recyclerView = (RecyclerView) findViewById(R.id.commentRecyclerView);
        sendCommentBtn = (ImageView) findViewById(R.id.sendCommentBtn);
        txtAddComment = (EditText) findViewById(R.id.txtAddComment);
        commentTitle = (TextView) findViewById(R.id.commentTitle);
//---

        builder = DaggerCommentComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .commentModule(new CommentModule(this));
        builder.build().injectComment(this);
        //----
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        resultComments = (List<ResultComment>) extras.getSerializable("resultComments");
        fromWhere = extras.getString("fromWhere");
        if (fromWhere.equals("Itinerary")) {
            itineraryData = (ResultItinerary) extras.getSerializable("itineraryData");
            nextOffset = (String) extras.getSerializable("nextOffset");
            commentTitle.setText(itineraryData.getItineraryFromCityName() + " " + itineraryData.getItineraryToCityName() + " " + Util.persianNumbers(itineraryData.getItineraryDuration()) + " روز");

        } else if (fromWhere.equals("Attraction")) {
            attraction = (ResultItineraryAttraction) extras.getSerializable("attraction");
            nextOffset = (String) extras.getSerializable("nextOffset");
            commentTitle.setText(attraction.getAttractionTitle());
        }

        adapter = new CommentListAdapter(CommentListActivity.this, this, resultComments, getApplicationContext(), R.layout.fragment_comment_item);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if (nextOffset != null && Integer.valueOf(nextOffset) > 1) {
            recyclerView.smoothScrollToPosition(Integer.valueOf(nextOffset) - 1);
        }

        recyclerView.addOnItemTouchListener(new RecyclerItemOnClickListener(getApplicationContext(), new RecyclerItemOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
//                ImageView reservationBtn = (ImageView) view.findViewById(R.id.ReservationBtn);
//                reservationBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.e("reserve", "click");
//                    }
//                });
            }
        }));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


                                                 if (dy < 0) //check for scroll down
                                                 {
                                                     visibleItemCount = mLayoutManager.getChildCount();
                                                     totalItemCount = mLayoutManager.getItemCount();
                                                     pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

//                                                     if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                                     if ((pastVisiblesItems) <= 15 && loading && (Integer.valueOf(nextOffset) > 0)) {
                                                         loading = false;
                                                         visibleItemCount = mLayoutManager.getChildCount();
                                                         totalItemCount = mLayoutManager.getItemCount();
                                                         pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
//                                                         if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                                                         commentPresenter.getCommentList("pagecomments", itineraryData.getItineraryId(), "itinerary", nextOffset);

//                                                         }
                                                     }

                                                 }
                                             }
                                         }

        );


        txtAddComment.addTextChangedListener(new

                                                     TextWatcher() {
                                                         @Override
                                                         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                         }

                                                         @Override
                                                         public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                                             if (start != 0 && (start == 0 && before == 0 && count == 1)) {
//                                                                 sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
//                                                             }
                                                             if (start != 0 || (start == 0 && before == 0 && count == 1)) {
                                                                 sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_pink));
                                                             }
                                                         }

                                                         @Override
                                                         public void afterTextChanged(Editable s) {
                                                             Log.e("d", s.toString());
                                                             if (s.toString().equals("")) {
                                                                 sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
                                                             }
                                                         }
                                                     }

        );
        sendCommentBtn.setOnClickListener(new View.OnClickListener()
                    //// TODO: 9/20/17 salam hoda chetori injaro ba farid barresi konnn
                                          {
                                              @Override
                                              public void onClick(View v) {
                                                  Log.e("send comment", "clicked");
                                                  String userId = Util.getUseRIdFromShareprefrence(getApplicationContext());
                                                  if (!userId.isEmpty() && !txtAddComment.getText().equals("") && !sending) {
                                                      sending = true;
                                                      sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
                                                      if (fromWhere.equals("Itinerary")) {
                                                          commentPresenter.callInsertComment(new CommentSend(userId, "1", "itinerary", itineraryData.getItineraryId(), "comment", String.valueOf(txtAddComment.getText())),Util.getTokenFromSharedPreferences(getApplicationContext()),Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                                                      } else if (fromWhere.equals("Attraction")) {
                                                          commentPresenter.callInsertComment(new CommentSend(userId, "1", "itinerary", attraction.getAttractionId(), "attraction", String.valueOf(txtAddComment.getText())),Util.getTokenFromSharedPreferences(getApplicationContext()),Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                                                      }
                                                      txtAddComment.setText("");
                                                  } else if (userId.isEmpty()) {
                                                      Log.e("user is not login", "error");
                                                      Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید ", Toast.LENGTH_LONG).show();
                                                  }
                                              }
                                          }

        );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_itinerary_comment;
    }

    @Override
    public void showComments(ResultCommentList resultCommentList) {

        List<ResultComment> newresultComments = resultCommentList.getResultComment();
        if (!nextOffset.equals(resultCommentList.getStatistics().getOffsetNext().toString())) {
            resultComments.addAll(0, newresultComments);
            adapter.notifyDataSetChanged();
            nextOffset = resultCommentList.getStatistics().getOffsetNext().toString();
            loading = true;
        }
    }

    @Override
    public void sendCommentMessage(ResultCommentList resultCommentList) {
        entity.Status status = resultCommentList.getStatus();
        if (status.getStatus() == 200) {
            sending = false;
            CustomDialogAlert customDialogAlert = new CustomDialogAlert(CommentListActivity.this);
            customDialogAlert.show();
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void commentResult(String message) {
        Toast.makeText(getApplicationContext(), "اشکال در ارسال پیام، بعد از بررسی اتصال به اینترنت دوباره تلاش کنید", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showComplete() {

    }

    public class CustomDialogAlert extends Dialog implements
            View.OnClickListener {

        public Activity c;
        public Dialog d;
        public TextView txtNo, standardTitle, alertDescription;


        public CustomDialogAlert(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.custom_alert_close);
            txtNo = (TextView) findViewById(R.id.txtNo);
            standardTitle = (TextView) findViewById(R.id.txtAlertTitle);
            alertDescription = (TextView) findViewById(R.id.alertDescription);
            alertDescription.setText("پیام شما با موفقیت دریافت شد، بعد از بررسی منتشر خواهد شد.");
            txtNo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txtNo:
                    dismiss();
                    break;
            }
            dismiss();
        }
    }


    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
