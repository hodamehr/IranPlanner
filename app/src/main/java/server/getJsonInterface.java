package server;

import android.util.Log;

import entity.Login;
import entity.ResultItineraryAttractionList;
import entity.ResultItineraryList;
import entity.ResultUserLogin;
import entity.map.MapResult;
import entity.map.Route;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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
                                            @Query("offset") String param5);


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

    //    ?https://maps.googleapis.com/maps/api/directions/json?origin=35.6859016418457,51.38629913330078&destination=36.40290069580078,55.01570129394531&sensor=false
    @GET("https://maps.googleapis.com/maps/api/directions/json")
    Call<Route> getMapResult(@Query("origin") String param1,
                             @Query("destination") String param2,
                             @Query("sensor") String param3);


    //        @POST("/SF_UserLogin.svc/rest/login/{employeeCode}/{password}") // Assume your base url is http://172.16.7.203/sfAppServices/
    @POST("api-user.php?action=login/{email}/{password}")
    // Assume your base url is http://172.16.7.203/sfAppServices/
    public Call<Login> login(@Path("email") String email, @Path("password") String password);

//    @GET("/me?site=stackoverflow&order=desc&sort=reputation&filter=default)
    @POST("/api-user.php/{email}/{password}")
    Call<ResultUserLogin> tt(@Path("email") String email, @Path("password") String password);
//    Call<Login> tt(@Path("email") String email, @Path("password") String password);

}
