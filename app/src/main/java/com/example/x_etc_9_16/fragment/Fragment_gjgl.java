package com.example.x_etc_9_16.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.adapter.X_Elist_adapter;
import com.example.x_etc_9_16.adapter.X_diaad_apter;
import com.example.x_etc_9_16.bean.GJCX;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_gjgl extends Fragment {

    private TextView txt901;
    private TextView txtTime;
    private TextView txtRen;
    private Button btnXiangqing;
    private ExpandableListView elistview;
    private List<GJCX> gjcxList;
    private Map<String,List<GJCX>> map;
    private X_Elist_adapter adapter;
    private X_diaad_apter dia_adapter;
    private ListView listView;
    private TextView title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_gjcx, null);
        initView(view);
        title.setText("公交查询");
        getOkHttp();

        btn();

        return view;
    }

    private void btn() {
        btnXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
                builder.setView(view);


                listView = view.findViewById(R.id.dia_list);
                Button button = view.findViewById(R.id.dia_btn);
                TextView textView = view.findViewById(R.id.heji);
                textView.setText(renshu+"");

                    dia_adapter = new X_diaad_apter(gjcxList);
                    listView.setAdapter(dia_adapter);

                final AlertDialog dialog = builder.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    private void getOkHttp() {
        if (gjcxList == null){
            map = new HashMap<>();
            gjcxList = new ArrayList<>();
        }
        new OkHttpTo()
                .setUrl("get_bus_stop_distance")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        gjcxList.clear();
                        map.clear();
                        gjcxList.addAll((List<GJCX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<GJCX>>() {}.getType()));
                        getren();
                        List<GJCX> list1 = new Gson().fromJson(jsonObject.optJSONArray("中医院站").toString(),
                                new TypeToken<List<GJCX>>() {}.getType());

                        Collections.sort(list1, new Comparator<GJCX>() {
                            @Override
                            public int compare(GJCX o1, GJCX o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        map.put("中医院站",list1);

                        List<GJCX> list2 = new Gson().fromJson(jsonObject.optJSONArray("联想大厦站").toString(),
                                new TypeToken<List<GJCX>>(){}.getType());

                        Collections.sort(list2, new Comparator<GJCX>() {
                            @Override
                            public int compare(GJCX o1, GJCX o2) {
                                return o1.getDistance() - o2.getDistance();
                            }
                        });
                        map.put("联想大厦站",list2);
                        showListview();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showListview() {
        if (adapter == null){
            adapter = new X_Elist_adapter(map);
            elistview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private int renshu = 0;
    private void getren() {
        renshu = 0;
        for (int i=0;i<gjcxList.size();i++){
            GJCX gjcx = gjcxList.get(i);
            renshu += gjcx.getPerson();
        }
        txtRen.setText("当前承载能力："+renshu+"人");
    }


    private void initView(View view) {
        txt901 = view.findViewById(R.id.txt_901);
        txtTime = view.findViewById(R.id.txt_time);
        txtRen = view.findViewById(R.id.txt_ren);
        btnXiangqing = view.findViewById(R.id.btn_xiangqing);
        elistview = view.findViewById(R.id.elistview);
        title = getActivity().findViewById(R.id.title);
    }
}
