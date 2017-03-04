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
    @GET("api-itinerary.php?action=list&lang=fa&from=342&limit=10&offset=0&to")
    Call<ResultItineraryList> getItinerarys(@Query("action") String param1,
                                            @Query("lang") String param2,
                                            @Query("from") String param3,
                                            @Query("limit") String param4,
                                            @Query("offset") String param5,
                                            @Query("to") String param6);

    @GET("api-data.php?action=widget&uid=792147600796866&cid=1&ntype=itinerary&nid=21905&gtype=bookmark&gvalue=1")
    Call<InterestResult> getInterest(
            @Query("action") String action,
            @Query("uid") String uid,
            @Query("cid") String cid,
            @Query("ntype") String ntype,
            @Query("nid") String nid,
            @Query("gtype") String gtype,
            @Query("gvalue") String gvalue);

    @GET("api-itinerary.php?action=searchattractioncity&lang=fa&from=342&limit=10&offset=0&attraction=id")
    Call<ResultItineraryList> getItinerarysAttraction(@Query("action") String action,
                                                      @Query("lang") String lang,
                                                      @Query("from") String from,
                                                      @Query("limit") String limit,
                                                      @Query("offset") String offset,
                                                      @Query("attraction") String attraction);


    @GET("api-user.php?action=login&email=faridsaniee@gmail.com&password=090afe0d4abb5dfdccb84641fe115680")
    Call<LoginResult> getLoginResult(@Query("action") String param1,
                                     @Query("email") String param2,
                                     @Query("password") String param3);


    @POST("api-data.php?action=comment")
    Call<ResultCommentList> callInsertComment(@Body CommentSend request);

    //    api.parsdid.com/iranplanner/app/api-data.php?action=pagecomments&nid=1&ntype=attraction
    @GET("api-data.php?action=pagecomments&nid=1&ntype=attraction")
    Call<ResultCommentList> getCommentList(
            @Query("action") String action,
            @Query("nid") String nid,
            @Query("ntype") String ntype);

    //    http://api.parsdid.com/iranplanner/app/api-lodging.php?action=list&city=342
    @GET("api-lodging.php?action=list&city=342")
    Call<ResultLodgingList> getLodgingReserve(
            @Query("action") String action,
            @Query("city") String city);
//    api.parsdid.com/iranplanner/app/api-lodging.php?action=full&id=23107

    @GET("api-lodging.php?action=full&id=23107")
    Call<ResultLodgingHotel> getHotelReserve(
            @Query("action") String action,
            @Query("id") String idHotel);

    //    http://api.parsdid.com/iranplanner/app/api-lodging.php?action=room&id=22649&fromdate=1488289475&todate=1488489475
    @GET("api-lodging.php?action=room&id=22649&fromdate=1488289475&todate=1488489475")
    Call<ResultLodgingRoomList> getResultLodgingRoomList(
            @Query("action") String action,
            @Query("id") String id,
            @Query("fromdate") String fromdate,
            @Query("todate") String todate);

    @GET("api-data.php?action=nodeuser&id=28439&uid=323148788221963&ntype=itinerary")
    Call<ResultWidgetFull> getWidgetResult(@Query("action") String param1,
                                           @Query("id") String param2,
                                           @Query("uid") String param3,
                                           @Query("ntype") String param4);

    @GET("api-user.php")
//    api.parsdid.com/iranplanner/app/api-user.php?action=register&email=z@zhoda.com&password=hhhhh&fname=hoda&lname=vahidi&gender=1&cid=1
    Call<ResultRegister> getRegisterResult(@Query("action") String action,
                                           @Query("email") String email,
                                           @Query("password") String password,
                                           @Query("fname") String fname,
                                           @Query("lname") String lname,
                                           @Query("gender") String gender,
                                           @Query("cid") String cid);

    @GET("api-itinerary.php")
    Call<ResultItineraryAttractionList> getItineraryAttractionList(
            @Query("action") String param1,
            @Query("lang") String param2,
            @Query("id") String param3
    );

    //    http://api.parsdid.com/iranplanner/app/api-itinerary.php?action=searchprovince&province=302
    @GET("api-itinerary.php")
    Call<ResultItineraryList> getItinerarysFromProvince(@Query("action") String param1,
                                                        @Query("province") String param2,
                                                        @Query("offset") String param3);

}
