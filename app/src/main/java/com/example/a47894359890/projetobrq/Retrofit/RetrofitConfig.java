package com.example.a47894359890.projetobrq.Retrofit;

import com.example.a47894359890.projetobrq.Retrofit.service.RestInterface;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    //atributo
    private final Retrofit retrofit;
    //private String urlConexao = "http://192.168.4.244:8080/gerenciadepjs/rest/";
    private String urlConexao = "http://brqgerencia.projeto.senai/gerenciadepjs/rest/";

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl(urlConexao)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public RetrofitConfig(OkHttpClient client){
        this.retrofit = new Retrofit.Builder().baseUrl(urlConexao).client(client)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public RestInterface getRestInterface() {
        return this.retrofit.create(RestInterface.class);
    }

}
