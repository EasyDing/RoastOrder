package com.rm.easy.ro.roastorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Init widget
    private Button createClass;
    private Button createOrder;
    private Button endOrder;

    private TableRow createClassTR;
    private TableRow createOrderTR;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createClass = (Button)findViewById(R.id.main_createClass);
        createOrder = (Button)findViewById(R.id.main_createOrder);

        createClassTR = (TableRow)findViewById(R.id.main_createClass_tablerow);
        createOrderTR = (TableRow)findViewById(R.id.main_createOrder_tablerow);

        //set linstener

        createClass.setOnClickListener(this);
        createOrder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_createClass:
                createOrderTR.setVisibility(View.GONE);
                createClassTR.setVisibility(View.VISIBLE);
                break;
            case R.id.main_createOrder:
                createClassTR.setVisibility(View.GONE);
                createOrderTR.setVisibility(View.VISIBLE);
                break;
        }

    }
}
