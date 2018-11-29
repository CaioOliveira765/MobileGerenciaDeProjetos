package com.example.a47894359890.projetobrq.rv.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.a47894359890.projetobrq.ColorRnd;
import com.example.a47894359890.projetobrq.R;
import com.example.a47894359890.projetobrq.Views.ProjetoActivity;
import com.example.a47894359890.projetobrq.model.Tecnologias;
import com.example.a47894359890.projetobrq.rv.adapter.TecnologiAdapter;

public class TecnologiaViewHolder extends RecyclerView.ViewHolder {

    public final TextView nome;
    private Long tecnologiaId;
    public final TecnologiAdapter adapter;
    ImageView imgLetra;
    String letra;
    ColorRnd generator = ColorRnd.DEFAULT;

    public TecnologiaViewHolder(final View view,final TecnologiAdapter adapter) {
        super(view);
        this.adapter = adapter;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Activity context = (Activity)view.getContext();
                final Intent intent = new Intent(context, ProjetoActivity.class);
                intent.putExtra("tecnologiaId", tecnologiaId);
                context.startActivityForResult(intent, 2);
                context.startActivity(intent);
            }
        });
        imgLetra = view.findViewById(R.id.tvNomeLetra);
        nome = view.findViewById(R.id.tvNomeTec);
    }

    public void preencher(Tecnologias tecnologias) {
        tecnologiaId = tecnologias.getId();

        letra = String.valueOf(tecnologias.getNome().charAt(0));

//        Create a new TextDrawable for our image's background
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(letra, generator.getRandomColor());

        imgLetra.setImageDrawable(drawable);
        nome.setText(tecnologias.getNome());
    }


}
