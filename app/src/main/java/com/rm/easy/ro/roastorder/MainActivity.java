package com.rm.easy.ro.roastorder;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.rm.easy.ro.roastorder.Adapter.OrderListAdapter;
import com.rm.easy.ro.roastorder.Adapter.SelectSpringAdapter;
import com.rm.easy.ro.roastorder.iface.HttpCallbackListener;
import com.rm.easy.ro.roastorder.jsonBean.Data;
import com.rm.easy.ro.roastorder.jsonBean.JsonGeneral;
import com.rm.easy.ro.roastorder.util.GsonUtil;
import com.rm.easy.ro.roastorder.util.HttpUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{

    public static final String HOST = "http://rmcoffee.imwork.net/rmRO/";

    public static final String TAG = "info";
    public static final int CONNECT_ERROR = 0;
    public static final int CHECK_SUCCESS = 1;
    public static final int CHECK_FAIL = 2;
    public static final int CONNECT_SUCCESS = 3;
    public static final int CONNECT_FAIL = 4;
    public static final int CREATE_CLASS_SUCCESS = 5;
    public static final int CREATE_CLASS_FAIL = 6;
    public static final int GETCOUNTRYDATE_SUCCESS = 7;
    public static final int GETFARMDATE_SUCCESS = 8;
    public static final int GETCLASSDATE_SUCCESS = 9;
    public static final int CREATEORDER_SUCCESS = 10;
    public static final int GETORDERDATA_SUCCESS = 11;
    public static final int ENDORDER_SUCCESS = 12;






    public static final int COMMON_FAIL = 99;

    private List<String> countryList = new ArrayList<>();
    private List<String> farmList = new ArrayList<>();
    private List<String> classList = new ArrayList<>();

    private String countryName;





    //Init main widget
    private Button createClass;
    private Button createOrder;
    private Button endOrder;
    private Button connect;

    //Init class widget

    private Button classSubmit;
    private Button classCheck;

    private EditText country;
    private EditText farm;
    private EditText classes;

    //Init order widget
    private Button pullData;
    private Spinner selectCountry;
    private Spinner selectFarm;
    private Spinner selectClass;
    private EditText roastWeight;
    private Button orderSubmit;

    //Init End widget
    private Button pullOrderData;
    private ListView orderList;

    private TableRow createClassTR;
    private TableRow createOrderTR;
    private TableRow endOrderTR;

    //OS Var
    private JsonGeneral globalJG;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createClass = (Button)findViewById(R.id.main_createClass);
        createOrder = (Button)findViewById(R.id.main_createOrder);
        connect = (Button)findViewById(R.id.createConnect);
        endOrder = (Button)findViewById(R.id.main_endOrder);

        classCheck = (Button)findViewById(R.id.main_createClass_check);
        classSubmit = (Button)findViewById(R.id.main_createClass_submit);
        country = (EditText)findViewById(R.id.main_createClass_country);
        farm = (EditText)findViewById(R.id.main_createClass_farm);
        classes = (EditText)findViewById(R.id.main_createClass_class);

        pullData = (Button)findViewById(R.id.main_createOrder_getData);
        selectCountry = (Spinner)findViewById(R.id.main_createOrder_country);
        selectFarm = (Spinner)findViewById(R.id.main_createOrder_farm);
        selectClass = (Spinner)findViewById(R.id.main_createOrder_class);
        roastWeight = (EditText)findViewById(R.id.main_createOrder_weight);
        orderSubmit = (Button)findViewById(R.id.main_createOrder_submit);

        pullOrderData = (Button)findViewById(R.id.main_endOrder_getData);
        orderList = (ListView)findViewById(R.id.main_endOrder_listView);




        createClassTR = (TableRow)findViewById(R.id.main_createClass_tablerow);
        createOrderTR = (TableRow)findViewById(R.id.main_createOrder_tablerow);
        endOrderTR = (TableRow)findViewById(R.id.main_endOrder_tablerow);




        //set button linstener

        createClass.setOnClickListener(this);
        createOrder.setOnClickListener(this);
        connect.setOnClickListener(this);
        classCheck.setOnClickListener(this);
        classSubmit.setOnClickListener(this);
        pullData.setOnClickListener(this);
        orderSubmit.setOnClickListener(this);
        endOrder.setOnClickListener(this);
        pullOrderData.setOnClickListener(this);

        //set spring listener
        selectCountry.setOnItemSelectedListener(this);
        selectFarm.setOnItemSelectedListener(this);
        selectClass.setOnItemSelectedListener(this);

        //set listview listener
        orderList.setOnItemClickListener(this);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            JsonGeneral jg = (JsonGeneral)msg.obj;
                        switch (msg.what){
                case CHECK_SUCCESS:
                    Toast.makeText(MainActivity.this,"未通过检查："+jg.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case CHECK_FAIL:
                    Toast.makeText(MainActivity.this,"通过检查："+jg.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case CREATE_CLASS_SUCCESS:
                    Toast.makeText(MainActivity.this,"新建成功", Toast.LENGTH_SHORT).show();
                    break;
                case CREATE_CLASS_FAIL:
                    Toast.makeText(MainActivity.this,"新建失败", Toast.LENGTH_SHORT).show();
                    break;
                case CONNECT_ERROR:
                    Toast.makeText(MainActivity.this,"网络超时，请重试",Toast.LENGTH_SHORT).show();
                    break;
                case CONNECT_SUCCESS:
                    Toast.makeText(MainActivity.this,"已连接",Toast.LENGTH_SHORT).show();
                    break;
                case GETCOUNTRYDATE_SUCCESS:
                    countryList.clear();
                    Toast.makeText(MainActivity.this,"获取数据成功",Toast.LENGTH_SHORT).show();
                    while(jg.getData().iterator().hasNext()){
                        countryList.add(jg.getData().get(0).getCountry().toString());
                        jg.getData().remove(0);
                    }
                    SelectSpringAdapter countryAdapter = new SelectSpringAdapter(MainActivity.this, android.R.layout.simple_spinner_item, countryList);
                    countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    selectCountry.setAdapter(countryAdapter);
                    break;
                case GETFARMDATE_SUCCESS:
                    farmList.clear();
                    while(jg.getData().iterator().hasNext()){
                        farmList.add(jg.getData().get(0).getFarm().toString());
                        jg.getData().remove(0);
                    }
                    SelectSpringAdapter farmAdapter = new SelectSpringAdapter(MainActivity.this, android.R.layout.simple_spinner_item, farmList);
                    farmAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    selectFarm.setAdapter(farmAdapter);
                    break;
                case GETCLASSDATE_SUCCESS:
                    classList.clear();
                    while(jg.getData().iterator().hasNext()){
                        classList.add(jg.getData().get(0).getClasses().toString());
                        jg.getData().remove(0);
                    }
                    SelectSpringAdapter classAdapter = new SelectSpringAdapter(MainActivity.this, android.R.layout.simple_spinner_item, classList);
                    classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    selectClass.setAdapter(classAdapter);
                    break;
                case CREATEORDER_SUCCESS:
                    Log.i(TAG,"Mainactivity-开单成功");
                    Toast.makeText(MainActivity.this,"开单成功",Toast.LENGTH_SHORT).show();
                    break;
                case GETORDERDATA_SUCCESS:
                    OrderListAdapter adapter = new OrderListAdapter(MainActivity.this, R.layout.order_list_item_listview,jg.getData());
                    orderList.setAdapter(adapter);
                    break;
                case ENDORDER_SUCCESS:
                    Toast.makeText(MainActivity.this,"结单成功",Toast.LENGTH_SHORT).show();
                    break;

                case COMMON_FAIL:
                    Toast.makeText(MainActivity.this,"失败:" + jg.getMessage(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };








    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_createClass:
                createOrderTR.setVisibility(View.GONE);
                endOrderTR.setVisibility(View.GONE);
                createClassTR.setVisibility(View.VISIBLE);
                break;
            case R.id.main_createOrder:
                createClassTR.setVisibility(View.GONE);
                endOrderTR.setVisibility(View.GONE);
                createOrderTR.setVisibility(View.VISIBLE);
                break;
            case R.id.main_endOrder:
                createOrderTR.setVisibility(View.GONE);
                createClassTR.setVisibility(View.GONE);
                endOrderTR.setVisibility(View.VISIBLE);
                break;
            case R.id.createConnect:
                String connectAddress = HOST+"connect.php";
                String connectReqStr = "";
                sendRequest(connectAddress, connectReqStr, CONNECT_SUCCESS, CONNECT_FAIL);
                break;
                //Create Class Begin
            case R.id.main_createClass_check:
                String checkAddress = HOST+"check_class.php";
                String checkReqStr = "country=" + country.getText() + "&farm=" + farm.getText() + "&classes=" + classes.getText();
                sendRequest(checkAddress, checkReqStr, CHECK_SUCCESS, CHECK_FAIL);
                break;
            case R.id.main_createClass_submit:
                String createClassSubmitAddress = HOST+"createClass.php";
                String createClassSubmitReqStr = "country=" + country.getText() + "&farm=" + farm.getText() + "&classes=" + classes.getText();
                sendRequest(createClassSubmitAddress, createClassSubmitReqStr, CREATE_CLASS_SUCCESS, CREATE_CLASS_FAIL);
                break;
                //Create class End & Create Order Begin
            case R.id.main_createOrder_getData:
                String getDataAddress = HOST+"getData.php";
                String getDataReqStr = "var=country";
                sendRequest(getDataAddress,getDataReqStr,GETCOUNTRYDATE_SUCCESS,COMMON_FAIL);
                break;
            case R.id.main_createOrder_submit:

                String createOrderAddress = HOST+"createOrder.php";
                String createOrderReqStr = "country=" + selectCountry.getSelectedItem().toString() + "&farm=" + selectFarm.getSelectedItem().toString() +
                        "&classes=" + selectClass.getSelectedItem().toString() + "&weight=" + roastWeight.getText();
                sendRequest(createOrderAddress,createOrderReqStr,CREATEORDER_SUCCESS,COMMON_FAIL);
                break;
                //Create Order End & End Order Begin
            case R.id.main_endOrder_getData:
                String endOrderGetDataAddress = HOST+"getOrderData.php";
                String endOrderGetDataReqStr = "";
                sendRequest(endOrderGetDataAddress,endOrderGetDataReqStr,GETORDERDATA_SUCCESS,COMMON_FAIL);
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()){
            case R.id.main_createOrder_country:

                String getFarmDataAddress = HOST+"getData.php";
                String getFarmDataReqStr = "var=farm&country="+countryList.get(i).toString();
                sendRequest(getFarmDataAddress,getFarmDataReqStr,GETFARMDATE_SUCCESS,COMMON_FAIL);
                //Toast.makeText(MainActivity.this,countryList.get(i).toString(),Toast.LENGTH_SHORT).show();
                setCountryName(countryList.get(i).toString());
                break;
            case R.id.main_createOrder_farm:
                String getClassDataAddress = HOST+"getData.php";
                String getClassDataReqStr = "var=class&farm="+farmList.get(i).toString()+"&country="+getCountryName();
                sendRequest(getClassDataAddress,getClassDataReqStr,GETCLASSDATE_SUCCESS,COMMON_FAIL);
                //Toast.makeText(MainActivity.this,farmList.get(i).toString(),Toast.LENGTH_SHORT).show();
                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        final Data data = getGlobalJG().getData().get(i);

        Log.i(TAG,"MainActivity-"+i+"-"+data.getClassId());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("订单管理");
        builder.setMessage("请确认：\n<"+data.getCountry().toString()+data.getFarm().toString()+data.getClasses().toString()+">\n是否烘焙完成。");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG, "MainActivity-取消按钮-" + i);
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("结单", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG, "MainActivity-结单按钮-" + i);
                String endOrderAddress = HOST+"endOrder.php";
                String endOrderReqStr = "cId="+data.getClassId();
                sendRequest(endOrderAddress,endOrderReqStr,ENDORDER_SUCCESS,COMMON_FAIL);
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }



    //http

    private void sendRequest(String add, String reqStr, final int successTag, final int failTag){
        HttpUtil.sendHttpRequest(add, reqStr, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                JsonGeneral jsonGenerals = GsonUtil.parseJsonWithGson(response,JsonGeneral.class);//Json data is processed using Gson
                Log.i(TAG,"MainActivity-"+jsonGenerals.getStatus());
                //判断请求响应
                Message msg = new Message();
                if(jsonGenerals.getStatus().equals("Success")){
                    msg.what = successTag;
                    msg.obj = jsonGenerals;
                    setGlobalJG(jsonGenerals);
                }else{
                    msg.what = failTag;
                    msg.obj = jsonGenerals;
                }
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {
                Message msg = new Message();
                msg.what = CONNECT_ERROR;
                handler.sendMessage(msg);
                e.printStackTrace();
            }
        });
    }

    //Getter & Setter


    public List<String> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<String> countryList) {
        this.countryList = countryList;
    }

    public List<String> getFarmList() {
        return farmList;
    }

    public void setFarmList(List<String> farmList) {
        this.farmList = farmList;
    }

    public List<String> getClassList() {
        return classList;
    }

    public void setClassList(List<String> classList) {
        this.classList = classList;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public JsonGeneral getGlobalJG() {
        return globalJG;
    }

    public void setGlobalJG(JsonGeneral globalJG) {
        this.globalJG = globalJG;
    }
}
