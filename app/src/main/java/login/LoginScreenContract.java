package login;

/**
 * Created by h.vahidimehr on 07/05/2017.
 */

public class LoginScreenContract {
    interface View {
//        void showItineraries(ResultItineraryList resultItineraryList);

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
