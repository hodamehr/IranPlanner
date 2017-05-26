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

    @GET("api-itinerary.php")
    Call<ResultItineraryList> getItinerarysAttraction(@Query("action") String action,
                                                      @Query("lang") String lang,
                                                      @Query("from") String from,
                                                      @Query("limit") String limit,
                                                      @Query("offset") String offset,
                                                      @Query("attraction") String attraction);

    //    @GET("api-user.php?action=login&email=faridsaniee@gmail.com&password=090afe0d4abb5dfdccb84641fe115680")
    @GET("api-user.php")
    Call<LoginResult> getLoginResult(@Query("action") String action,
                                     @Query("email") String email,
                                     @Query("password") String password,
                                     @Query("cid") String token,
                                     @Query("andId") String androidId
                                     );
//    http://api.parsdid.com/iranplanner/app/api-user.php?action=login&email=09358891239&password=6efa240fc47e3f882ae16d163ca26f91

    @POST("api-com.iranplanner.tourism.iranplanner.di.data.php")
//    api.parsdid.com/iranplanner/app/api-com.iranplanner.tourism.iranplanner.di.data.php?action=nodeuser&id=28439&uid=323148788221963&ntype=itinerary

    Call<ResultCommentList> callInsertComment(@Body CommentSend request);

    @GET("api-com.iranplanner.tourism.iranplanner.di.data.php")
    Call<ResultCommentList> getCommentList(
            @Query("action") String action,
            @Query("nid") String nid,
            @Query("ntype") String ntype,
            @Query("offset") String offset);

    @GET("api-lodging.php")
    Call<ResultLodgingList> getLodgingReserve(
            @Query("action") String action,
            @Query("city") String city);

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

    @GET("api-com.iranplanner.tourism.iranplanner.di.data.php")
    Call<ResultWidgetFull> getWidgetResult(@Query("action") String param1,
                                           @Query("id") String param2,
                                           @Query("uid") String param3,
                                           @Query("ntype") String param4);

    @GET("api-user.php")
    Call<ResultRegister> getRegisterResult(@Query("action") String action,
                                           @Query("email") String email,
                                           @Query("password") String password,
                                           @Query("fname") String fname,
                                           @Query("lname") String lname,
                                           @Query("gender") String gender,
                                           @Query("cid") String cid,
                                           @Query("phone") String phone);

    @GET("api-itinerary.php")
    Call<ResultItineraryAttractionList> getItineraryAttractionList(
            @Query("action") String param1,
            @Query("lang") String param2,
            @Query("id") String param3
    );

    @GET("api-itinerary.php")
    Call<ResultItineraryList> getItinerarysFromProvince(@Query("action") String param1,
                                                        @Query("province") String param2,
                                                        @Query("offset") String param3);

}
