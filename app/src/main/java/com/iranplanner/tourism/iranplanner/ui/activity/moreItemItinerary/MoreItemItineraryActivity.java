package com.iranplanner.tourism.iranplanner.ui.activity.moreItemItinerary;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coinpany.core.android.widget.CTouchyWebView;
import com.coinpany.core.android.widget.Utils;
import com.coinpany.core.android.widget.calendar.dateutil.PersianCalendar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.iranplanner.tourism.iranplanner.R;


import com.iranplanner.tourism.iranplanner.di.model.App;

import com.iranplanner.tourism.iranplanner.ui.activity.MapFullActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.hotelReservationListOfCity.ReservationListActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.showAttraction.ShowAttractionActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.StandardActivity;
import com.iranplanner.tourism.iranplanner.ui.activity.comment.CommentListActivity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import entity.InterestResult;
import entity.ItineraryLodgingCity;
import entity.ItineraryPercentage;
import entity.ResultComment;
import entity.ResultCommentList;
import entity.ResultData;
import entity.ResultItinerary;
import entity.ResultItineraryAttraction;
import entity.ResultItineraryAttractionDay;
import entity.ResultItineraryAttractionList;
import entity.ResultWidget;

import entity.ResultWidgetFull;


import entity.ShowAttractionListItinerary;
import tools.Constants;

import tools.Util;

import tools.widget.PersianDatePicker;

public class MoreItemItineraryActivity extends StandardActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,
        View.OnClickListener,
        ItineraryContract.View {

    @Inject
    ItineraryPresenter itineraryPresenter;

    private GoogleMap mMap;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    ResultItinerary itineraryData;
    private String itineraryId;
    List<ResultItineraryAttraction> itineraryActionList;
    List<ResultItineraryAttractionDay> resultItineraryAttractionDays;
    CircularProgressBar progress1;
    CircularProgressBar progress2;
    CircularProgressBar progress3;
    TextView textTpeTravel1;
    TextView textTpeTravel2;
    TextView textTpeTravel3;
    List<Marker> markers;
    TextView textPercentage1;
    TextView textPercentage2;
    TextView textPercentage3;
    TextView txtItinerary_attraction_type, txtDate;
    ProgressDialog progressDialog;
    ImageView itinerary_attraction_type_more;
    protected CTouchyWebView contentFullDescription;
    TextView txtItinerary_attraction_Difficulty;
    TextView txtItinerary_count_attraction;
    ImageView imgItineraryListMore;
    TextView itineraryDuration;
    TextView fromCityName, toCityName;
    TextView showItinerys;
    TextView txtOk, MoreInoText;
//    LinearLayout rateHolder, bookmarkHolder, doneHolder, nowVisitedHolder, beftorVisitedHolder, likeHolder, okHolder, dislikeHolder, commentHolder;
    RelativeLayout ratingHolder, GroupHolder, supplierLayoutMore, VisitedLayout, LikeLayout, changeDateHolder;
    PersianCalendar persianCurrentDate;
//    ImageView bookmarkImg, doneImg, dislikeImg, okImg, likeImg, rateImg, beftorVisitedImg, nowVisitedImg, triangleShowAttraction;
    RotateAnimation rotate;
    String rotateImage;
    Animation translateAnimation;
    boolean ratingHolderFlag = false;
    String myData;
    Boolean showMore = true;
    ViewPager toolsPager;
    List<ItineraryLodgingCity> lodgingReservation;
    Date startOfTravel;
    List<Date> stayNights;
    Map<String, Integer> dateCity;
    Button showReservation, showItinerary;
    //    List<Map<String,Integer>> ss;
    int BookmarkValue;
    int LikeValue;
    int VisitedValue;
    int WishValue;
    //    DaggerItineraryComponent.Builder builder;
    DaggerItineraryComponent.Builder builder;

    private void findView() {
//        setContentView(R.layout.fragment_itinerary_item_more);
        txtItinerary_attraction_Difficulty = (TextView) findViewById(R.id.txtItinerary_attraction_Difficulty);
        toolsPager = (ViewPager) findViewById(R.id.toolsPager);
        txtItinerary_attraction_type = (TextView) findViewById(R.id.txtItinerary_attraction_type);
        txtItinerary_count_attraction = (TextView) findViewById(R.id.txtItinerary_count_attraction);
        itineraryDuration = (TextView) findViewById(R.id.itineraryDuration);
        fromCityName = (TextView) findViewById(R.id.fromCityName);
        toCityName = (TextView) findViewById(R.id.toCityName);
        showReservation = (Button) findViewById(R.id.showReservation);
        itinerary_attraction_type_more = (ImageView) findViewById(R.id.itinerary_attraction_type_more);
//        showItinerys = (TextView) findViewById(R.id.showItinerys);
        showItinerary = (Button) findViewById(R.id.showItinerary1);
        textTpeTravel1 = (TextView) findViewById(R.id.textTpeTravel1);
        textTpeTravel2 = (TextView) findViewById(R.id.textTpeTravel2);
        textTpeTravel3 = (TextView) findViewById(R.id.textTpeTravel3);
        progress1 = (CircularProgressBar) findViewById(R.id.progress1);
        progress2 = (CircularProgressBar) findViewById(R.id.progress2);
        progress3 = (CircularProgressBar) findViewById(R.id.progress3);
        textPercentage1 = (TextView) findViewById(R.id.textPercentage1);
        textPercentage2 = (TextView) findViewById(R.id.textPercentage2);
        textPercentage3 = (TextView) findViewById(R.id.textPercentage3);
        txtDate = (TextView) findViewById(R.id.txtDate);
//        rateHolder = (LinearLayout) findViewById(R.id.rateHolder);
//        doneHolder = (LinearLayout) findViewById(R.id.doneHolder);
//        nowVisitedHolder = (LinearLayout) findViewById(R.id.nowVisitedHolder);
//        beftorVisitedHolder = (LinearLayout) findViewById(R.id.beftorVisitedHolder);
//        dislikeHolder = (LinearLayout) findViewById(R.id.dislikeHolder);
//        commentHolder = (LinearLayout) findViewById(R.id.commentHolder);
//        okHolder = (LinearLayout) findViewById(R.id.okHolder);
//        likeHolder = (LinearLayout) findViewById(R.id.likeHolder);
//        bookmarkHolder = (LinearLayout) findViewById(R.id.bookmarkHolder);
//        ratingHolder = (RelativeLayout) findViewById(R.id.ratingHolder);
        GroupHolder = (RelativeLayout) findViewById(R.id.GroupHolder);
        VisitedLayout = (RelativeLayout) findViewById(R.id.VisitedLayout);
//        LikeLayout = (RelativeLayout) findViewById(R.id.LikeLayout);
        changeDateHolder = (RelativeLayout) findViewById(R.id.changeDateHolder);
        supplierLayoutMore = (RelativeLayout) findViewById(R.id.supplierLayoutMore);
        imgItineraryListMore = (ImageView) findViewById(R.id.imgItineraryListMore);
        contentFullDescription = (CTouchyWebView) findViewById(R.id.contentFullDescription);
        txtOk = (TextView) findViewById(R.id.txtOk);
        MoreInoText = (TextView) findViewById(R.id.MoreInoText);
//        bookmarkImg = (ImageView) findViewById(R.id.bookmarkImg);
//        doneImg = (ImageView) findViewById(R.id.doneImg);
//
//        dislikeImg = (ImageView) findViewById(R.id.dislikeImg);
//        okImg = (ImageView) findViewById(R.id.okImg);
//        likeImg = (ImageView) findViewById(R.id.likeImg);
//        rateImg = (ImageView) findViewById(R.id.rateImg);
//        beftorVisitedImg = (ImageView) findViewById(R.id.beftorVisitedImg);
//        nowVisitedImg = (ImageView) findViewById(R.id.nowVisitedImg);
//        triangleShowAttraction = (ImageView) findViewById(R.id.triangleShowAttraction);
//        nowVisitedImg = (ImageView) findViewById(R.id.nowVisitedImg);
    }

//    private void setInterestResponce(List<ResultWidget> resultWidget) {
//        if (resultWidget.get(0).getWidgetBookmarkValue() != null && resultWidget.get(0).getWidgetBookmarkValue() == 1) {
//            bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmarkgreen));
//        }
//        if (resultWidget.get(0).getWidgetLikeValue() != null && resultWidget.get(0).getWidgetLikeValue() == 1) {
//            likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//
//        }
//        if (resultWidget.get(0).getWidgetLikeValue() != null && resultWidget.get(0).getWidgetLikeValue() == 2) {
//            okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//
//        }
//        if (resultWidget.get(0).getWidgetLikeValue() != null && resultWidget.get(0).getWidgetLikeValue() == 3) {
//            dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//        }
//        if (resultWidget.get(0).getWidgetVisitedValue() != null && resultWidget.get(0).getWidgetVisitedValue() == 1) {
//            nowVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//            doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//        }
//        if (resultWidget.get(0).getWidgetVisitedValue() != null && resultWidget.get(0).getWidgetVisitedValue() == 2) {
//            beftorVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//            doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e(TAG, "MoreItemItineraryActivity");

        findView();
        BookmarkValue = 0;
        LikeValue = 0;
        VisitedValue = 0;
        WishValue = 0;
        showToolViewPager();
//     toolsPager.setPageMargin(1);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        itineraryData = (ResultItinerary) bundle.getSerializable("itineraryData");
        String duration = bundle.getString("duration");
        List<ResultWidget> resultWidget = (List<ResultWidget>) bundle.getSerializable("resultWidget");
//        if (resultWidget != null) {
//            setInterestResponce(resultWidget);
//        }
        setTypeOfTravel();
        myData = itineraryData.getItineraryBody();
        setWebViewContent(getShowMoreString(myData));
        SetPercentage();
        setImageView();
//---------------- set current date
        long time = System.currentTimeMillis();
        txtDate.setText(Utils.getSimpleDateMilli(time));
        startOfTravel = new Date(time);
//        resultLodging();
        txtItinerary_attraction_Difficulty.setText(itineraryData.getItineraryDifficulty().getItineraryDifficultyGroup());
        txtItinerary_count_attraction.setText(Util.persianNumbers(itineraryData.getItineraryCountAttraction()) + " مکان دیدنی");
        itineraryDuration.setText(duration);
        if (itineraryData.getItineraryFromCityName().equals(itineraryData.getItineraryToCityName())) {
            fromCityName.setText(itineraryData.getItineraryFromCityName());
            toCityName.setText("درون شهری");
        } else {
            fromCityName.setText(itineraryData.getItineraryFromCityName());
            toCityName.setText(itineraryData.getItineraryToCityName());
        }
//// TODO: 04/10/2017 vase show more
        //listener bara inke vaghti maghadir width , height set shod
//        supplierLayoutMore.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int width = supplierLayoutMore.getWidth();
//                int height = supplierLayoutMore.getHeight();
//                if (width > 0 && height > 0) {
//                    VisitedLayout.setVisibility(View.INVISIBLE);
//                    LikeLayout.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
//        getIntrestResponce();
//        String cityid = itineraryData.getItineraryId();
//        String name = Util.getUseRIdFromShareprefrence(getApplicationContext());

//        ratingHolder.setOnClickListener(this);
        changeDateHolder.setOnClickListener(this);
//        rateHolder.setOnClickListener(this);
//        doneHolder.setOnClickListener(this);
//        likeImg.setOnClickListener(this);
//        okImg.setOnClickListener(this);
//        dislikeImg.setOnClickListener(this);
//        nowVisitedImg.setOnClickListener(this);

//        beftorVisitedImg.setOnClickListener(this);
        itineraryId = itineraryData.getItineraryId();
//        bookmarkHolder.setOnClickListener(this);
        showItinerary.setOnClickListener(this);
        MoreInoText.setOnClickListener(this);
        showReservation.setOnClickListener(this);
//        commentHolder.setOnClickListener(this);

        //-------------------map
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Initializing
        MarkerPoints = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        toolsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("position", position + "");

            }

            @Override
            public void onPageSelected(int position) {
//                if (position == 2) {
//                    Intent intent = new Intent(MoreItemItineraryActivity.this, GridActivity.class);
//                    startActivity(intent);
//                }
//                if (position == 0) {
//                    Intent intent = new Intent(MoreItemItineraryActivity.this, GridActivity.class);
//                    startActivity(intent);
//                }
//                if (position == 1) {
//                    Intent intent = new Intent(MoreItemItineraryActivity.this, GridActivity.class);
//                    startActivity(intent);
//                }
                Log.e("position", position + "");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("position", state + "");
            }

        });

        builder = DaggerItineraryComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .itineraryModule(new ItineraryModule(this));
        builder.build().inject(this);
//        itineraryPresenter.getWidgetResult("nodeuser", itineraryData.getItineraryId(), Util.getUseRIdFromShareprefrence(getApplicationContext()), "itinerary", Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_itinerary_item_more;

    }

    private void showToolViewPager() {
//        ShowTavelToolsAdapter showTavelToolsAdapter = new ShowTavelToolsAdapter(getSupportFragmentManager());
        ShowTavelToolsAdapter showTavelToolsAdapter = new ShowTavelToolsAdapter(getApplicationContext(), MoreItemItineraryActivity.this, itineraryId);
        toolsPager.setAdapter(showTavelToolsAdapter);
        toolsPager.setCurrentItem(2);
        toolsPager.setClipToPadding(false);

    }

//    private String getUseRIdFromShareprefrence() {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        String userId = preferences.getString("userId", "");
//        return userId;
////        if(ShareprefrenceName!=null){
////            return true;
////        }else {
////            return false;
////        }
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.commentHolder:
                showProgressDialog();
                builder.build().inject(this);
                itineraryPresenter.getItineraryCommentList("pagecomments", itineraryId, "itinerary", "0", Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                break;

            case R.id.showReservation:
                Intent intent = new Intent(this, ReservationListActivity.class);
                intent.putExtra("itineraryData", (Serializable) itineraryData);
                intent.putExtra("startOfTravel", startOfTravel);
                startActivity(intent);
                break;

            case R.id.changeDateHolder:
                CustomDialogTravel cdd = new CustomDialogTravel(this);
                cdd.show();
                break;

            case R.id.MoreInoText:
                if (showMore) {
                    setWebViewContent(myData);
                    MoreInoText.setText("مطلب کوتاه");
                    showMore = false;
                } else {
                    setWebViewContent(getShowMoreString(myData));
                    MoreInoText.setText("بیشتر بخوانید");
                    showMore = true;
                }

                break;
//            case R.id.ratingHolder:
//                if (ratingHolderFlag) {
//                    builder.build().inject(this);
//                    ratingHolderFlag = itineraryPresenter.doTranslateAnimationUp(ratingHolder, GroupHolder, triangleShowAttraction);
//                }
//                break;
//            case R.id.rateHolder:
//                if (!ratingHolderFlag) {
//                    VisitedLayout.setVisibility(View.INVISIBLE);
//                    LikeLayout.setVisibility(View.VISIBLE);
//                    rotateImage = "rateImg";
//                    builder.build().inject(this);
//                    ratingHolderFlag = itineraryPresenter.doTranslateAnimationDown(ratingHolder, GroupHolder, triangleShowAttraction, supplierLayoutMore.getHeight());
//                    break;
//                } else {
//                    builder.build().inject(this);
//                    ratingHolderFlag = itineraryPresenter.doTranslateAnimationUp(ratingHolder, GroupHolder, triangleShowAttraction);
//                    break;
//                }

//            case R.id.doneHolder:
//                if (!ratingHolderFlag) {
//                    LikeLayout.setVisibility(View.INVISIBLE);
//                    VisitedLayout.setVisibility(View.VISIBLE);
//                    rotateImage = "doneImg";
//                    builder.build().inject(this);
//                    ratingHolderFlag = itineraryPresenter.doTranslateAnimationDown(ratingHolder, GroupHolder, triangleShowAttraction, supplierLayoutMore.getHeight());
//                    break;
//
//                } else {
//                    builder.build().inject(this);
//                    ratingHolderFlag = itineraryPresenter.doTranslateAnimationUp(ratingHolder, GroupHolder, triangleShowAttraction);
//                    break;
//                }

//            case R.id.likeImg:
//                rotateImage = "likeImg";
//                if (LikeValue == 1) {
//                    OnClickedIntrestedWidget("like", Constants.intrestDefault, likeImg);
//                } else {
//                    OnClickedIntrestedWidget("like", Constants.likeImg, likeImg);
//                }
//                break;
//            case R.id.okImg:
//                rotateImage = "okImg";
//                if (LikeValue == 2) {
//                    OnClickedIntrestedWidget("like", Constants.intrestDefault, okImg);
//                } else {
//                    OnClickedIntrestedWidget("like", Constants.okImg, okImg);
//                }
//                break;
//
//            case R.id.dislikeImg:
//                rotateImage = "dislikeImg";
//                if (LikeValue == 2) {
//                    OnClickedIntrestedWidget("like", Constants.intrestDefault, dislikeImg);
//                } else {
//                    OnClickedIntrestedWidget("like", Constants.dislikeImg, dislikeImg);
//                }
//                break;

//            case R.id.nowVisitedImg:
//                rotateImage = "nowVisitedImg";
//
//                if (VisitedValue == 1) {
//                    OnClickedIntrestedWidget("visited", Constants.intrestDefault, nowVisitedImg);
//                } else {
//                    OnClickedIntrestedWidget("visited", Constants.nowVisitedImg, nowVisitedImg);
//                }
//                break;
//
//            case R.id.beftorVisitedImg:
//                rotateImage = "beftorVisitedImg";
//                if (VisitedValue == 2) {
//                    OnClickedIntrestedWidget("visited", Constants.intrestDefault, beftorVisitedImg);
//                } else {
//                    OnClickedIntrestedWidget("visited", Constants.beftorVisitedImg, beftorVisitedImg);
//                }
//                break;

//            case R.id.bookmarkHolder:
//                rotateImage = "bookmarkImg";
//
//                if (BookmarkValue == 1) {
//                    OnClickedIntrestedWidget("bookmark", Constants.intrestDefault, bookmarkImg);
//                } else {
//                    OnClickedIntrestedWidget("bookmark", Constants.bookmarkImg, bookmarkImg);
//                }
//                break;

            case R.id.showItinerary1:
                showProgressDialog();
                builder.build().inject(this);
//                itineraryPresenter.getItineraryAttractionList("attraction", "fa", itineraryId,Util.getTokenFromSharedPreferences(getApplicationContext()),Util.getAndroidIdFromSharedPreferences(getApplicationContext()));
                itineraryPresenter.getItineraryAttractionListDay("attractionday", "fa", itineraryId, Util.getTokenFromSharedPreferences(getApplicationContext()), Util.getAndroidIdFromSharedPreferences(getApplicationContext()));

                break;
        }
    }

    private void OnClickedIntrestedWidget(String gType, String gValue, ImageView imageView) {
        if (!Util.getUseRIdFromShareprefrence(getApplicationContext()).isEmpty()) {
            itineraryPresenter.doWaitingAnimation(imageView);
            itineraryPresenter.getInterest("widget", Util.getUseRIdFromShareprefrence(getApplicationContext()), "1", "itinerary", itineraryId, gType, gValue, Util.getUseRIdFromShareprefrence(getApplicationContext()));
        } else {
            Log.e("user is not login", "error");
            Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
        }
    }

//    private void checkWhichImageIntrested(String imageView) {
//
//        String im = imageView;
//        switch (im) {
//            case "bookmarkImg":
//                bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmarkgreen));
//                break;
//            case "nowVisitedImg":
//                nowVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//                doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//                beftorVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_off));
//
//                break;
//            case "beftorVisitedImg":
//                beftorVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//                doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//                nowVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_off));
//
//                break;
//            case "dislikeImg":
//                dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//                okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_off));
//                likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_off));
//
//                break;
//            case "okImg":
//                okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//                likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_off));
//                dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_off));
//                break;
//            case "likeImg":
//                likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//                dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_off));
//                okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_off));
//                break;
//            default:
//                break;
//        }
//    }

    private String getShowMoreString(String myData) {
        int count = 0;
        int position = 0;
        for (count = 0; count < 40; count++) {
            position = myData.indexOf(" ", position + 1);
        }
        return myData.substring(0, position) + "...";
    }

    private void setWebViewContent(String myData) {
        contentFullDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        contentFullDescription.setLongClickable(false);
        contentFullDescription.setHapticFeedbackEnabled(false);


        String pish = "<html><head><style type=\"text/css\">@font-face {color:#737373;font-family: MyFont;src: url(\"file:///android_asset/fonts/IRANSansMobile.ttf\")}body {font-family: MyFont;font-size: small;text-align: justify;direction:rtl}</style></head><body>";
        String pas = "</body></html>";
        String myHtmlString = pish + myData + pas;
        contentFullDescription.loadDataWithBaseURL(null, myHtmlString, "text/html", "UTF-8", null);
    }

    private void setTypeOfTravel() {
        if (itineraryData.getItineraryTransportId().equals("2830")) {
            itinerary_attraction_type_more.setImageDrawable(getResources().getDrawable(R.mipmap.ic_air_gret));
            txtItinerary_attraction_type.setText("هوایی");
        } else if (itineraryData.getItineraryTransportId().equals("2831")) {
            itinerary_attraction_type_more.setImageDrawable(getResources().getDrawable(R.mipmap.ic_train_grey));
            txtItinerary_attraction_type.setText("ترن");
        } else if (itineraryData.getItineraryTransportId().equals("2829")) {
            itinerary_attraction_type_more.setImageDrawable(getResources().getDrawable(R.mipmap.ic_road_grey));
            txtItinerary_attraction_type.setText("جاده ای");
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MoreItemItineraryActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("لطفا منتظر بمانید");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }

    private void setImageView() {
        if (itineraryData.getItineraryImgUrl() != null) {
            String url = itineraryData.getItineraryImgUrl();

            imgItineraryListMore.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext())
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .listener(new RequestListener<String, GlideDrawable>() {

                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            //// TODO: 22/01/2017  get defeult picture
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imgItineraryListMore)
            ;
        } else {
            Glide.clear(imgItineraryListMore);
            imgItineraryListMore.setImageDrawable(null);

        }
    }

    private void SetPercentage() {
        ArrayList<String> addtypes = new ArrayList<>();
        ArrayList<String> addtypesper = new ArrayList<>();
        ArrayList<String> deleteType = new ArrayList<>();
        ArrayList<String> deleteTypeper = new ArrayList<>();
        deleteType.add("مذهبی");
        deleteType.add("تاریخی و فرهنگی");
        deleteType.add("طبیعی");
        deleteType.add("تفریحی و ورزشی");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        deleteTypeper.add("0");
        for (ItineraryPercentage p : itineraryData.getItineraryPercentage()) {
            addtypes.add(p.getItineraryAttractionType());
            addtypesper.add(p.getItineraryAttractionTypePercentage());
            if (deleteType.contains(p.getItineraryAttractionType())) {
                deleteType.remove(p.getItineraryAttractionType());
            }
        }
        if (addtypes.size() <= 3) {
            for (int i = addtypes.size(); i < 3; i++) {
                addtypes.add(deleteType.get(i - 1));
                addtypesper.add(deleteTypeper.get(i - 1));
            }
        }

        textTpeTravel1.setText(addtypes.get(0));
        textTpeTravel2.setText(addtypes.get(1));
        textTpeTravel3.setText(addtypes.get(2));
        progress1.setProgress(Float.valueOf(addtypesper.get(0)));
        progress2.setProgress(Float.valueOf(addtypesper.get(1)));
        progress3.setProgress(Float.valueOf(addtypesper.get(2)));
        float perc1 = Float.valueOf(addtypesper.get(0));
        float perc2 = Float.valueOf(addtypesper.get(1));
        float perc3 = Float.valueOf(addtypesper.get(2));

        textPercentage1.setText((Util.persianNumbers(String.valueOf((int) perc1)) + "%"));
        textPercentage2.setText((Util.persianNumbers(String.valueOf((int) perc2)) + "%"));
        textPercentage3.setText((Util.persianNumbers(String.valueOf((int) perc3)) + "%"));
    }


    //camera zoom to all of points
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        try {
            if (hasFocus == true) {
                RelativeLayout myLinearLayout = (RelativeLayout) findViewById(R.id.mapHolder);
                int width = myLinearLayout.getWidth();
                int height = myLinearLayout.getHeight();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                if (markers != null) {
                    for (Marker marker : markers) {
                        builder.include(marker.getPosition());
                    }
                }

                LatLngBounds bounds = builder.build();
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
                mMap.animateCamera(cu);
            }

        } catch (Exception e) {

        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "اجازه دسترسی داده نشد", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }


    @Override
    public void showAttraction(ResultItineraryAttractionList resultItineraryAttractionList) {
        itineraryActionList = resultItineraryAttractionList.getResultItineraryAttraction();
        progressDialog.dismiss();
        Intent intent = new Intent(getApplicationContext(), ShowAttractionActivity.class);
        intent.putExtra("ResultItineraryAttraction", (Serializable) itineraryActionList);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        Log.e("error", " in get attraction list" + message);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        if (message.contains("Unable to resolve host ") || message.contains("Software caused connection abort")) {
            Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        }
//        if (message.contains("HTTP 400 BAD REQUEST")) {
//            Toast.makeText(getApplicationContext(), "در این مسیر برنامه سفری یافت نشد", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void showComplete() {
        Log.e("complete", "get attraction list");
        progressDialog.dismiss();
    }

    @Override
    public void showItineraryComment(ResultCommentList resultCommentList, String commentType) {
        List<ResultComment> resultComments = resultCommentList.getResultComment();
        Intent intent = new Intent(MoreItemItineraryActivity.this, CommentListActivity.class);
        intent.putExtra("resultComments", (Serializable) resultComments);
        intent.putExtra("itineraryData", (Serializable) itineraryData);
        intent.putExtra("nextOffset", resultCommentList.getStatistics().getOffsetNext().toString());
        intent.putExtra("fromWhere", commentType);
        startActivity(intent);
        progressDialog.dismiss();
    }

    @Override
    public void setLoadWidget(ResultWidgetFull resultWidgetFull) {
//        if (resultWidgetFull.getResultWidget().get(0).getWidgetBookmarkValue() != null) {
//            BookmarkValue = resultWidgetFull.getResultWidget().get(0).getWidgetBookmarkValue();
//        }
//        if (resultWidgetFull.getResultWidget().get(0).getWidgetLikeValue() != null) {
//            LikeValue = resultWidgetFull.getResultWidget().get(0).getWidgetLikeValue();
//        }
//        if (resultWidgetFull.getResultWidget().get(0).getWidgetVisitedValue() != null) {
//            VisitedValue = resultWidgetFull.getResultWidget().get(0).getWidgetVisitedValue();
//        }
//        if (resultWidgetFull.getResultWidget().get(0).getWidgetWishValue() != null) {
//            WishValue = resultWidgetFull.getResultWidget().get(0).getWidgetWishValue();
//        }
//
//        List<ResultWidget> resultUserLogin = resultWidgetFull.getResultWidget();
//        if (resultUserLogin.get(0).getWidgetBookmarkValue() != null && resultUserLogin.get(0).getWidgetBookmarkValue() == 1) {
//            bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmarkgreen));
//        } else if (resultUserLogin.get(0).getWidgetBookmarkValue() == null || resultUserLogin.get(0).getWidgetBookmarkValue() == 0) {
//            bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmark_grey));
//        }
//        if (resultUserLogin.get(0).getWidgetLikeValue() != null && resultUserLogin.get(0).getWidgetLikeValue() == 1) {
//            likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_on));
//        } else if (resultUserLogin.get(0).getWidgetLikeValue() == null || resultUserLogin.get(0).getWidgetLikeValue() == 0) {
//            likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_like_off));
//        }
//        if (resultUserLogin.get(0).getWidgetLikeValue() != null && resultUserLogin.get(0).getWidgetLikeValue() == 2) {
//            okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_on));
//        } else if (resultUserLogin.get(0).getWidgetLikeValue() == null || resultUserLogin.get(0).getWidgetLikeValue() == 0) {
//            okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_off));
//
//        }
//        if (resultUserLogin.get(0).getWidgetLikeValue() != null && resultUserLogin.get(0).getWidgetLikeValue() == 3) {
//            dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//            rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_on));
//        } else if (resultUserLogin.get(0).getWidgetLikeValue() == null || resultUserLogin.get(0).getWidgetLikeValue() == 0) {
//            dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_off));
//
//        }
//        if (resultUserLogin.get(0).getWidgetVisitedValue() != null && resultUserLogin.get(0).getWidgetVisitedValue() == 1) {
//            nowVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//            doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_now_go_on));
//        }
//        if (resultUserLogin.get(0).getWidgetVisitedValue() != null && resultUserLogin.get(0).getWidgetVisitedValue() == 2) {
//            beftorVisitedImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//            doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_before_go_on));
//        }
    }

    @Override
    public void setIntrestedWidget(InterestResult InterestResult) {

        ResultData resultData = InterestResult.getResultData();
        //// TODO: 14/02/2017
//        rotate.setRepeatCount(0);
//        checkWhichImageIntrested(rotateImage);
//                    bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmark_pink));

    }

    @Override
    public void showAnimationWhenWaiting() {
//        ratingHolderFlag = itineraryPresenter.doTranslateAnimationUp(ratingHolder, GroupHolder, triangleShowAttraction);
    }

    @Override
    public void showAttractionDay(ShowAttractionListItinerary showAttractionListItinerary) {
        resultItineraryAttractionDays = showAttractionListItinerary.getResultItineraryAttractionDay();
        progressDialog.dismiss();
        Intent intent = new Intent(getApplicationContext(), ShowAttractionActivity.class);
        intent.putExtra("resultItineraryAttractionDays", (Serializable) resultItineraryAttractionDays);
        startActivity(intent);
    }

    @Override
    public void setIntrestValue(InterestResult InterestResult) {

    }

    @Override
    public void removeMarkers() {

    }

    @Override
    public void showMarkerAt(float latitude, float longitude) {

    }

    @Override
    public void showDirectionOnMap(PolylineOptions rectLine) {
//        PolylineOptions lineOptions = null;
//        lineOptions = new PolylineOptions();
//
//        lineOptions.width(10);
//        lineOptions.color(getApplicationContext().getResources().getColor(R.color.pink));
        mMap.addPolyline(rectLine).setColor(getApplicationContext().getResources().getColor(R.color.pink));
    }


    public class CustomDialogTravel extends Dialog implements
            View.OnClickListener {

        public Activity c;
        public Dialog d;
        public TextView yes, no;
        PersianDatePicker persianDatePickr;

        public CustomDialogTravel(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //        bara inke keybord bala nayad
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_date_travel);
            persianDatePickr = (PersianDatePicker) findViewById(R.id.travelDate);
            yes = (TextView) findViewById(R.id.txtOk);
            no = (TextView) findViewById(R.id.txtNo);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);
            startOfTravel = persianDatePickr.getDisplayDate();
            persianDatePickr.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(int newYear, int newMonth, int newDay) {
                    txtDate.setText(Utils.getSimpleDate(persianDatePickr.getDisplayDate()));
                    startOfTravel = persianDatePickr.getDisplayDate();
                }
            });
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.txtOk:
                    persianDatePickr.getDisplayDate();
                    dismiss();
//                break;
                    break;
                case R.id.txtNo:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
    }


    //-------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //------------- no zoom
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
//        showmap();
        DaggerItineraryComponent.builder()
                .netComponent(((App) getApplicationContext()).getGoogleNetComponent())
                .itineraryModule(new ItineraryModule(this))
                .build().inject(this);

        //-----------------
        List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
        if (MarkerPoints.size() > 1) {
            MarkerPoints.clear();
            mMap.clear();
        }

        markers = new ArrayList<>();
        MarkerOptions options = new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));

        for (ItineraryLodgingCity lodgingCity : lodgingCities) {
            LatLng point = new LatLng(Float.valueOf(lodgingCity.getCityPositionLat()), Float.valueOf(lodgingCity.getCityPositionLon()));
            MarkerPoints.add(point);
            options.position(point);
            markers.add(mMap.addMarker(options));
        }
        if (MarkerPoints.size() >= 2) {
            for (int j = 0; j < MarkerPoints.size() - 1; j++) {
                String origins = MarkerPoints.get(j).latitude + "," + MarkerPoints.get(j).longitude;
                String destination = MarkerPoints.get(j + 1).latitude + "," + MarkerPoints.get(j + 1).longitude;
                itineraryPresenter.getDirection(origins, destination);
            }
        }


        // Already two locations

//        markers = mapDirection.readytoDirect();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("map is ckicked", "true");
                Intent intent = new Intent(getApplicationContext(), MapFullActivity.class);
                List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
                intent.putExtra("lodgingCities", (Serializable) lodgingCities);
                startActivity(intent);
            }
        });

    }


    //onconnectioncallback
    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //
    @Override
    public void onLocationChanged(Location location) {

//        mLastLocation = location;
//        if (mCurrLocationMarker != null) {
//            mCurrLocationMarker.remove();
//        }
//
//        //Place current location marker
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);
//
//        //move map camera
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
//
//        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }

    }


}
