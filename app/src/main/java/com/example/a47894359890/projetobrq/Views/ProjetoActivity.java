package com.example.a47894359890.projetobrq.Views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Retrofit.RetrofitConfig;
import com.example.a47894359890.projetobrq.commons.AppUtils;
import com.example.a47894359890.projetobrq.model.Projeto;
import com.example.a47894359890.projetobrq.rv.adapter.ProjetoAdapter;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProjetoActivity extends Activity {

    private RecyclerView recyclerView;
    private String token;
    private List<Projeto> listaProjeto;
    private OkHttpClient okHttpClient;
    private SearchView searchView;
    private SharedPreferences sharedPreferences;
    private Long tecnologiaId;
    private ProjetoAdapter projetoAdapter;
    private ImageButton voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);

        recyclerView = findViewById(R.id.ListaId);
        searchView = findViewById(R.id.searchView);
        voltar = findViewById(R.id.voltarTec);

        Intent intent = getIntent();
        tecnologiaId = intent.getLongExtra("tecnologiaId", 0);

        sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecharTela();
           }
       });

        sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");

        okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request.Builder b = chain.request().newBuilder();
                b.addHeader("accept", "application/json");
                b.addHeader("authorization", token);
                return chain.proceed(b.build());
            }
        }).build();
        carregarListarProjetos(okHttpClient);


        buscarSearch();
    }

    private void fecharTela(){
        this.finish();
    }

    private void buscarSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               return false;
            }

            @Override
           public boolean onQueryTextChange(String s) {
               String ProjectBuscada = s;
                projetoAdapter.filtraPorNome(ProjectBuscada);
                return false;
            }
      });
        searchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
    }

    private void carregarListarProjetos(OkHttpClient okHttpClient) {
        Call<List<Projeto>> call = new RetrofitConfig(okHttpClient).getRestInterface().listarProjetosPorTecnologia(tecnologiaId);
        call.enqueue(new Callback<List<Projeto>>() {
            @Override
            public void onResponse(Call<List<Projeto>> call, Response<List<Projeto>> response) {
                if (response.isSuccessful()){
                    listaProjeto = response.body();
                    Log.d("projetos", listaProjeto.toString());
                    if (listaProjeto.size() > 0){
                        Log.d("project", listaProjeto.toString());
                        projetoAdapter = new ProjetoAdapter(listaProjeto, ProjetoActivity.this);
                        recyclerView.setAdapter(projetoAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Projeto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao carregar a Lista", Toast.LENGTH_SHORT).show();
            }
        });
        }
    }

