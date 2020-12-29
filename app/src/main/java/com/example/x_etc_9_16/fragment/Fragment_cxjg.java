package com.example.x_etc_9_16.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_9_16.Activity.Jkzp_12Activity;
import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.WZBH;
import com.example.x_etc_9_16.bean.WZXX;
import com.example.x_etc_9_16.bean.WZnumber;
import com.example.x_etc_9_16.bean.WZxx1;
import com.example.x_etc_9_16.adapter.X_clwz_adapter1;
import com.example.x_etc_9_16.adapter.X_clwz_adapter2;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_cxjg extends Fragment {

    private ListView listview1;
    private ListView listview2;
    private ImageView img_button;
    private FragmentTransaction fragmentTransaction;
    private TextView title;
    private X_clwz_adapter1 adapter1;
    private X_clwz_adapter2 adapter2;
    private List<WZBH> wzbhList;
    private List<WZxx1> wZxx1s;
    private List<WZnumber> numberlist;
    private List<WZXX> wzxxList;

    public Fragment_cxjg(List<WZBH> wzbhList) {
        this.wzbhList = wzbhList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_cxjg1, null);
        initView(view);
        if (wZxx1s==null){
            wZxx1s = new ArrayList<>();
        }else {
            wZxx1s.clear();
        }

        title.setText("查询结果");

        getOkbianhao();

        btn();

        return view;
    }

    private void getOkbianhao() {
        if (numberlist == null){
            numberlist = new ArrayList<>();
        }else {
            numberlist.clear();
        }
        new OkHttpTo()
                .setUrl("get_all_car_peccancy")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        numberlist.addAll(((List<WZnumber>)new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<WZnumber>>(){}.getType())));
                        getwzxx();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getwzxx() {
        if (wzxxList == null){
            wzxxList = new ArrayList<>();
        }else {
            wzxxList.clear();
        }
        //{"UserName":"user1"}
        new OkHttpTo()
                .setUrl("get_pessancy_infos")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wzxxList.addAll(((List<WZXX>)new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<WZXX>>(){}.getType())));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        for (int i=0;i<wzbhList.size();i++){
            int fakuan=0,koufen1=0;
            WZBH wzbh = wzbhList.get(i);
            List<String> strings = wzbh.getId();
            for (int q=0;q<strings.size();q++){
                String s = strings.get(q);
                for (int j=0;j<numberlist.size();j++){
                    WZnumber wZnumber = numberlist.get(j);
                    if (s.equals(wZnumber.getId())){
                        Log.d("aaaaaaa", "getdata: "+wZnumber.getInfoid());
                        for (int c=0;c<wzxxList.size();c++){
                            WZXX wzxx = wzxxList.get(c);
                            Log.d("ccccccc", "getdata: "+wzxxList.size());
                            if (wzxx.getInfoid().equals(wZnumber.getInfoid())){
                                Log.d("zzzzzzz", "getdata: "+wzxx.getRoad());
                                koufen1 += wzxx.getDeduct();
                                fakuan += wzxx.getFine();
                                wzbh.setFakuan(fakuan);
                                wzbh.setKoufen(koufen1);
                                wzbh.setTime(wZnumber.getTime());
                                wzbh.setMsg(wzxx.getMessage());
                            }
                        }

                    }
                }
            }
            wzbh.setWcl(strings.size());

        }
        showlist1();
    }

    private void showlist1() {

        if (adapter1 == null){
            adapter1 = new X_clwz_adapter1(numberlist,wzbhList,wzxxList);
            listview1.setAdapter(adapter1);
        }else {
            adapter1.notifyDataSetChanged();
        }
        adapter1.setCXJG(new X_clwz_adapter1.MyCXJG() {
            @Override
            public void setcxjg(int position, String i) {
                if (i.equals("btn")){
                    wZxx1s.clear();
                    WZBH wzbh = wzbhList.get(position);
                    List<String> strings = wzbh.getId();
                    for (int j=0;j<strings.size();j++){
                        String ss = strings.get(j);
                        for (int q=0;q<numberlist.size();q++){
                            WZnumber wZnumber = numberlist.get(q);
                            if (wZnumber.getId().equals(ss)){
                                for (int x=0;x<wzxxList.size();x++){
                                    WZXX wzxx = wzxxList.get(x);
                                    if (wzxx.getInfoid().equals(wZnumber.getInfoid())){
                                        WZxx1 wZxx1 = new WZxx1();
                                        wZxx1.setKoufen(wzxx.getDeduct());
                                        wZxx1.setFakuan(wzxx.getFine());
                                        wZxx1.setMsg(wzxx.getMessage());
                                        wZxx1.setTime(wZnumber.getTime());
                                        wZxx1.setDaolu(wzxx.getRoad());
                                        wZxx1s.add(wZxx1);
                                    }
                                }
                            }
                        }
                    }
                    Log.d("qqqqqqq", "setcxjg: "+wZxx1s.size());
                        adapter2 = new X_clwz_adapter2(wZxx1s);
                        listview2.setAdapter(adapter2);
                        adapter2.setjk(new X_clwz_adapter2.MyJK() {
                            @Override
                            public void setjian(String daolu) {
                                Intent intent = new Intent(getContext(), Jkzp_12Activity.class)
                                        .putExtra("1",daolu);
                                startActivity(intent);
                            }
                        });
                }else if (i.equals("jian")){
                    wzbhList.remove(position);
                    wZxx1s.clear();
                    adapter1.notifyDataSetChanged();
                    adapter2 = new X_clwz_adapter2(wZxx1s);
                    listview2.setAdapter(adapter2);
                }
            }
        });


    }

    private void btn() {
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1(new Fragment_clwz(wzbhList));
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }


    private void initView(View view) {
        title = getActivity().findViewById(R.id.title);
        img_button = view.findViewById(R.id.img_button);
        listview1 = view.findViewById(R.id.listview1);
        listview2 = view.findViewById(R.id.listview2);
    }

}
