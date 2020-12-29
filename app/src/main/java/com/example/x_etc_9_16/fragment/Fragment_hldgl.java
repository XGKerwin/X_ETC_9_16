package com.example.x_etc_9_16.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.bean.HLD;
import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.adapter.X_Spinner_adapter;
import com.example.x_etc_9_16.adapter.X_hld_adapter;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_hldgl extends Fragment {

    private TextView title;
    private Spinner spinner;
    private Button btnPlcz;
    private Button btnCx;
    private ListView listview;
    private X_Spinner_adapter Sadapter;
    private List<HLD> hldList;
    private int pos = 0;
    private X_hld_adapter adapter;
    private String[] arr = {"路口升序","路口降序","红灯升序","红灯降序","黄灯升序","黄灯降序","绿灯升序","绿灯降序",};
    private ProgressDialog pd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_gldgl, null);
        initView(view);
        title.setText("红绿灯管理");
        Spin();

        getFor();

        spinshijian();

        btnchaxun();

        btn_plcz();

        return view;
    }

    private void btn_plcz() {
        btnPlcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "";
                for (int i=0;i<hldList.size();i++){
                    HLD hld = hldList.get(i);
                    if (hld.isIsxz()){
                        if (s.equals("")){
                            s = hld.getId()+"";
                        }else {
                            s+=hld.getId()+"";
                        }
                    }
                }
                if (s.equals("")){
                    Toast.makeText(getContext(),"请选择路口",Toast.LENGTH_SHORT).show();
                }else {
                    getDia("fuxuan",1);
                }
            }
        });
    }

    private void btnchaxun() {


        btnCx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(hldList, new Comparator<HLD>() {
                    @Override
                    public int compare(HLD o1, HLD o2) {
                        switch (pos){
                            case 0:
                                return o1.getNumber() - o2.getNumber();
                            case 1:
                                return o2.getNumber() - o1.getNumber();
                            case 2:
                                return o1.getRed() - o2.getRed();
                            case 3:
                                return o2.getRed() - o1.getRed();
                            case 4:
                                return o1.getYellow() - o2.getYellow();
                            case 5:
                                return o2.getYellow() - o1.getYellow();
                            case 6:
                                return o1.getGreen() - o2.getGreen();
                            case 7:
                                return o2.getGreen() - o1.getGreen();
                        }
                        return 0;
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void spinshijian() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Spin() {
        Sadapter = new X_Spinner_adapter(arr);
        spinner.setAdapter(Sadapter);
    }

    private void getFor() {

        if (hldList == null){
            hldList = new ArrayList<>();
        }else {
            hldList.clear();
        }

        for (int i=1;i<=5;i++){

            Log.d("cccccccccc", "getFor: "+i);
            getOkHttp(i);
        }
    }

    private void getOkHttp(final int i) {
        /**
         * {"UserName":"user1","number":"1"}
         */
        new OkHttpTo()
                .setUrl("get_traffic_light")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",i)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (hldList.size()==5){
                            hldList.clear();
                            pd.dismiss();
                        }

                        hldList.addAll((List<HLD>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<HLD>>(){}.getType()));

                        Collections.sort(hldList, new Comparator<HLD>() {
                            @Override
                            public int compare(HLD o1, HLD o2) {
                                return o1.getNumber()-o2.getNumber();
                            }
                        });

                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showList() {
        if (adapter == null){
            adapter = new X_hld_adapter(hldList);
            listview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

        adapter.setHLD(new X_hld_adapter.MyHLD() {
            @Override
            public void seton(int position, int i, boolean isChecked) {
                if (i==1){
                    HLD hld = hldList.get(position);
                    hld.setIsxz(isChecked);
                }else if (i==2){
                    getDia("btn", position+1);
                }
            }
        });

    }

    private void getDia(final String s, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.hld_dia,null);
        builder.setView(view);

        final EditText ed_hong = view.findViewById(R.id.ed_hong);
        final EditText ed_huang = view.findViewById(R.id.ed_huang);
        final EditText ed_lv = view.findViewById(R.id.ed_lv);
        Button btn_queding = view.findViewById(R.id.btn_queding);
        Button btn_quxiao = view.findViewById(R.id.btn_quxiao);

        final AlertDialog dialog = builder.show();
        btn_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_hong.getText().toString().equals("")){
                    Toast.makeText(getContext(),"请输入红灯周期",Toast.LENGTH_SHORT).show();
                }else if (ed_huang.getText().toString().equals("")){
                    Toast.makeText(getContext(),"黄灯周期不能为空",Toast.LENGTH_SHORT).show();
                }else if (ed_lv.getText().toString().equals("")){
                    Toast.makeText(getContext(),"绿灯周期不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    pd = new ProgressDialog(getContext());
                    pd.setCancelable(false);
                    pd.setTitle("提示");
                    pd.setMessage("网络访问中");
                    pd.show();
                    String huang = ed_huang.getText().toString();
                    String hong = ed_hong.getText().toString();
                    String lv = ed_lv.getText().toString();
                    if (s.equals("btn")){
                        setHLD(hong,huang,lv,position);
                    }else if (s.equals("fuxuan")){
                        for (int i=0;i<hldList.size();i++){
                            HLD hld = hldList.get(i);
                            if (hld.isIsxz()){
                                setHLD(hong,huang,lv,i);
                            }
                        }
                    }
                }
            }
        });

    }

    private void setHLD(final String hong, final String huang, final String lv, final int position) {
        //{"UserName":"user1","number":"1","red":"1","yellow":"1","green":"1"}
        new OkHttpTo()
                .setUrl("set_traffic_light")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",position)
                .setJsonObject("red",hong)
                .setJsonObject("yellow",huang)
                .setJsonObject("green",lv)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("dddddd", "onResponse: "+position);
                        Log.d("dddddd", "onResponse: "+hong);
                        Log.d("dddddd", "onResponse: "+huang);
                        Log.d("dddddd", "onResponse: "+lv);
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                        getFor();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        spinner = view.findViewById(R.id.spinner);
        btnPlcz = view.findViewById(R.id.btn_plcz);
        btnCx = view.findViewById(R.id.btn_cx);
        listview = view.findViewById(R.id.listview);
        title = getActivity().findViewById(R.id.title);
    }
}
