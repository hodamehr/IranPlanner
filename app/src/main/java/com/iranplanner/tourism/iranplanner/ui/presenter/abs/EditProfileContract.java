package com.iranplanner.tourism.iranplanner.ui.presenter.abs;

import com.iranplanner.tourism.iranplanner.ui.presenter.Presenter;

import entity.LoginReqSend;
import entity.LoginResult;
import entity.ResultUpdate;
import entity.updateProfileSend;


/**
 * Created by Hoda
 */
public abstract class EditProfileContract extends Presenter<EditProfileContract.View> {
    public interface View {
        void showEditProfilePostResul(ResultUpdate resultUpdate);

        void showError(String message);

        void showComplete();

        void showProgress();

        void dismissProgress();
    }

    public abstract void getEditProfilePostResul(updateProfileSend request, String cid,
                                           String androidId);
}
