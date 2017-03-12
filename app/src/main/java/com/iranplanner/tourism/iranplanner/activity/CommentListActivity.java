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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_itinerary_comment);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.commentRecyclerView);
        sendCommentBtn = (ImageView) findViewById(R.id.sendCommentBtn);
        txtAddComment = (EditText) findViewById(R.id.txtAddComment);
        commentTitle = (TextView) findViewById(R.id.commentTitle);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        Bundle extras = getIntent().getExtras();
        List<ResultComment> resultComments = (List<ResultComment>) extras.getSerializable("resultComments");
        itineraryData = (ResultItinerary) extras.getSerializable("itineraryData");
        adapter = new CommentListAdapter(CommentListActivity.this, this, resultComments, getApplicationContext(), R.layout.fragment_comment_item);
        commentTitle.setText(itineraryData.getItineraryFromCityName() + " " + itineraryData.getItineraryToCityName() + " " + Util.persianNumbers(itineraryData.getItineraryDuration()) + " روز");
        recyclerView.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
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

        txtAddComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

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
                    getResultOfCommentInsert(userId, String.valueOf(txtAddComment.getText()), itineraryData.getItineraryId());
                    txtAddComment.setText("");
                } else if (userId.isEmpty()) {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void getResultOfCommentInsert(String userId, String comment, String itineraryId) {
//        getResultLodgingRoomList
        Retrofit retrofit = new Retrofit.Builder()
                .client(setHttpClient())
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
//        {"uid":"1","cid":"1","ntype":"attraction","nid":"1","gtype":"comment","gvalue":"khobi"}
        Call<ResultCommentList> callc = getJsonInterface.callInsertComment(new CommentSend(userId, "1", "itinerary", itineraryId, "comment", comment));
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
