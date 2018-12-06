package com.example.a47894359890.projetobrq.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Retrofit.RetrofitConfig;
import com.example.a47894359890.projetobrq.commons.AppUtils;
import com.example.a47894359890.projetobrq.model.Projeto;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

public class ProjetosDetalhadosActivity extends AppCompatActivity {

    private Projeto listaProject;
    private String token;
    private OkHttpClient okHttpClient;
    private Long projetoId;
    private ImageButton imageButton;
    SharedPreferences sharedPreferences;

    private TextView id;
    private TextView nome;
    private TextView tecnolgias;
    private TextView horas;
    private TextView dataInicio;
    private TextView dataFim;
    private TextView status;
    private TextView respBrq;
    private TextView respCliente;
    private TextView descricao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetos_detalhados);
        imageButton = findViewById(R.id.voltarTecd);

        id = findViewById(R.id.idBanco);
        nome = findViewById(R.id.nomeBanco);
        tecnolgias = findViewById(R.id.tecnologiaBanco);
        horas = findViewById(R.id.horasBanco);
        dataInicio = findViewById(R.id.dataiBanco);
        dataFim = findViewById(R.id.datafBanco);
        status = findViewById(R.id.statusBanco);
        respBrq = findViewById(R.id.responsavelbBanco);
        respCliente = findViewById(R.id.responsavelcBanco);
        descricao = findViewById(R.id.descricaoBanco);


        Intent intent = getIntent();
        projetoId = intent.getLongExtra("projetoID", 0);

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
        carregarListaProectDetalhado(okHttpClient);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela();
            }
        });



    }
    private void voltarTela(){
        this.finish();
    }

    private void carregarListaProectDetalhado(OkHttpClient okHttpClient) {
        Call<Projeto> call = new RetrofitConfig(okHttpClient).getRestInterface().listarProjetos(projetoId);
        call.enqueue(new Callback<Projeto>() {
            @Override
            public void onResponse(Call<Projeto> call, retrofit2.Response<Projeto> response) {
                if (response.isSuccessful()){
                    listaProject = response.body();
                    Log.d("project", listaProject.toString());
                    id.setText(listaProject.getId().toString());
                    nome.setText(listaProject.getNome());
                    tecnolgias.setText(listaProject.getTecnologia().toString());
                    horas.setText(String.valueOf(listaProject.getHoras()));
                    dataInicio.setText(listaProject.getDataInicio());
                    dataFim.setText(listaProject.getDataFim());
                    status.setText(listaProject.getStatus().getNome());
                    respBrq.setText(listaProject.getResponsavelBRQ());
                    respCliente.setText(listaProject.getResponsavelCliente());
                    descricao.setText(listaProject.getDescricao());
                }
            }

            @Override
            public void onFailure(Call<Projeto> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao carregar Projeto", Toast.LENGTH_SHORT) .show();
            }
        });
    }
}