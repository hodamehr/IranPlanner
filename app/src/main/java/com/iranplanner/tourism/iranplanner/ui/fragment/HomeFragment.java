package com.iranplanner.tourism.iranplanner.ui.fragment;


import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iranplanner.tourism.iranplanner.R;
import com.iranplanner.tourism.iranplanner.standard.StandardFragment;

import java.util.List;

import autoComplet.MyFilterableAdapterCityProvince;
import autoComplet.MyFilterableAdapterProvince;
import autoComplet.ReadJsonCityProvince;
import autoComplet.ReadJsonProvince;
import entity.CityProvince;
import entity.Province;
import tools.Util;


/**
 * Created by phelat on 9/29/16.
 */
public class HomeFragment extends StandardFragment implements View.OnClickListener {
    int width;
    int height;
    boolean anim = false;
    Button aboutCityBtn;
    LinearLayout card_view_province_list;
    TextView txtWhereGo;
    boolean showContentProvince = true;
    View toolbarFeatureElevation;
    String cityProvinceName;
    private String cityProvinceId;
    List<CityProvince> tempCityProvince;
    NestedScrollView scroller;

    public HomeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        AppBarLayout appBarLayout = (AppBarLayout) rootView.findViewById(R.id.app_bar);
        final LinearLayout featureHolder = (LinearLayout) rootView.findViewById(R.id.featureHolder);
        aboutCityBtn = (Button) rootView.findViewById(R.id.aboutCityBtn);
        card_view_province_list = (LinearLayout) rootView.findViewById(R.id.card_view_province_list);
        txtWhereGo = (TextView) rootView.findViewById(R.id.txtWhereGo);
        LinearLayout btnShowProvince = (LinearLayout) rootView.findViewById(R.id.btnShowProvince);
        btnShowProvince.setOnClickListener(this);
        featureHolder.getHeight();
        featureHolder.getScaleY();
        featureHolder.getY();
        ViewTreeObserver vto = featureHolder.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    featureHolder.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    featureHolder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                width = featureHolder.getMeasuredWidth();
                height = featureHolder.getMeasuredHeight();

            }
        });
        final RelativeLayout frameLayout = (RelativeLayout) rootView.findViewById(R.id.featureListRelativeLayout);
        final LinearLayout featureListHolder = (LinearLayout) rootView.findViewById(R.id.featureListHolder);
        toolbarFeatureElevation = (View) rootView.findViewById(R.id.toolbarFeatureElevation);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                appBarLayout.getTotalScrollRange();
                if (verticalOffset <= (-1) * (appBarLayout.getTotalScrollRange())) {
//                    featureListHolder.setVisibility(View.VISIBLE);
//                    frameLayout.setVisibility(View.VISIBLE);
//                    toolbarFeatureElevation.setVisibility(View.VISIBLE);
                } else if (verticalOffset > (-1) * appBarLayout.getTotalScrollRange()) {
//                    featureListHolder.setVisibility(View.INVISIBLE);
//                    frameLayout.setVisibility(View.INVISIBLE);
//                    toolbarFeatureElevation.setVisibility(View.GONE);
                }
            }
        });
        scroller = (NestedScrollView) rootView.findViewById(R.id.nestedScrollView);

        if (scroller != null) {

            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY > oldScrollY) {
                        Log.i("Scroll DOWN", "Scroll DOWN");
                        if (scrollY > height && anim == false) {
                            frameLayout.setBackgroundColor(getResources().getColor(R.color.background));
                            Animation animate1 = new AlphaAnimation(0.2f, 1.0f);
                            animate1.setDuration(1000);
                            animate1.setFillAfter(true);
                            featureListHolder.startAnimation(animate1);
                            featureListHolder.setVisibility(View.VISIBLE);
                            frameLayout.setVisibility(View.VISIBLE);
                            toolbarFeatureElevation.setVisibility(View.VISIBLE);
                            anim = true;
                            scroller.smoothScrollTo(0, scrollY + 170);
                        }
                    }
                    if (scrollY < oldScrollY && scrollY <= height) {
                        Log.i("Scroll UP", "Scroll UP");
                        frameLayout.setVisibility(View.INVISIBLE);
                        featureListHolder.setVisibility(View.GONE);
                        anim = false;
                    }

                    if (scrollY == 0) {
                        Log.i("TOP DOWN", "TOP SCROLL");
                    }

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        Log.i("SCROLL", "BOTTOM SCROLL");
                    }
                }
            });
        }
        aboutCityBtn.setOnClickListener(this);
        txtWhereGo.setOnClickListener(this);
        return rootView;
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aboutCityBtn:
                AboutCityFragment aboutCityFragment = AboutCityFragment.newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.containerHomeFragment, aboutCityFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.btnShowProvince:
                onClickShowProvince(card_view_province_list);
                break;
            case R.id.txtWhereGo:
                CustomDialogSearchLocation customDialogSearchLocation = new CustomDialogSearchLocation(getActivity());

                customDialogSearchLocation.show();
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(customDialogSearchLocation.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                customDialogSearchLocation.show();
                customDialogSearchLocation.getWindow().setAttributes(lp);
                customDialogSearchLocation.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
                break;
        }
    }

    private void onClickShowProvince(LinearLayout card_view_province) {
        if (showContentProvince) {
            card_view_province.setVisibility(View.VISIBLE);
            showContentProvince = false;
        } else {
            card_view_province.setVisibility(View.GONE);
            showContentProvince = true;
        }
    }

    public class CustomDialogSearchLocation extends Dialog implements
            View.OnClickListener {

        public Activity c;
        public Dialog d;
        public TextView no;
        ListView listd;

        public CustomDialogSearchLocation(Activity a) {
            super(a);
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

//            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog_home_search_location);
            AutoCompleteTextView autoTextWhere = (AutoCompleteTextView) findViewById(R.id.autoTextWhere);
            listd = (ListView) findViewById(R.id.listd);
            no = (TextView) findViewById(R.id.txtNo);
            no.setOnClickListener(this);
            tempCityProvince = autoCompleteProvince(autoTextWhere);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.txtNo:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }


        List<CityProvince> provinces;

        public List<CityProvince> autoCompleteProvince(AutoCompleteTextView textProvience) {
            ReadJsonCityProvince readJsonProvince = new ReadJsonCityProvince();
            provinces = readJsonProvince.getListOfCityProvience(getContext());
            MyFilterableAdapterCityProvince adapter = new MyFilterableAdapterCityProvince(getContext(), android.R.layout.simple_list_item_1, provinces);
            textProvience.setAdapter(adapter);
            listd.setAdapter(adapter);
            return provinces;
        }

    }


}
