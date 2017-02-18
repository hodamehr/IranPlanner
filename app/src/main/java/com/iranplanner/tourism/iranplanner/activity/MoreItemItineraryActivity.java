package com.iranplanner.tourism.iranplanner.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardActivity;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.InterestResult;
import entity.ItineraryLodgingCity;
import entity.ItineraryPercentage;
import entity.ResultData;
import entity.ResultItinerary;
import entity.ResultItineraryAttraction;
import entity.ResultItineraryAttractionList;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import server.getJsonInterface;
import tools.MapDirection;
import tools.Util;
import tools.widget.PersianDatePicker;

public class MoreItemItineraryActivity extends StandardActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, Callback<ResultItineraryAttractionList>, Animation.AnimationListener, View.OnClickListener {

    private GoogleMap mMap;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    ResultItinerary itineraryData;
    private String itineraryId;
    List<ResultItineraryAttraction> itineraryActionList;
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
    TextView txtOk;
    LinearLayout rateHolder, bookmarkHolder, doneHolder, nowVisitedHolder, beftorVisitedHolder, likeHolder, okHolder, dislikeHolder;
    RelativeLayout ratingHolder, GroupHolder, supplierLayoutMore, VisitedLayout, LikeLayout, changeDateHolder;
    PersianCalendar persianCurrentDate;
    ImageView bookmarkImg, doneImg, dislikeImg, okImg, likeImg, rateImg, beftorVisitedImg, nowVisitedImg, wishImg, triangleShowAttraction;
    RotateAnimation rotate;
    String rotateImage;
    Animation translateAnimation;
    boolean ratingHolderFlag = false;



    private void findView() {
        setContentView(R.layout.fragment_itinerary_item_more);
        txtItinerary_attraction_Difficulty = (TextView) findViewById(R.id.txtItinerary_attraction_Difficulty);
        txtItinerary_attraction_type = (TextView) findViewById(R.id.txtItinerary_attraction_type);
        txtItinerary_count_attraction = (TextView) findViewById(R.id.txtItinerary_count_attraction);
        itineraryDuration = (TextView) findViewById(R.id.itineraryDuration);
        fromCityName = (TextView) findViewById(R.id.fromCityName);
        toCityName = (TextView) findViewById(R.id.toCityName);
        itinerary_attraction_type_more = (ImageView) findViewById(R.id.itinerary_attraction_type_more);
        showItinerys = (TextView) findViewById(R.id.showItinerys);
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
        rateHolder = (LinearLayout) findViewById(R.id.rateHolder);
        doneHolder = (LinearLayout) findViewById(R.id.doneHolder);
        nowVisitedHolder = (LinearLayout) findViewById(R.id.nowVisitedHolder);
        beftorVisitedHolder = (LinearLayout) findViewById(R.id.beftorVisitedHolder);
        dislikeHolder = (LinearLayout) findViewById(R.id.dislikeHolder);
        okHolder = (LinearLayout) findViewById(R.id.okHolder);
        likeHolder = (LinearLayout) findViewById(R.id.likeHolder);
        bookmarkHolder = (LinearLayout) findViewById(R.id.bookmarkHolder);
        ratingHolder = (RelativeLayout) findViewById(R.id.ratingHolder);
        GroupHolder = (RelativeLayout) findViewById(R.id.GroupHolder);
        VisitedLayout = (RelativeLayout) findViewById(R.id.VisitedLayout);
        LikeLayout = (RelativeLayout) findViewById(R.id.LikeLayout);
        changeDateHolder = (RelativeLayout) findViewById(R.id.changeDateHolder);
        supplierLayoutMore = (RelativeLayout) findViewById(R.id.supplierLayoutMore);
        imgItineraryListMore = (ImageView) findViewById(R.id.imgItineraryListMore);
        contentFullDescription = (CTouchyWebView) findViewById(R.id.contentFullDescription);
        txtOk = (TextView) findViewById(R.id.txtOk);
        bookmarkImg = (ImageView) findViewById(R.id.bookmarkImg);
        doneImg = (ImageView) findViewById(R.id.doneImg);

        dislikeImg = (ImageView) findViewById(R.id.dislikeImg);
        okImg = (ImageView) findViewById(R.id.okImg);
        likeImg = (ImageView) findViewById(R.id.likeImg);
        rateImg = (ImageView) findViewById(R.id.rateImg);
        beftorVisitedImg = (ImageView) findViewById(R.id.beftorVisitedImg);
        nowVisitedImg = (ImageView) findViewById(R.id.nowVisitedImg);
        wishImg = (ImageView) findViewById(R.id.wishImg);
        triangleShowAttraction = (ImageView) findViewById(R.id.triangleShowAttraction);
    }
//wish visited like

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findView();


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        itineraryData = (ResultItinerary) bundle.getSerializable("itineraryData");
        String duration = bundle.getString("duration");
        setTypeOfTravel();
        setWebViewContent();
        SetPercentage();
        setImageView();
//---------------- set current date
        long time = System.currentTimeMillis();
        txtDate.setText(Utils.getSimpleDateMilli(time));


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



        //listener bara inke vaghti maghadir width , height set shod
        supplierLayoutMore.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = supplierLayoutMore.getWidth();
                int height = supplierLayoutMore.getHeight();
                if (width > 0 && height > 0) {
                    VisitedLayout.setVisibility(View.INVISIBLE);
                    LikeLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
        ratingHolder.setOnClickListener(this);
        changeDateHolder.setOnClickListener(this);
        rateHolder.setOnClickListener(this);
        doneHolder.setOnClickListener(this);
        likeImg.setOnClickListener(this);
        okImg.setOnClickListener(this);
        dislikeImg.setOnClickListener(this);
        nowVisitedImg.setOnClickListener(this);
        wishImg.setOnClickListener(this);
        beftorVisitedImg.setOnClickListener(this);
        itineraryId = itineraryData.getItineraryId();
        bookmarkHolder.setOnClickListener(this);
        showItinerys.setOnClickListener(this);
        //-------------------map
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }
        // Initializing
        MarkerPoints = new ArrayList<>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private String getUseRIdFromShareprefrence() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String userId = preferences.getString("userId", "");
        return userId;
//        if(ShareprefrenceName!=null){
//            return true;
//        }else {
//            return false;
//        }

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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.changeDateHolder:
                CustomDialogTravel cdd = new CustomDialogTravel(this);
                cdd.show();
                break;
            case R.id.ratingHolder:
                if (ratingHolderFlag) {
                    translateUp();
                }
                break;
            case R.id.rateHolder:
                VisitedLayout.setVisibility(View.INVISIBLE);
                LikeLayout.setVisibility(View.VISIBLE);
                rotateImage = "rateImg";
                translateDown();
                break;
            case R.id.doneHolder:
                LikeLayout.setVisibility(View.INVISIBLE);
                VisitedLayout.setVisibility(View.VISIBLE);
                rotateImage = "doneImg";
                translateDown();
                break;
            case R.id.likeImg:
                rotateImage = "likeImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(likeImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "1", "like");

                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.okImg:
                rotateImage = "okImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(okImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "2", "like");

                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.dislikeImg:
                rotateImage = "dislikeImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(dislikeImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "3", "like");

                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.wishImg:
                rotateImage = "wishImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(nowVisitedImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "1", "wish");
                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.beftorVisitedImg:
                rotateImage = "beftorVisitedImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(beftorVisitedImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "2", "visited");
                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bookmarkHolder:
                rotateImage = "bookmarkImg";
                if (!getUseRIdFromShareprefrence().isEmpty()) {
                    animWaiting(bookmarkImg);
                    String uid = getUseRIdFromShareprefrence();
                    getInterestResult(uid, itineraryId, "1", "bookmark");

                } else {
                    Log.e("user is not login", "error");
                    Toast.makeText(getApplicationContext(), "شما به حساب کاربری خود وارد نشده اید", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.showItinerys:
                Log.e("open itinerarylist", "open");
                getAttraction(itineraryId);
                showProgressDialog();
                break;


        }
    }


    public void getInterestResult(String uid, String nid, String gvalue, String gtype) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(setHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface getJsonInterface = retrofit.create(server.getJsonInterface.class);
        Call<InterestResult> call = getJsonInterface.getInterest("widget", uid, "1", "itinerary", nid, gtype, gvalue);
        call.enqueue(new Callback<InterestResult>() {
            @Override
            public void onResponse(Call<InterestResult> call, Response<InterestResult> response) {
                if (response.body() != null) {
                    InterestResult jsonResponse = response.body();
                    ResultData resultData = jsonResponse.getResultData();
                    //// TODO: 14/02/2017
                    rotate.setRepeatCount(0);
                    checkWhichImageIntrested(rotateImage);
//                    bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmark_pink));
                } else {
                    Log.e("Responce body", "null");
                }
            }

            @Override
            public void onFailure(Call<InterestResult> call, Throwable t) {

            }
        });

    }

    private void checkWhichImageIntrested(String imageView) {

        String im = imageView;
        switch (im) {
            case "bookmarkImg":
                bookmarkImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_bookmark_pink));
                break;
            case "nowVisitedImg":
                doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_done_pink));
                translateUp();
                break;
            case "beftorVisitedImg":
                doneImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_done_pink));
                translateUp();
                break;
            case "dislikeImg":
                dislikeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_pink));
                translateUp();
                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_dislike_pink));
                break;
            case "okImg":
                okImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_pink));
                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_ok_pink));
                translateUp();

                break;
            case "likeImg":
                likeImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_air));
                rateImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_air));
                translateUp();

                break;
            case "wishImg":
                wishImg.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_wish_pink));
                break;
            default:
                break;
        }
    }

    private void animWaiting(ImageView image) {
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setRepeatCount(5);
        rotate.setDuration(5000);
        rotate.setInterpolator(new LinearInterpolator());
        image.startAnimation(rotate);

    }

    private void translateDown() {

        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(ratingHolder, "translationY", supplierLayoutMore.getHeight()),
                ObjectAnimator.ofFloat(GroupHolder, "translationY", supplierLayoutMore.getHeight()),
                ObjectAnimator.ofFloat(triangleShowAttraction, "translationY", -55));
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
        ratingHolderFlag = true;

    }

    private void translateUp() {
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(
                ObjectAnimator.ofFloat(ratingHolder, "translationY", 0),
                ObjectAnimator.ofFloat(GroupHolder, "translationY", 0),
                ObjectAnimator.ofFloat(triangleShowAttraction, "translationY", 0));
        mAnimatorSet.setDuration(1000);
        mAnimatorSet.start();
        ratingHolderFlag = false;
    }
//    private void slideDownAnimation() {
////        TranslateAnimation anim = new TranslateAnimation(0,0,-(supplierLayoutMore.getHeight()),supplierLayoutMore.getHeight());
//        TranslateAnimation anim1 = new TranslateAnimation(0, 0, -(supplierLayoutMore.getHeight()), 0);
//        anim1.setDuration(1000);
//        anim1.setFillAfter(true);
//        ratingHolder.startAnimation(anim1);
//        ratingHolderFlag = true;
//        GroupHolder.startAnimation(anim1);
//    }

    //
//
//    private Animation slideUpAnimation(int duration) {
//        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, -(supplierLayoutMore.getHeight()));
//        anim.setDuration(duration);
//        anim.setFillAfter(true);
//        ratingHolder.startAnimation(anim);
//        ratingHolderFlag = false;
//        GroupHolder.startAnimation(anim);
//        return anim;
//    }

    private void setWebViewContent() {
        contentFullDescription.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        contentFullDescription.setLongClickable(false);
        contentFullDescription.setHapticFeedbackEnabled(false);
        String myData = itineraryData.getItineraryBody();
        String pish = "<html><head><style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/IRANSansMobile.ttf\")}body {font-family: MyFont;font-size: medium;text-align: justify;}</style></head><body>";
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

    /*   private void setMonth() {

           Button farvardin = (Button) findViewById(R.id.farvardin);
           Button ordibehesht = (Button) findViewById(R.id.ordibehesht);
           Button khordad = (Button) findViewById(R.id.khordad);
           Button tir = (Button) findViewById(R.id.tir);
           Button mordad = (Button) findViewById(R.id.mordad);
           Button shahrivar = (Button) findViewById(R.id.shahrivar);
           Button mehr = (Button) findViewById(R.id.mehr);
           Button aban = (Button) findViewById(R.id.aban);
           Button azar = (Button) findViewById(R.id.azar);
           Button dey = (Button) findViewById(R.id.dey);
           Button bahman = (Button) findViewById(R.id.bahman);
           Button esfand = (Button) findViewById(R.id.esfand);
           for (ItinerarySeasson itinerarySeasson : itineraryData.getItinerarySeasson()) {
               String month = itinerarySeasson.getMonthName();
               switch (month) {
                   case "1":
                       farvardin.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "2":
                       ordibehesht.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "3":
                       khordad.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "4":
                       tir.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "5":
                       mordad.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "6":
                       shahrivar.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "7":
                       mehr.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "8":
                       aban.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "9":
                       azar.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "10":
                       dey.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "11":
                       bahman.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   case "12":
                       esfand.setBackground(getDrawable(R.drawable.circle_stroke));
                       break;
                   default:
                       farvardin.setBackground(getDrawable(R.drawable.circle_tohi));
                       break;
               }
           }


       }*/

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //------------- no zoom
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(false);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

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
        List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
        if (MarkerPoints.size() > 1) {
            MarkerPoints.clear();
            mMap.clear();
        }
        MapDirection mapDirection = new MapDirection(mMap, getApplicationContext(), lodgingCities, MarkerPoints);


        // Already two locations

        markers = mapDirection.readytoDirect();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Log.e("map is ckicked", "true");
                Intent intent = new Intent(getApplicationContext(), MapFullActivity.class);
//                intent.putExtra("itineraryData", (Serializable) itineraryData);
//                intent.putExtra("fromWhere", "itinerary");
                List<ItineraryLodgingCity> lodgingCities = itineraryData.getItineraryLodgingCity();
                intent.putExtra("lodgingCities", (Serializable) lodgingCities);
                startActivity(intent);
            }
        });

    }

    //camera zoom to all of points
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus == true) {
            RelativeLayout myLinearLayout = (RelativeLayout) findViewById(R.id.mapHolder);
            int width = myLinearLayout.getWidth();
            int height = myLinearLayout.getHeight();
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (Marker marker : markers) {
                builder.include(marker.getPosition());
            }
            LatLngBounds bounds = builder.build();
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            mMap.animateCamera(cu);
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

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

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
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }


    public void getAttraction(String itineraryId) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.parsdid.com/iranplanner/app/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        getJsonInterface stackOverflowAPI = retrofit.create(getJsonInterface.class);
        Call<ResultItineraryAttractionList> call = stackOverflowAPI.getItineraryAttractionList("attraction", "fa", itineraryId);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<ResultItineraryAttractionList> call, Response<ResultItineraryAttractionList> response) {
        Log.e("ok", "ResultItineraryAttractionList");

        if (response.body() != null) {
            ResultItineraryAttractionList jsonResponse = response.body();
            itineraryActionList = jsonResponse.getResultItineraryAttraction();
            progressDialog.dismiss();
            Intent intent = new Intent(getApplicationContext(), ShowAttractionActivity.class);
            intent.putExtra("ResultItineraryAttraction", (Serializable) itineraryActionList);
            startActivity(intent);

//            ResultItineraryList jsonResponse = response.body();
//            List<ResultItinerary> data = jsonResponse.getResultItinerary();

//            ItineraryListFragment itineraryListFragment = new ItineraryListFragment();
//            Bundle bundle = new Bundle();
//            bundle.putSerializable("ResultItineraryAttraction", (Serializable) itineraryActionList);
//            itineraryListFragment.setArguments(bundle);
//            loadFragment(this, itineraryListFragment, R.id.containerCityCity, true, 0, 0);

//            for (ResultItineraryAttraction attraction.json : itineraryActionList) {
//                for (int a = 0; a <= itineraryActionList.size(); a++) {
//                    if (attraction.json.getItineraryDayplanName() == String.valueOf(a)) {
//
//                    }
//                }
//            }
        } else {
            Log.e("Responce body", "null");
        }

    }

    @Override
    public void onFailure(Call<ResultItineraryAttractionList> call, Throwable t) {

        Log.e("Responce  body", "itinerary detail null");
        Toast.makeText(getApplicationContext(), "عدم دسترسی به اینترنت", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public class CustomDialogTravel extends Dialog implements
            android.view.View.OnClickListener {

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
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_date_travel);
            persianDatePickr= (PersianDatePicker) findViewById(R.id.travelDate);
            yes = (TextView) findViewById(R.id.txtOk);
            no = (TextView) findViewById(R.id.txtNo);
            yes.setOnClickListener(this);
            no.setOnClickListener(this);
            persianDatePickr.setOnDateChangedListener(new PersianDatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(int newYear, int newMonth, int newDay) {
                    txtDate.setText(Utils.getSimpleDate(persianDatePickr.getDisplayDate()));
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
}
