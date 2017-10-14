package com.iranplanner.tourism.iranplanner.ui.activity.forgetPassword;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResetPassword;
import entity.SendEmailResetPassword;


/**
 * Created by Hoda
 */
public abstract class ForgetPasswordContract extends Presenter<ForgetPasswordContract.View> {
    public interface View {
        void showForgetPassword(ResetPassword resetPassword);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }



    public abstract void  getResetPassword(SendEmailResetPassword SendEmailResetPassword, String token,
                                           String androidId);


}
