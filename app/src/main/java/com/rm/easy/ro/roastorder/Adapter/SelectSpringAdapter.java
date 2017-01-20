package com.rm.easy.ro.roastorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Easy.D on 2016/11/13.
 */
public class SelectSpringAdapter extends ArrayAdapter<String> {

    private int resourceId;
    private List<String> list;

    public SelectSpringAdapter(Context context, int textViewResourceId, List<String> objs){
        super(context, textViewResourceId, objs);
        resourceId = textViewResourceId;
        list = objs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView text = (TextView)convertView.findViewById(android.R.id.text1);
        text.setText(list.get(position).toString()+"g");
        return convertView;
    }
}
