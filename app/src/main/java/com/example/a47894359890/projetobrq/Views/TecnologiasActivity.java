package com.example.a47894359890.projetobrq.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Retrofit.RetrofitConfig;
import com.example.a47894359890.projetobrq.commons.AppUtils;
import com.example.a47894359890.projetobrq.model.Tecnologias;
import com.example.a47894359890.projetobrq.rv.adapter.TecnologiAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

public class TecnologiasActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private SearchView searchView;
    private List<Tecnologias> listTec;
    private String token;
    private OkHttpClient  okHttpClient;
    SharedPreferences sharedPreferences;
    private TecnologiAdapter tecnologiAdapter;
    private TextView voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnologias);

        recyclerView = findViewById(R.id.ListId);
        searchView = findViewById(R.id.pesquisaId);
        voltar = findViewById(R.id.voltarPLogin);

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

        voltar.setText("sair");

        carregarLista(okHttpClient);
        buscarSearchView();

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TecnologiasActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void buscarSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //recebo a string que quero
                String TecBuscada = s;
                //coloca o filtro no adapter
                tecnologiAdapter.filtraPorNome(TecBuscada);
                return false;
            }
        });
        searchView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                searchView.setIconified(false);
            }
        });
    }

    private void carregarLista(OkHttpClient okHttpClient) {
        Call<List<Tecnologias>> call = new RetrofitConfig(okHttpClient).getRestInterface().listarTecnologias();
        call.enqueue(new Callback<List<Tecnologias>>() {
            @Override
            public void onResponse(Call<List<Tecnologias>> call, retrofit2.Response<List<Tecnologias>> response) {
                if (response.isSuccessful()){
                    listTec = response.body();
                    if (listTec.size() > 0){
                        Log.d("tecs", listTec.toString());
                        tecnologiAdapter = new TecnologiAdapter(listTec, TecnologiasActivity.this);
                        recyclerView.setAdapter(tecnologiAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager);
                        // recyclerView.setNestedScrollingEnabled(false);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Tecnologias>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao carregar lista.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

