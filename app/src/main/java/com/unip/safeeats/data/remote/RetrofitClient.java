package com.unip.safeeats.data.remote;

import com.unip.safeeats.util.Constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Classe responsável por criar conexão com API de banco de Dados
*
* */
public class RetrofitClient {
    private static Retrofit retrofit;
    private static final String URL = Constants.URL;

    public static ApiService getApiService(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}
