package com.kpdigital.mywallpaper;


import static com.kpdigital.mywallpaper.APIUtilities.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    String BASE_URL = "https://api.unsplash.com";

    @Headers("Authorization: Client-ID " + API)
    @GET("/photos")
    Call<List<ImageModel>> getImage(
            @Query("orientation") String orientation,
            @Query("q") int q,
            @Query("page") int page,
            @Query("per_page") int per_page
    );



    @Headers("Authorization: Client-ID " + API)
    @GET("/search/photos")
    Call<searchModel> getSearchImage(
            @Query("query") String query,
            @Query("orientation") String orientation,
            @Query("q") int q,
            @Query("w") int w,
            @Query("h") int h,
            @Query("page") int page,
            @Query("per_page") int per_page
    );

    @Headers("Authorization: Client-ID " + API)
    @GET("/topics")
    Call<List<String>> getTopics(

    );

}
