package server;

import entity.CommentSend;
import entity.InterestResult;
import entity.LoginResult;
import entity.ResultComment;
import entity.ResultCommentList;
import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;
import entity.ResultLodging;
import entity.ResultLodgingFull;
import entity.ResultLodgingHotel;
import entity.ResultLodgingList;
import entity.ResultLodgingRoomList;
import entity.ResultRegister;
import entity.ResultSouvenirList;
import entity.ResultWidgetFull;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Hoda on 10/01/2017.
 */
public interface getJsonInterface {
    @GET("api-itinerary.php")
    Call<ResultItineraryList> getItinerarys(@Query("action") String action,
                                            @Query("lang") String lang,
                                            @Query("from") String from,
                                            @Query("limit") String limit,
                                            @Query("offset") String offset,
                                            @Query("to") String to);

    @GET("api-com.iranplanner.tourism.iranplanner.di.data.php")
    Call<InterestResult> getInterest(
            @Query("action") String action,
            @Query("uid") String uid,
            @Query("cid") String cid,
            @Query("ntype") String ntype,
            @Query("nid") String nid,
            @Query("gtype") String gtype,
            @Query("gvalue") String gvalue);

    @GET("api-itinerary.php")
    Call<ResultSouvenirList> getSouvenirList(
            @Query("action") String action,
            @Query("id") String itineraryId);







    @GET("api-lodging.php")
    Call<ResultLodgingHotel> getHotelReserve(
            @Query("action") String action,
            @Query("id") String idHotel);

    @GET("api-lodging.php")
    Call<ResultLodgingRoomList> getResultLodgingRoomList(
            @Query("action") String action,
            @Query("id") String id,
            @Query("fromdate") String fromdate,
            @Query("todate") String todate);






}
