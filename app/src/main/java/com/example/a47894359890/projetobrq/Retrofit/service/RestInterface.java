package com.example.a47894359890.projetobrq.Retrofit.service;

import com.example.a47894359890.projetobrq.model.Projeto;
import com.example.a47894359890.projetobrq.model.Tecnologias;
import com.example.a47894359890.projetobrq.model.Usuario;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestInterface {

        @POST("auth/jwt")
        Call<ResponseBody> buscarLogin(@Body Usuario usuario);

        @GET("tecnologias")
        Call<List<Tecnologias>> listarTecnologias();

        @GET("projetos/{id}")
        Call<Projeto> listarProjetos(@Path("id") Long id);

        // revisar sintaxe
        @GET("projetos/tec{id}")
        Call<List<Projeto>> listarProjetosPorTecnologia(@Path("id") Long id);

        @PUT("usuario/enviarsenha")
        Call<ResponseBody> recSenha(@Body Usuario usuario);
}
