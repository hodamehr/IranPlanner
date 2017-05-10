package com.iranplanner.tourism.iranplanner.mainscreen;


import java.util.List;

import entity.ResultItinerary;
import entity.ResultItineraryList;

/**
 * Created by Hoda
 */
public interface MainSearchScreenContract {
    interface View {
        //        void showItineraries(List<ResultItinerary>  ResultItineraries);
        void showItineraries(ResultItineraryList resultItineraryList, String typeOfSearch);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadItineraryFromCity(String action,
                                   String lang,
                                   String from,
                                   String limit,
                                   String offset,
                                   String to);

        void loadItineraryFromProvince(String action,
                                       String province,
                                       String offset);

        void loadItineraryFromAttraction(String action,
                                         String lang,
                                         String from,
                                         String limit,
                                         String offset,
                                         String attraction);
    }


}
