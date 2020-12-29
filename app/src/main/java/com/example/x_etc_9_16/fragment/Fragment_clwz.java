package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.WZBH;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class Fragment_clwz extends Fragment {

    private EditText edCph;
    private Button btnChaxun;
    private WZBH wzbh;
    private List<WZBH> wzbhList;
    private FragmentTransaction fragmentTransaction;
    private TextView title;

    public Fragment_clwz(List<WZBH> wzbhList) {
        this.wzbhList = wzbhList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_clwz, null);
        initView(view);
        title.setText("车辆违章");


        
        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cph = "鲁A"+edCph.getText().toString();
                getOkhttp(cph);
            }
        });

        return view;
    }

    private void getOkhttp(final String cph) {
        //{"UserName":"user1","plate":"鲁A10001"}
        new OkHttpTo()
                .setUrl("get_peccancy_plate")
                .setJsonObject("UserName","user1")
                .setJsonObject("plate",cph)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (wzbhList.size()!=0){
                            for (int i=0;i<wzbhList.size();i++){
                                WZBH wzbh = wzbhList.get(i);
                                if (wzbh.getCph().equals(cph)){
                                    Fragment1(new Fragment_cxjg(wzbhList));
                                    return;
                                }
                            }
                        }
                        wzbh = new Gson().fromJson(jsonObject.toString(), WZBH.class);
                        wzbh.setCph(cph);
                        wzbhList.add(wzbh);
                        Fragment1(new Fragment_cxjg(wzbhList));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        edCph = view.findViewById(R.id.ed_cph);
        btnChaxun = view.findViewById(R.id.btn_chaxun);
    }
}
