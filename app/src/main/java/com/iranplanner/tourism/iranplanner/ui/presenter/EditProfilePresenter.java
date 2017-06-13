package com.iranplanner.tourism.iranplanner.ui.presenter;


import com.iranplanner.tourism.iranplanner.ui.presenter.abs.CommentContract;
import com.iranplanner.tourism.iranplanner.ui.presenter.abs.EditProfileContract;

import javax.inject.Inject;

import entity.CommentSend;
import entity.ResultCommentList;
import entity.ResultUpdate;
import entity.updateProfileSend;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hoda on 11-May-16.
 */
public class EditProfilePresenter extends EditProfileContract {

    public Retrofit retrofit;
    View mView;

    @Inject
    public EditProfilePresenter(Retrofit retrofit, View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }


    @Override
    public void initialize() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getEditProfilePostResul(updateProfileSend updateProfileSend, String cid, String androidId) {
        retrofit.create(EditProfileService.class)
                .callEditProfile(updateProfileSend,cid,androidId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultUpdate>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultUpdate resultUpdate) {
                        mView.showEditProfilePostResul(resultUpdate);
                    }
                });
    }

    public interface EditProfileService {


        @POST("api-user.php?action=update")
        Observable<ResultUpdate> callEditProfile(@Body updateProfileSend request, @Query("cid") String cid, @Query("andId") String andId);
    }
}
