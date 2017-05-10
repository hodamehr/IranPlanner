package com.iranplanner.tourism.iranplanner.attraction;


import com.iranplanner.tourism.iranplanner.mainscreen.MainSearchScreenContract;

import javax.inject.Inject;

import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Hoda on 11-May-16.
 */
public class AttractionScreenPresenter implements AttractionScreenContract.Presenter {

    public Retrofit retrofit;
    AttractionScreenContract.View mView;

    @Inject
    public AttractionScreenPresenter(Retrofit retrofit, AttractionScreenContract.View mView) {
        this.retrofit = retrofit;
        this.mView = mView;
    }

    @Override
    public void getItineraryAttractionList(String action, String lang, String id) {
        retrofit.create(ItineraryService.class).getItineraryAttractionList(action,lang,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<ResultItineraryAttractionList>() {

                    @Override
                    public void onCompleted() {
                        mView.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(ResultItineraryAttractionList resultItineraryAttractionList) {
                        mView.showAttraction(resultItineraryAttractionList);
                    }
                });
    }


    public interface ItineraryService {
        @GET("api-itinerary.php")
        Observable<ResultItineraryAttractionList> getItineraryAttractionList(
                @Query("action") String action,
                @Query("lang") String lang,
                @Query("id") String id
        );

    }
}
