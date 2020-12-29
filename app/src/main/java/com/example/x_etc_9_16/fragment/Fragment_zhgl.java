package com.example.x_etc_9_16.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.adapter.X_ZHGL_adapter;
import com.example.x_etc_9_16.bean.CZJL;
import com.example.x_etc_9_16.bean.ZHGL;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_zhgl extends Fragment {
    private TextView title;
    private ListView listview;
    private List<ZHGL> zhglList;
    private int yuzhi;
    private X_ZHGL_adapter adapter;
    private TextView plcz,czjl;
    private String S_time1,S_time2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhgl, null);
        initView(view);
        title.setText("账户管理");
        plcz.setText("批量充值");
        czjl.setText("充值记录");
        zhglList = new ArrayList<>();
        getTime();

        getOkHttp();

        plcz1();

        return view;
    }

    private String cp = "";


    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  星期E");
        Date date = new Date(System.currentTimeMillis());
        S_time1 = simpleDateFormat.format(date);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        Date date1 = new Date(System.currentTimeMillis());
        S_time2 = simpleDateFormat1.format(date1);


    }

    private void getOkHttp() {

        new OkHttpTo()
                .setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        zhglList.clear();
                        zhglList.addAll((List<ZHGL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<ZHGL>>() {}.getType()));

                        getyuzhi();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getyuzhi() {
        new OkHttpTo()
                .setUrl("get_car_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        yuzhi = jsonObject1.optInt("threshold");
                        getList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private int shengxiadeyue;


    private void getList() {
        if (adapter == null){
            adapter = new X_ZHGL_adapter(zhglList,yuzhi);
            listview.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }

        adapter.setMyOnclick(new X_ZHGL_adapter.MyOnclick() {
            @Override
            public void setOn(int position, int i, boolean isChecked, int balance) {
                if (i==1){

                    ZHGL zhgl = zhglList.get(position);

                    zhgl.setXz(isChecked);

                    shengxiadeyue = balance;

                }else if (i==2){
                    ZHGL zhgl = zhglList.get(position);
                    dialog(zhgl.getPlate());
                    shengxiadeyue = balance;
                }
            }
        });
    }

    private void plcz1() {
        plcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp = "";
                for (int i=0;i<zhglList.size();i++){
                    ZHGL zhgl = zhglList.get(i);
                    if (zhgl.isXz()){

                        if (cp.equals("")){
                            cp = zhgl.getPlate();
                        }else {
                            cp += ","+zhgl.getPlate();
                        }
                    }
                }

                if (cp.equals("")){
                    Toast.makeText(getContext(),"您没有选择车牌",Toast.LENGTH_SHORT).show();
                }else {
                    dialog("");
                }
            }
        });


    }

    private void dialog(final String plate) {

        Log.i("vvvvvvvvvvvvvvvvvvvvvv", "dialog: "+plate);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dralog,null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView txt_cph = view.findViewById(R.id.txt_cph);
        final EditText ed_jin = view.findViewById(R.id.ed_jine);
        Button btn_cz = view.findViewById(R.id.btn_chongzhi);
        Button btn_qx = view.findViewById(R.id.btn_quxiao);

        if (plate.equals("")){
            txt_cph.setText(cp);
        }else {
            txt_cph.setText(plate);
        }
        final AlertDialog dialog = builder.show();
        btn_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ed_jin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals("0")){
                    ed_jin.setText("");
                    Toast.makeText(getContext(),"不能以0开头",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("--------1", "onClick: -----"+cp);
                if (ed_jin.getText().toString().equals("")){
                    Toast.makeText(getContext(),"充值金额不能为空",Toast.LENGTH_SHORT).show();
                }else if (plate.equals("")){
                    for (int i=0;i<zhglList.size();i++){
                        ZHGL zhgl = zhglList.get(i);
                        if (zhgl.isXz()){
                            Log.d("ccccccc", "onClick: "+zhgl.getPlate());
                            setOkhttp(zhgl.getPlate(),ed_jin.getText().toString());
                        }
                    }
                }else {
                    setOkhttp(plate,ed_jin.getText().toString());
                }
            }
        });
    }


    private void setOkhttp(final String chepai, final String jine) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("提示");
        progressDialog.setMessage("网络访问中");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",chepai)
                .setJsonObject("balance",jine)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getTime();
                        Log.d("ccccccc", "onResponse1: "+S_time1);
                        Log.d("ccccccc", "onResponse1: "+S_time2);
                        int czjine = Integer.parseInt(jine);
                        CZJL czjl = new CZJL();
                        czjl.setTime1(S_time1);
                        czjl.setCzr("admin");
                        czjl.setCph(chepai);
                        czjl.setChongzhi(czjine);
                        czjl.setYue(shengxiadeyue);
                        czjl.setChongzhitime(S_time2);
                        if (czjl.save()){
                            Toast.makeText(getContext(),"充值成功",Toast.LENGTH_SHORT).show();
                            getOkHttp();
                        }else {
                            Toast.makeText(getContext(),"充值失败",Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        plcz.setText("");
        czjl.setText("");
    }

    private void initView(View view) {
        listview = view.findViewById(R.id.listview);
        title = getActivity().findViewById(R.id.title);
        czjl = getActivity().findViewById(R.id.biaoti_czjl);
        plcz = getActivity().findViewById(R.id.biaoti_plcz);
    }
}
