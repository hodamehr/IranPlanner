package server;

import entity.Login;
import entity.LoginResult;
import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;
import entity.ResultRegister;
import entity.ResultUserLogin;
import retrofit2.Call;
import retrofit2.http.GET;
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
