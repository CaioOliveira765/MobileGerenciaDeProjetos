package com.example.a47894359890.projetobrq.rv.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.model.Projeto;
import com.example.a47894359890.projetobrq.model.Tecnologias;
import com.example.a47894359890.projetobrq.rv.holder.ProjetoHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjetoAdapter extends RecyclerView.Adapter {

    private List<Projeto> projeto;
    private Activity activity;
    private List<Projeto> projetoList;

    public ProjetoAdapter(List<Projeto> projeto, Activity activity) {
        this.projeto = projeto;
        this.activity = activity;
        this.projetoList = new ArrayList<>();
        this.projetoList.addAll(projeto);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_principal, parent, false);
        ProjetoHolder holder = new ProjetoHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProjetoHolder viewHolder = (ProjetoHolder) holder;
        Projeto project = projeto.get(position);
        ((ProjetoHolder) holder).preencher(project);
    }

    @Override
    public int getItemCount() {
        return projeto.size();
    }

    public void filtraPorNome(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        projeto.clear();
        if (charText.length() == 0){
            projeto.addAll(projetoList);
        }else {
            for (Projeto l : projetoList){
                if (l.getNome().toLowerCase(Locale.getDefault()).contains(charText) ||
                        l.getStatus().getNome().toLowerCase(Locale.getDefault()).contains(charText) ||
                        l.getResponsavelBRQ().toLowerCase(Locale.getDefault()).contains(charText) ||
                        l.getId().toString().toLowerCase(Locale.getDefault()).contains(charText)) {
                    projeto.add(l);
                }
            }
        }
        notifyDataSetChanged();
    }
}
