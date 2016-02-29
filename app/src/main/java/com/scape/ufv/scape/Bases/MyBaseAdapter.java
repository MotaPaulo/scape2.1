package com.scape.ufv.scape.Bases;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scape.ufv.scape.R;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {

    ArrayList<Ativid> lista = new ArrayList<Ativid>();
    LayoutInflater inflater;
    Context context;

    public MyBaseAdapter(Context context, ArrayList<Ativid> lista){
        this.lista = lista;
        this.context = context;
        //inflater=LayoutInflater.from(this.context);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lista.size();
    }

    @Override
    public Ativid getItem(int position) {
        // TODO Auto-generated method stub
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.listagenda_layout,null);
            viewHolder = new MyViewHolder();
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.tvnome = detail(convertView,R.id.hfim,lista.get(position).getNome());
       // viewHolder.tvhorain = detail(convertView,R.id.textView2,lista.get(position).getHora_in());
       // viewHolder.tvhorafim = detail(convertView, R.id.tin,lista.get(position).getHora_fim());
//        viewHolder.tvdata = detail(convertView, R.id.textView4, lista.get(position).getData());

        return convertView;
    }
    private TextView detail(View v,int resId, String text){
        TextView tv = (TextView) v.findViewById(resId);
        tv.setText(text);
        return tv;
    }

    private class MyViewHolder{
        TextView tvnome,tvhorain,tvhorafim,tvdata;
    }

}
