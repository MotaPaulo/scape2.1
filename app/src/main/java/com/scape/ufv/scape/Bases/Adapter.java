package com.scape.ufv.scape.Bases;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scape.ufv.scape.R;

import java.util.List;

public class Adapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private List<Ativid> itens;

    public Adapter(Context context, List<Ativid> itens) {
        this.itens = itens;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Ativid getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte item;


        if(view == null){
            view = mInflater.inflate(R.layout.listagenda_layout,null);

            item = new ItemSuporte();
            item.nome = ((TextView) view.findViewById(R.id.idat));
           // item.hin = ((TextView)view.findViewById(R.id.textView2));
           // item.hfim = ((TextView)view.findViewById(R.id.tin));
           // item.data = ((TextView)view.findViewById(R.id.textView4));
            view.setTag(item);
        }
        else{
            item = (ItemSuporte) view.getTag();
        }
        Ativid atividade = itens.get(position);

        item.nome.setText(atividade.getNome());
        item.hin.setText(atividade.getHora_in());
        item.hfim.setText(atividade.getHora_fim());
        item.data.setText(atividade.getData());

        return view;


    }
    private class ItemSuporte{
        TextView nome;
        TextView hin;
        TextView hfim;
        TextView data;
    }

}
