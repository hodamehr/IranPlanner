package com.iranplanner.tourism.iranplanner.ui.fragment.myaccount;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.GetInfoReqSend;
import entity.GetInfoResult;


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
