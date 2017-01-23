package com.iranplanner.tourism.iranplanner.standard;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;

import com.iranplanner.tourism.iranplanner.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Hoda on 10/01/2017.
 */

public class StandardActivity extends AppCompatActivity {
    LocaleUtils localeUtils=new LocaleUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int[] attrs = {R.attr.fontPath};
        TypedArray a = obtainStyledAttributes(R.style.StandardText, attrs);
        String fontPath = a.getString(0);
        a.recycle();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(fontPath)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        localeUtils.setLocale(new Locale("fa"));
        localeUtils.updateConfig((Application) getApplicationContext(), getBaseContext().getResources().getConfiguration());
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeUtils.updateConfig((Application) getApplicationContext(), newConfig);
    }

    /**
     * Attach CalligraphyContextWrapper to context because of new fonts.
     *
     * @param newBase
     */

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    public void loadFragment(FragmentActivity callingActivity, Fragment fragment, int containerId, boolean addToBackStack, int outTransition, int inTransition) {
        FragmentManager supportFragmentManager = callingActivity.getSupportFragmentManager();
        if (!addToBackStack) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.setCustomAnimations(inTransition, outTransition, inTransition, outTransition);
            transaction.replace(containerId, fragment).commitAllowingStateLoss();
        } else {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.setCustomAnimations(inTransition, outTransition, inTransition, outTransition);
            transaction.replace(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }
    }

    public void loadFragment(FragmentActivity callingActivity, Fragment fragment, int containerId, boolean addToBackStack, int outTransition, int inTransition, int outPopTransition, int inPopTransition) {
        FragmentManager supportFragmentManager = callingActivity.getSupportFragmentManager();
        if (!addToBackStack) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.setCustomAnimations(inTransition, outTransition, inPopTransition, outPopTransition);
            transaction.replace(containerId, fragment).commit();
        } else {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.setCustomAnimations(inTransition, outTransition, inPopTransition, outPopTransition);
            transaction.replace(containerId, fragment).addToBackStack(fragment.getClass().getName()).commit();
        }
    }

    private boolean encrypted = false;

//    public LoginResponse getFromPreferences() {
//        String responseStr = ApplicationContext.getInstance().getFromPreferences(Constants.PREF_LOGIN_RESPONSE, "", encrypted);
//        String[] split = responseStr.split(";");
//        if (split.length != 5) {
//            return null;
//        }
//
//        boolean isSuccessful = Boolean.parseBoolean(split[0]);
//        long userId = Long.parseLong(split[1]);
//        long sessionId = Long.parseLong(split[2]);
//        long sessionSecret = Long.parseLong(split[3]);
//        boolean filledProfile = Boolean.parseBoolean(split[4]);
//
//        LoginResponse lastLoginResponse = new LoginResponse();
//        lastLoginResponse.UserId = userId;
//        lastLoginResponse.sessionId = sessionId;
//        lastLoginResponse.sessionSecret = sessionSecret;
//        lastLoginResponse.isSuccessful = isSuccessful;
//        lastLoginResponse.filledProfile = filledProfile;
//        return lastLoginResponse;
//    }

//    /**
//     * This method is just for backward compatibility and will not available in release version
//     */
//    @Deprecated
//    private LoginResponse getLoginResponseFromDatabase() {
//        return loginService.getLastLoginResponse();
//    }

//    public void saveInPreferences(LoginResponse lastLoginResponse) {
//        String combined = String.valueOf(lastLoginResponse.isSuccessful) + ";";
//        combined += String.valueOf(lastLoginResponse.UserId) + ";";
//        combined += String.valueOf(lastLoginResponse.sessionId) + ";";
//        combined += String.valueOf(lastLoginResponse.sessionSecret) + ";";
//        combined += String.valueOf(lastLoginResponse.filledProfile);
//        ApplicationContext.getInstance().saveInPreferences(Constants.PREF_LOGIN_RESPONSE, combined, encrypted);
//    }

//    @Override
//    protected void onPause() {
//        clearReferences();
//        super.onPause();
//    }
//
//    @Override
//    protected synchronized void onResume() {
//        super.onResume();
//        ApplicationContext.getInstance().setCurrentActivity(this);
//    }
//
//    @Override
//    protected void onDestroy() {
////        SQLiteStudioService.instance().stop();
//        clearReferences();
//        super.onDestroy();
//    }
//
//    private void clearReferences() {
//        Activity currActivity = ApplicationContext.getInstance().getCurrentActivity();
//        if (this.equals(currActivity))
//            ApplicationContext.getInstance().setCurrentActivity(null);
//    }


    public int getActionBarSize() {
        return (int) getResources().getDimension(R.dimen.actionBarSize);
    }

    public class LocaleUtils {
        private  Locale sLocale;

        public  void setLocale(Locale locale) {
            sLocale = locale;
            if(sLocale != null) {
                Locale.setDefault(sLocale);
            }
        }

        public  void updateConfig(ContextThemeWrapper wrapper) {
            if(sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration configuration = new Configuration();
                configuration.setLocale(sLocale);
                wrapper.applyOverrideConfiguration(configuration);
            }
        }

        public  void updateConfig(Application app, Configuration configuration) {
            if (sLocale != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                //Wrapping the configuration to avoid Activity endless loop
                Configuration config = new Configuration(configuration);
                // We must use the now-deprecated config.locale and res.updateConfiguration here,
                // because the replacements aren't available till API level 24 and 17 respectively.
                config.locale = sLocale;
                Resources res = app.getBaseContext().getResources();
                res.updateConfiguration(config, res.getDisplayMetrics());
            }
        }
    }


}
