package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import com.google.firebase.auth.UserInfo;
import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.GetInfoReqSend;
import entity.GetInfoResult;
import entity.InfoResult;
import entity.LoginReqSend;
import entity.LoginResult;


/**
 * Created by Hoda
 */
public abstract class SettingContract extends Presenter<SettingContract.View> {
    public interface View {
        void showInfoUserResult(GetInfoResult infoResult);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }

    public abstract void getUserInfoPostResult(GetInfoReqSend getInfoReqSend, String cid, String androidId);
}
