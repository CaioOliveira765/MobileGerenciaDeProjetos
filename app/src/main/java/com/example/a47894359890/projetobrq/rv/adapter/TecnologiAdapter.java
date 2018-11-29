package com.example.a47894359890.projetobrq.rv.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.model.Tecnologias;
import com.example.a47894359890.projetobrq.rv.holder.TecnologiaViewHolder;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TecnologiAdapter extends RecyclerView.Adapter{

    private List<Tecnologias> tecnologia;
    private Activity activity;
    private List<Tecnologias> tecnologiaList;

    public TecnologiAdapter(List<Tecnologias> tecnologias, Activity activity) {
        this.tecnologia = tecnologias;
        this.activity = activity;
        this.tecnologiaList = new ArrayList<>();
        this.tecnologiaList.addAll(tecnologia);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tecnologia_item_lista, parent,
                false);
        TecnologiaViewHolder holder = new TecnologiaViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TecnologiaViewHolder viewHolder = (TecnologiaViewHolder) holder;

        Tecnologias tecnol = tecnologia.get(position);

        ((TecnologiaViewHolder) holder).preencher(tecnol);

    }

    @Override
    public int getItemCount() {
        return tecnologia.size();
    }

    public void filtraPorNome(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        tecnologia.clear();
        if (charText.length() == 0){
            tecnologia.addAll(tecnologiaList);
        }else {
            for (Tecnologias l : tecnologiaList){
                if (l.getNome().toLowerCase(Locale.getDefault()).contains(charText)){
                    tecnologia.add(l);
                }
            }
        }
        notifyDataSetChanged();
    }
}
