package com.example.a47894359890.projetobrq.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Retrofit.RetrofitConfig;
import com.example.a47894359890.projetobrq.model.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EsqueceuActivity extends AppCompatActivity {

    private Button btnEnviar;
    private Button voltar;
    private EditText email;
    private String emailDigitado;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueceu);

        voltar = findViewById(R.id.btnVoltar);
        btnEnviar = findViewById(R.id.btEnviarEsqueceu);
        email = findViewById(R.id.tvEmailEsqueceu);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDigitado = email.getText().toString();
                Usuario usuario = new Usuario(emailDigitado);
                Call<ResponseBody> call = new RetrofitConfig().getRestInterface().recSenha(usuario);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(EsqueceuActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Email enviado com Sucesso", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EsqueceuActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });

    }
}
