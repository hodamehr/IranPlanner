package server;

import entity.ResultItineraryAttraction;
import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Hoda on 10/01/2017.
 */
public interface getJsonInterface {
    @GET("api-itinerary.php?action=list&lang=fa&from=342&limit=10&offset=0")
    Call<ResultItineraryList> getItinerarys(@Query("action") String param1,
                                            @Query("lang") String param2,
                                            @Query("from") String param3,
                                            @Query("limit") String param4,
                                            @Query("offset") String param5);


    @GET("api-itinerary.php")
    Call<ResultItineraryAttractionList> getItineraryAttractionList(
            @Query("action") String param1,
            @Query("lang") String param2,
            @Query("id") String param3
    );
}
