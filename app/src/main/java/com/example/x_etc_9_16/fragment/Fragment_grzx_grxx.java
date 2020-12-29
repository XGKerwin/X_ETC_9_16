package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.bean.GRXX;
import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.adapter.X_GRXX_adapter;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_grzx_grxx extends Fragment {

    private TextView name;
    private TextView txtXingbie;
    private TextView txtTel;
    private TextView txtShenfenzheng;
    private TextView txtTime;
    private GridView gridview;
    private List<GRXX> grxxList;
    private X_GRXX_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_grxx, null);
        initView(view);

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (grxxList == null) {
            grxxList = new ArrayList<>();
        } else {
            grxxList.clear();
        }
        //{"UserName":"user1","Password":"1234"}
        new OkHttpTo()
                .setUrl("get_root")
                .setJsonObject("UserName", "user1")
                .setJsonObject("Password", "1234")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        grxxList.add(new Gson().fromJson(jsonObject.toString(), GRXX.class));
                        txtShenfenzheng.setText("身份证号：" + grxxList.get(0).getIdnumber());
                        txtTel.setText("手机号码：" + grxxList.get(0).getTel());
                        txtXingbie.setText("性别：" + grxxList.get(0).getSex());
                        txtTime.setText("注册时间：" + grxxList.get(0).getRegistered_time());
                        name.setText("姓名："+grxxList.get(0).getName());
                        showlistview();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showlistview() {
        List<String> strings = grxxList.get(0).getPlate();
        if (adapter == null){
            adapter = new X_GRXX_adapter(strings);
            gridview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }

    }

    private void initView(View view) {
        name = view.findViewById(R.id.name);
        txtXingbie = view.findViewById(R.id.txt_xingbie);
        txtTel = view.findViewById(R.id.txt_tel);
        txtShenfenzheng = view.findViewById(R.id.txt_shenfenzheng);
        txtTime = view.findViewById(R.id.txt_time);
        gridview = view.findViewById(R.id.gridview);
    }
}
