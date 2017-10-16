package com.iranplanner.tourism.iranplanner.ui.fragment.myaccount;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.GetInfoReqSend;
import entity.GetInfoResult;
import entity.ResultReservationReqStatus;


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
        void showResultReservationReqStatus(ResultReservationReqStatus resultReservationReqStatus);
    }

    public abstract void getUserInfoPostResult(GetInfoReqSend getInfoReqSend, String cid, String androidId);

//    public abstract void getResultReservationReqStatus(String action,
//                                                       String type,
//                                                       String value,
//                                                       String cid,
//                                                       String androidId);
}
