package com.example.x_etc_9_16.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.bean.LKCX;
import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fragment_lkcx extends Fragment {

    private TextView txtKsTop;
    private TextView txtKsBotton;
    private TextView txtKsLeft;
    private TextView txtGsRight;
    private TextView txtXueyuanlu;
    private TextView txtLianxianglu;
    private TextView txtXingfulu;
    private TextView txtYiyuanlu;
    private TextView txtTcc;
    private TextView txtTime;
    private TextView txtXingqi;
    private TextView txtWendu;
    private TextView txtShidu;
    private TextView txtPm;
    private ImageView imgShuaxin;
    private List<LKCX> lkcxList;
    private OkHttpTo okHttpTo;
    private TextView title;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_lkcx, null);
        initView(view);
        title.setText("路况查询");

        getTime();

        imgShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttpwendu();
                okHttpTo.setIsLoop(false);
                getOkHttp();
            }
        });
        getOkHttpwendu();

        getOkHttp();        //获取路况信息


        return view;
    }

    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String s = simpleDateFormat.format(date);
        txtTime.setText(s);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("E");
        Date date1 = new Date(System.currentTimeMillis());
        String s1 = simpleDateFormat1.format(date1);
        txtXingqi.setText(s1);
    }

    private void getOkHttpwendu() {
        /**
         *     "temperature": 30,
         *     "humidity": 15,
         *     "illumination": 3992,
         *     "co2": 6163,
         *     "pm25": 31,
         */
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        txtWendu.setText("温度："+jsonObject.optString("temperature")+" ℃");
                        txtShidu.setText("相对湿度："+jsonObject.optString("humidity")+"％");
                        txtPm.setText("PM2.5："+jsonObject.optString("pm25")+"ug/m3");
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        if (lkcxList == null){
            lkcxList = new ArrayList<>();
        }else {
            lkcxList.clear();
        }

        okHttpTo = new OkHttpTo();
                okHttpTo.setUrl("get_road_status")
                .setJsonObject("UserName","user1")
                .setJsonObject("RoadId",0)
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        lkcxList.clear();
                        Log.d("cccccc", "onResponse: "+lkcxList.size());
                        lkcxList.addAll((List<LKCX>)new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<LKCX>>(){}.getType()));
                        Log.d("--------", "onResponse: "+lkcxList.size());
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void show() {
        for (int i=0;i<lkcxList.size();i++){        //根据道路设置拥挤程度
            LKCX lkcx = lkcxList.get(i);
            switch (lkcx.getName()){
                case "学院路":
                    show1(lkcx.getState(),txtXueyuanlu);
                    break;
                case "联想路":
                    show1(lkcx.getState(),txtLianxianglu);
                    break;
                case "医院路":
                    show1(lkcx.getState(),txtYiyuanlu);
                    break;
                case "幸福路":
                    show1(lkcx.getState(),txtXingfulu);
                    break;
                case "环城快速路":
                    show1(lkcx.getState(),txtKsBotton);
                    show1(lkcx.getState(),txtKsLeft);
                    show1(lkcx.getState(),txtKsTop);
                    break;
                case "环城高速":
                    show1(lkcx.getState(),txtGsRight);
                    break;
                case "停车场":
                    show1(lkcx.getState(),txtTcc);
                    break;
            }
        }
    }

    @SuppressLint("ResourceType")
    private void show1(String state, TextView textView) {       //根据拥挤程度改变textview的颜色
        switch (state){
            case "1":
                textView.setBackgroundColor(Color.parseColor("#6ab82e"));
                break;
            case "2":
                textView.setBackgroundColor(Color.parseColor("#ece93a"));
                break;
            case "3":
                textView.setBackgroundColor(Color.parseColor("#f49b25"));
                break;
            case "4":
                textView.setBackgroundColor(Color.parseColor("#e33532"));
                break;
            case "5":
                textView.setBackgroundColor(Color.parseColor("#b01e23"));
                break;
        }

    }


    private void initView(View view) {
        txtKsTop = view.findViewById(R.id.txt_ks_top);
        txtKsBotton = view.findViewById(R.id.txt_ks_botton);
        txtKsLeft = view.findViewById(R.id.txt_ks_left);
        txtGsRight = view.findViewById(R.id.txt_gs_right);
        txtXueyuanlu = view.findViewById(R.id.txt_xueyuanlu);
        txtLianxianglu = view.findViewById(R.id.txt_lianxianglu);
        txtXingfulu = view.findViewById(R.id.txt_xingfulu);
        txtYiyuanlu = view.findViewById(R.id.txt_yiyuanlu);
        txtTcc = view.findViewById(R.id.txt_tcc);
        txtTime = view.findViewById(R.id.txt_time);
        txtXingqi = view.findViewById(R.id.txt_xingqi);
        txtWendu = view.findViewById(R.id.txt_wendu);
        txtShidu = view.findViewById(R.id.txt_shidu);
        txtPm = view.findViewById(R.id.txt_pm);
        imgShuaxin = view.findViewById(R.id.img_shuaxin);
        title = getActivity().findViewById(R.id.title);
    }
}
