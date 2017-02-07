package com.rm.easy.ro.roastorder.Adapter;

import android.content.Context;
import android.util.Log;
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
    private Float temp;

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
        TextView lb = (TextView)view.findViewById(R.id.order_list_item_lb);
        TextView weight = (TextView)view.findViewById(R.id.order_list_item_weight);
        classes.setText(data.getCountry()+","+data.getFarm()+","+data.getClasses());
        lb.setText(Float.parseFloat(data.getWeight())+"lbs");
        if(Float.parseFloat(data.getWeight())/0.5%2 == 1){

            setTemp(Float.parseFloat(data.getWeight()) * 540 + 30);
        }else{
            setTemp(Float.parseFloat(data.getWeight()) * 540);
        }
        weight.setText(getTemp()+"g");


        return view;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }
}
