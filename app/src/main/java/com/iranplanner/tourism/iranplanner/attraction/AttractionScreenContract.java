package com.iranplanner.tourism.iranplanner.attraction;


import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;

/**
 * Created by Hoda
 */
public interface AttractionScreenContract {
    interface View {

        void showAttraction(ResultItineraryAttractionList resultItineraryAttractionList);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void getItineraryAttractionList(String action,
                                        String lang,
                                        String id);
    }


}
