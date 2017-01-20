package com.rm.easy.ro.roastorder.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.rm.easy.ro.roastorder.R;
import com.rm.easy.ro.roastorder.jsonBean.Data;

import java.util.List;

/**
 * Created by Easy.D on 2017/1/20.
 */
public class OrderListAdapter extends ArrayAdapter<Data> {

    private int resourceId;

    public OrderListAdapter(Context context, int textViewResourceId, List<Data> objs){
        super(context, textViewResourceId, objs);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Data data = getItem(position);
        View view;

        view = LayoutInflater.from(getContext()).inflate(resourceId,null);
        TextView classes = (TextView)view.findViewById(R.id.order_list_item_class);
        TextView weight = (TextView)view.findViewById(R.id.order_list_item_weight);
        classes.setText(data.getCountry()+","+data.getFarm()+","+data.getClasses());
        weight.setText(data.getWeight()+"g");


        return view;
    }
}
