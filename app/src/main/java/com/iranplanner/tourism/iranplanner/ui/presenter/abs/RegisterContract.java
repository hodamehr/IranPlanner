package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.RegisterReqSend;
import entity.ResultRegister;


/**
 * Created by Hoda
 */
public abstract class RegisterContract extends Presenter<RegisterContract.View> {
    public interface View {
        void showRegisterMessage(ResultRegister resultRegister);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }

    public abstract void getRegisterResult(String action, String email, String password, String fname, String lname, String gender, String cid, String phone);
    public abstract void getRegisterLoginResult(RegisterReqSend registerReqSend,String cid , String andId);

}
