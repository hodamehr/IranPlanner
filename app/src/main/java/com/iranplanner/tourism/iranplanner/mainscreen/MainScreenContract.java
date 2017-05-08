package com.iranplanner.tourism.iranplanner.mainscreen;




import java.util.List;

import entity.ResultItinerary;
import entity.ResultItineraryList;

/**
 * Created by Hoda
 */
public interface MainScreenContract {
    interface View {
//        void showItineraries(List<ResultItinerary>  ResultItineraries);
        void showItineraries(ResultItineraryList resultItineraryList);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadItinerary(String action,
                           String lang,
                           String from,
                           String limit,
                           String offset,
                           String to);
    }
}
