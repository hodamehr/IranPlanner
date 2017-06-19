package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.EmailVerifyReq;
import entity.LoginReqSend;
import entity.LoginResult;
import entity.ResultUpdate;
import entity.ResultUpdateReturn;
import entity.ResultVerifyEmail;
import entity.updateProfileSend;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Hoda
 */
public abstract class EditProfileContract extends Presenter<EditProfileContract.View> {
    public interface View {
        void showEditProfilePostResul(ResultUpdateReturn resultUpdate);
        void showVerifyEmailResult( ResultVerifyEmail resultVerifyEmail);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }

    public abstract void getEditProfilePostResul(updateProfileSend request, String cid,
                                           String androidId);
    public abstract void getVerifyEmail(EmailVerifyReq request, String cid, String andId);


}
