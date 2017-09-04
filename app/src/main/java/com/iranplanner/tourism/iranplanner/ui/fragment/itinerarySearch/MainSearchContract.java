package com.iranplanner.tourism.iranplanner.ui.fragment.itinerarySearch;


import entity.ResultItineraryList;

/**
 * Created by Hoda
 */
public abstract class MainSearchContract {
    public interface View {
        //        void showItineraries(List<ResultItinerary>  ResultItineraries);
        void showItineraries(ResultItineraryList resultItineraryList, String typeOfSearch);

        void showError(String message);

        void showComplete();
        void showProgress();
        void dismissProgress();
    }


    public abstract void loadItineraryFromCity(String action,
                                               String lang,
                                               String from,
                                               String limit,
                                               String offset,
                                               String to,
                                               String cid,
                                               String andID);

    public abstract void loadItineraryFromProvince(String action,
                                                   String province,
                                                   String offset,
                                                   String cid,
                                                   String andID);

    public abstract void loadItineraryFromAttraction(String action,
                                                     String lang,
                                                     String from,
                                                     String limit,
                                                     String offset,
                                                     String attraction,
                                                     String cid,
                                                     String andID);


}
