package com.iranplanner.tourism.iranplanner.ui.activity.forgetPassword;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResetValueForget;
import entity.ResultResetPassword;


/**
 * Created by Hoda
 */
public abstract class ForgetPassContract extends Presenter<ForgetPassContract.View> {
    public interface View {

void showResetPassword(ResultResetPassword resultResetPassword);
        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }



    public abstract void sendResetPass( ResetValueForget resetValueForget,
                                                 String token,
                                                 String androidId);


}