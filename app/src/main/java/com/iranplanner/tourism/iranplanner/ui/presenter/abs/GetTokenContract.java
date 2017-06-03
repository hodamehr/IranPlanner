package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.ResultPhoneVerify;
import entity.ResultSendPhone;


/**
 * Created by Hoda
 */
public abstract class GetTokenContract extends Presenter<GetTokenContract.View> {
    public interface View {

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
        void getTokenResult(ResultPhoneVerify resultPhoneVerify);
    }
//    api.parsdid.com/iranplanner/app/api-user.php?action=phoneverify&phone=09350891239&token=24443
    public abstract void getToken(String action,
                                         String phone,
                                         String token,
                                         String cid,
                                         String androidID);

}
