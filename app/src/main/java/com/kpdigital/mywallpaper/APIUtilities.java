package com.kpdigital.mywallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtilities {

    private static Retrofit retrofit = null;
    public static final String API = "qLCiUB8xKDpDXMo2QQh6ZRAN5t2NgGLx9FN2VFJWuGA";


    public static APIInterface getAPIInterface() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(APIInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit.create(APIInterface.class);
    }
}
