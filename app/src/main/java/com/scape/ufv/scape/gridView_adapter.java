package com.scape.ufv.scape;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by souzadomingues on 02/02/16.
 */

public class gridView_adapter extends BaseAdapter {
    private ArrayList<String> listTitles;
    private ArrayList<Integer> listIcons;
    private Activity activity;

    public gridView_adapter(Activity activity,ArrayList<String> listTitles, ArrayList<Integer> listIcons) {
        super();
        this.listIcons = listIcons;
        this.listTitles = listTitles;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listTitles.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listTitles.get(position);
    }

    public void setItem(int position, String value){
        listTitles.set(position, value);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewIcons;
        public TextView txtViewTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.gridview_item, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.imgViewIcons = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.txtViewTitle.setText(listTitles.get(position));
        view.imgViewIcons.setImageResource(listIcons.get(position));

        return convertView;
    }
}
