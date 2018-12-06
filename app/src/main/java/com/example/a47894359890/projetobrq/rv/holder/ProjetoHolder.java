package com.example.a47894359890.projetobrq.rv.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Views.ProjetosDetalhadosActivity;
import com.example.a47894359890.projetobrq.model.Projeto;
import com.example.a47894359890.projetobrq.rv.adapter.ProjetoAdapter;

public class ProjetoHolder extends RecyclerView.ViewHolder {

    public final TextView nomeProjeto;
    private Long idProjeto;
    public final TextView projetoIdDaEmpresa;
    public final TextView statusProjeto;
    public final TextView responsavelBrq;
    public final ProjetoAdapter adapter;

    public ProjetoHolder(final View view,final ProjetoAdapter projetoAdapter) {
        super(view);
        this.adapter = projetoAdapter;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity)view.getContext();
                final Intent intent = new Intent(context, ProjetosDetalhadosActivity.class);
                intent.putExtra("projetoID", idProjeto);
                context.startActivity(intent);
                Log.d("idTec", idProjeto.toString());
            }
        });

        nomeProjeto = view.findViewById(R.id.item_nome);
        projetoIdDaEmpresa = view.findViewById(R.id.item_id_item);
        statusProjeto = view.findViewById(R.id.StatusBanco);
        responsavelBrq = view.findViewById(R.id.CordenadorBanco);

    }


    public void preencher(Projeto project) {
        idProjeto = project.getId();
        projetoIdDaEmpresa.setText(project.getId().toString());
        nomeProjeto .setText(project.getNome());
        statusProjeto.setText(project.getStatus().getNome());
        responsavelBrq.setText(project.getResponsavelBRQ());
    }
}
