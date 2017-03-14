package com.iranplanner.tourism.iranplanner.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coinpany.core.android.widget.Utils;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.RecyclerItemOnClickListener;
import com.iranplanner.tourism.iranplanner.adapter.CommentListAdapter;
import com.iranplanner.tourism.iranplanner.adapter.ReseveDateListAdapter;
import com.iranplanner.tourism.iranplanner.standard.DataTransferInterface;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.CommentSend;
import entity.ResultComment;
import entity.ResultCommentList;
import entity.ResultItinerary;
import entity.ResultItineraryAttraction;
import entity.ResultLodging;
import entity.ResultLodgingList;
import entity.ResultLodgingRoomList;
import entity.ResultRoom;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.Util;
import tools.widget.PersianDatePicker;

import static com.iranplanner.tourism.iranplanner.R.id.commentButtonHolder;

/**
 * Created by h.vahidimehr on 21/02/2017.
 */

public class CommentListActivity extends StandardActivity implements DataTransferInterface {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_itinerary_comment);
        recyclerView = (RecyclerView) findViewById(R.id.commentRecyclerView);
        sendCommentBtn = (ImageView) findViewById(R.id.sendCommentBtn);
        txtAddComment = (EditText) findViewById(R.id.txtAddComment);
        commentTitle = (TextView) findViewById(R.id.commentTitle);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        resultComments = (List<ResultComment>) extras.getSerializable("resultComments");
        fromWhere =  extras.getString("fromWhere");
        if(fromWhere.equals("Itinerary")){
            itineraryData = (ResultItinerary) extras.getSerializable("itineraryData");
            nextOffset = (String) extras.getSerializable("nextOffset");
            commentTitle.setText(itineraryData.getItineraryFromCityName() + " " + itineraryData.getItineraryToCityName() + " " + Util.persianNumbers(itineraryData.getItineraryDuration()) + " روز");

        }else if(fromWhere.equals("attraction")){
            attraction = (ResultItineraryAttraction) extras.getSerializable("attraction");
            nextOffset = (String) extras.getSerializable("nextOffset");
            commentTitle.setText(attraction.getAttractionTitle());
        }

        adapter = new CommentListAdapter(CommentListActivity.this, this, resultComments, getApplicationContext(), R.layout.fragment_comment_item);
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        if(nextOffset!=null && Integer.valueOf(nextOffset)>1){
            recyclerView.smoothScrollToPosition(Integer.valueOf(nextOffset)-1);
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
                if (dy > 0) //check for scroll up
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisiblesItems) == totalItemCount) {
                        getResultOfCommentList(itineraryData.getItineraryId(), nextOffset);
                    }
                }
            }
        });


        txtAddComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 0) {
                    sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
                }
                if (start != 0 || (start == 0 && before == 0 && count == 1)) {
                    sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_pink));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        sendCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("send comment", "clicked");
                String userId = Util.getUseRIdFromShareprefrence(getApplicationContext());
                if (!userId.isEmpty() && !txtAddComment.getText().equals("") && !sending) {
                    sending = true;
                    sendCommentBtn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_send_grey));
                    if(fromWhere.equals("Itinerary")){
                        getResultOfCommentInsert(userId, String.valueOf(txtAddComment.getText()), itineraryData.getItineraryId(),"itinerary");
                    }else if(fromWhere.equals("attraction")){
                        getResultOfCommentInsert(userId, String.valueOf(txtAddComment.getText()), attraction.getAttractionId(),"attraction");
                    }
                    txtAddComment.setText("");
                } else if (userId.isEmpty()) {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getResultOfCommentList(String itineraryId, String Offset) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
//        api-data.php?action=pagecomments&nid=1&ntype=attraction
        Call<ResultCommentList> callc = getJsonInterface.getCommentList("pagecomments", itineraryId, "itinerary", Offset);
        callc.enqueue(new Callback<ResultCommentList>() {
            @Override
            public void onResponse(Call<ResultCommentList> call, Response<ResultCommentList> response) {

                if (response.body() != null) {
                    ResultCommentList jsonResponse = response.body();
                    List<ResultComment> resultComments = jsonResponse.getResultComment();
                    if (!nextOffset.equals(response.body().getStatistics().getOffsetNext().toString())) {
                        resultComments.addAll(resultComments);
                        adapter.notifyDataSetChanged();
//                        waitingLoading.setVisibility(View.INVISIBLE);
                        nextOffset = response.body().getStatistics().getOffsetNext().toString();
                    }
                } else {
                    Log.e("comment body", "null");
                }
            }

            @Override
            public void onFailure(Call<ResultCommentList> call, Throwable t) {
                Log.e("result of intresting", "false");
            }
        });
    }

    public void getResultOfCommentInsert(String userId, String comment, String id,String type) {
//        getResultLodgingRoomList
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
//        {"uid":"1","cid":"1","ntype":"attraction","nid":"1","gtype":"comment","gvalue":"khobi"}
        Call<ResultCommentList> callc = getJsonInterface.callInsertComment(new CommentSend(userId, "1",type, id, "comment", comment));
        callc.enqueue(new Callback<ResultCommentList>() {
            @Override
            public void onResponse(Call<ResultCommentList> call, Response<ResultCommentList> response) {
                if (response.body() != null) {
                    ResultCommentList jsonResponse = response.body();
                    entity.Status status = jsonResponse.getStatus();
                    if (status.getStatus() == 200) {
                        sending = false;
                        CustomDialogAlert customDialogAlert = new CustomDialogAlert(CommentListActivity.this);
                        customDialogAlert.show();
                    }
                } else {
                    Log.e("comment body", "null");
                    sending = false;
                }
            }

            @Override
            public void onFailure(Call<ResultCommentList> call, Throwable t) {
                Log.e("result of intresting", "false");
            }
        });
    }

    public class CustomDialogAlert extends Dialog implements
            android.view.View.OnClickListener {

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

    private OkHttpClient setHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    @Override
    public void setValues(ArrayList<String> al) {
        al.get(0);
    }
}
