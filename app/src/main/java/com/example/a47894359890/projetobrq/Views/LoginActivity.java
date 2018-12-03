package com.example.a47894359890.projetobrq.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Retrofit.RetrofitConfig;
import com.example.a47894359890.projetobrq.commons.AppUtils;
import com.example.a47894359890.projetobrq.model.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    //Espaços no layout
    private EditText edEmail;
    private EditText edSenha;

    //o uqe o usuario digitar
    private String emailDigitado;
    private String senhaDigitado;


    private TextView erroLogin;
    private TextView edEsqueceu;
    private Button btLogin;

    private String token;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edEmail = findViewById(R.id.idEmail);
        edSenha = findViewById(R.id.idSenha);
        edEsqueceu = findViewById(R.id.idEsqueceu);
        btLogin = findViewById(R.id.btnLogin);
        erroLogin = findViewById(R.id.loginErro);

        sharedPreferences = getSharedPreferences(AppUtils.SHARED_KEY, Context.MODE_PRIVATE);

        edEmail.setText("admin@email.com");
        edSenha.setText("admin");
        edEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, EsqueceuActivity.class);
                startActivity(intent);
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDigitado = edEmail.getText().toString();
                senhaDigitado = edSenha.getText().toString();
                Usuario usuarioDaLista = new Usuario(emailDigitado, senhaDigitado);

                Call<ResponseBody> call = new RetrofitConfig().getRestInterface().buscarLogin(usuarioDaLista);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            SharedPreferences.Editor ed = sharedPreferences.edit();
                            ed.putString("token", response.headers().get("authorization"));
                            ed.apply();
                            Intent intent = new Intent(LoginActivity.this, TecnologiasActivity.class);
                            startActivity(intent);
                        }else
                            erroLogin.setVisibility(View.VISIBLE);
                            erroLogin.setText("Dados Inválidos.");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}



