package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.SJFX;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_1;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_2;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_3;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_4;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_5;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_6;
import com.example.x_etc_9_16.fragment.Fragment_sjfx_7;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment_sjfx extends Fragment {

    private List<Fragment> fragments;
    private List<SJFX> sjfxList, sjfxyes,sjfxno;
    private ViewPager viewpager;
    private LinearLayout Linear;
    private Map<String,String> mapyes, mapno;
    private TextView title;
    private Map<String,Integer> map2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx, null);
        initView(view);
        fragments = new ArrayList<>();
        title.setText("数据分析");

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (sjfxList == null) {
            sjfxList = new ArrayList<>();
        } else {
            sjfxList.clear();
        }
        new OkHttpTo()
                .setUrl("get_peccancy")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sjfxList.addAll((Collection<? extends SJFX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SJFX>>() {
                                }.getType()));
                        showdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private int sum = 0;
    private int t5 = 0,t3 = 0, t2 = 0;
    private int f1,f2;

    private void showdata() {

        getfragment1();
        getfragment4();
        getfragment5();
        getfragment6();
        getfragment7();

        sum = sjfxList.size();

        sjfxno = new ArrayList<>();

        map2 = new HashMap<>();
        for (int i = 0; i< sjfxyes.size(); i++){
            SJFX sjfx = sjfxyes.get(i);
            if (map2.get(sjfx.getCarnumber())==null){   //车牌号为空的话
                map2.put(sjfx.getCarnumber(),1);        //将车牌传入map
            }else {
                map2.put(sjfx.getCarnumber(),map2.get(sjfx.getCarnumber())+1);  //否则加1
            }
        }

        /**
         * 车辆违章次数分布
         */

        List<String> strings = new ArrayList<>();
        strings.addAll(map2.keySet());
        for (int i=0;i<strings.size();i++){
            if (map2.get(strings.get(i))>5){            //违章超过5个的
                t5++;
            }else if (map2.get(strings.get(i)) >=3){    //违章超过3个的
                t3++;
            }else if (map2.get(strings.get(i)) >= 1){   //违章超过4个的
                t2++;
            }
        }
        /**
         * 有无重复违章记录的车辆
         */
        for (int i=0;i<strings.size();i++){
            if (map2.get(strings.get(i))==1){
                f1++;
            }else {
                f2++;
            }
        }

        showfragent();
    }

    private Map<String,Integer> map7;
    private List<Map.Entry<String,Integer>> mas = new ArrayList<>();
    private void getfragment7() {
        map7 = new HashMap<>();

        for (int i = 0; i< sjfxyes.size(); i++){
            SJFX sjfx = sjfxyes.get(i);
            if (map7.get(sjfx.getPaddr())==null){   //车牌号为空的话
                map7.put(sjfx.getPaddr(),1);        //将车牌传入map
            }else {
                map7.put(sjfx.getPaddr(),map7.get(sjfx.getPaddr())+1);  //否则加1
            }
        }

        mas.addAll(map7.entrySet());
        Collections.sort(mas, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });

        Log.i("vvvvvvvvvvvvvvvsss", "getfragment7: "+mas.toString());

        Log.i("vvvvvvvvvvvvvvvvv", "getfragment7: "+mas.get(0).getValue());
        Log.i("vvvvvvvvvvvvvvvvv", "getfragment7: "+mas.get(0).getKey());
        Log.i("vvvvvvvvvvvvvvvvv", "getfragment7: "+mas.get(0));


    }

    private int t01=0,t23=0,t45=0,t67=0,t89=0,t1011=0,t1213=0,t1415=0,t1617=0,t1819=0,t2021=0,t2223=0;

    private void getfragment6() {

        for (int i = 0; i< sjfxyes.size(); i++){
            String Birth = sjfxyes.get(i).getDatetime().substring(11,13);
            String time = Birth;
            if ((time.equals("00")||(time.equals("01")))){
                t01++;
                Log.i("getfragment6", "getfragment6: "+time+"     "+t01);
            }else if ((time.equals("02")||(time.equals("03")))){
                t23++;
                Log.i("getfragment6t23", "getfragment6: "+time+"     "+t23);
            }else if ((time.equals("04")||(time.equals("05")))){
                t45++;
                Log.i("getfragment6t45", "getfragment6: "+time+"     "+t45);
            }else if ((time.equals("06")||(time.equals("07")))){
                t67++;
                Log.i("getfragment6t67", "getfragment6: "+time+"     "+t67);
            }else if ((time.equals("08")||(time.equals("09")))){
                t89++;
                Log.i("getfragment6t89", "getfragment6: "+time+"     "+t89);
            }else if ((time.equals("10")||(time.equals("11")))){
                t1011++;
                Log.i("getfragment6t1011", "getfragment6: "+time+"     "+t1011);
            }else if ((time.equals("12")||(time.equals("13")))){
                t1213++;
                Log.i("getfragment6t1213", "getfragment6: "+time+"     "+t1213);
            }else if ((time.equals("14")||(time.equals("15")))){
                t1415++;
                Log.i("getfragment6t1415", "getfragment6: "+time+"     "+t1415);
            }else if ((time.equals("16")||(time.equals("17")))){
                t1617++;
                Log.i("getfragment6t1617", "getfragment6: "+time+"     "+t1617);
            }else if ((time.equals("18")||(time.equals("19")))){
                t1819++;
                Log.i("getfragment6t1819", "getfragment6: "+time+"     "+t1819);
            }else if ((time.equals("20")||(time.equals("21")))){
                t2021++;
                Log.i("getfragment6t2021", "getfragment6: "+time+"     "+t2021);
            }else if ((time.equals("22")||(time.equals("23")))){
                t2223++;
                Log.i("getfragment6t2223", "getfragment6: "+time+"     "+t2223);
            }
        }



    }

    private int nanyes=0,nvyes=0;
    private int nanno=0,nvno=0;

    private void getfragment5() {
        /**
         * 男女有无车辆违章统计
         */

        /**
         * 所有违章的男生和女生
         */
        for (int i=0;i<sjfxyes.size();i++){
            SJFX sjfx = sjfxyes.get(i);
            if (sjfx.getSex().equals("男")){
                nanyes++;
            }else {
                nvyes++;
            }
        }

        /**
         * 多有未违章的男生和女生
         */
        for (int i=0;i<sjfxno.size();i++){
            SJFX sjfx = sjfxno.get(i);
            if (sjfx.getSex().equals("男")){
                nanno++;
            }else {
                nvno++;
            }
        }

        Log.i("getfragment5", "getfragment5: "+nanyes);
        Log.i("getfragment5", "getfragment5: "+nanno);
        Log.i("getfragment5", "getfragment5: "+nvno);
        Log.i("getfragment5", "getfragment5: "+nvyes);


    }

    private int y49 =0, y48 =0, y47 =0, y46 =0, y45 =0;
    private int n49 =0, n48 =0, n47 =0, n46 =0, n45 =0;
    private void getfragment4() {
        /**
         * 有违章的几零后统计
         */
        for (int i = 0; i< sjfxyes.size(); i++){
            String Birth = sjfxyes.get(i).getShengri().substring(0,4);
            String nian = ((Integer.parseInt(Birth)-1900)/10)+"";
            Log.i("lllllllllllllllllll", "getfragment4: "+nian);
            if (nian.equals("9")){
                y49++;
            }else if (nian.equals("8")){
                y48++;
            }else if (nian.equals("7")){
                y47++;
            }else if (nian.equals("6")){
                y46++;
            }else if (nian.equals("5")){
                y45++;
            }
        }
        /**
         * 无违章的记录统计
         */
        for (int i=0;i<sjfxno.size();i++){
            String Birth = sjfxno.get(i).getShengri().substring(0,4);
            String nian = ((Integer.parseInt(Birth)-1900)/10)+"";
            if (nian.equals("9")){
                n49++;
            }else if (nian.equals("8")){
                n48++;
            }else if (nian.equals("7")){
                n47++;
            }else if (nian.equals("6")){
                n46++;
            }else if (nian.equals("5")){
                n45++;
            }
        }
    }


    private void getfragment1() {

        /**
         *获取有违章车辆和无违章车辆的占比统计
         */

        sjfxyes = new ArrayList<>();
        mapno = new HashMap<>();
        mapyes = new HashMap<>();
        sjfxno = new ArrayList<>();

        for (int i = 0; i < sjfxList.size(); i++) {
            SJFX sjfx = sjfxList.get(i);
            if (sjfx.getPaddr().equals("")) {
                mapno.put(sjfx.getCarnumber(),"3");      //将无违章的车牌号存入map集合里
            }else {
                sjfxyes.add(sjfx);                       //获取出违章的全部记录
            }
        }
        for (int i = 0; i< sjfxyes.size(); i++){
            SJFX sjfx = sjfxyes.get(i);                  //将违章车牌存入map集合中
            mapyes.put(sjfx.getCarnumber(),"2");
        }

        for (int i=0;i<sjfxList.size();i++){
            SJFX sjfx = sjfxList.get(i);
            if (sjfx.getPaddr().equals("")){
                sjfxno.add(sjfx);
            }
        }


    }

    private void showfragent() {
        fragments.add(new Fragment_sjfx_1(mapno.size(),mapyes.size()));
        fragments.add(new Fragment_sjfx_2(f1,f2));
        fragments.add(new Fragment_sjfx_3(t2,t3,t5));
        fragments.add(new Fragment_sjfx_4(y49, y48, y47, y46, y45, n49, n48, n47, n46, n45));
        fragments.add(new Fragment_sjfx_5(nanyes,nanno,nvyes,nvno));
        fragments.add(new Fragment_sjfx_6(t01,t23,t45,t67,t89,t1011,t1213,t1415,t1617,t1819,t2021,t2223,sjfxyes.size()));
        fragments.add(new Fragment_sjfx_7(mas,sjfxyes.size()));

        viewpager.setAdapter(new Pageradapter(getActivity().getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i=0;i<Linear.getChildCount();i++){
                    ImageView imageView = (ImageView) Linear.getChildAt(i);
                    if (position == i){
                        imageView.setImageResource(R.drawable.yuan_hui_hei1);
                    }else {
                        imageView.setImageResource(R.drawable.yuan_bai_hei1);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int i=0;i<fragments.size();i++){
            ImageView imageView = new ImageView(getContext());
            if (i==0){
                imageView.setImageResource(R.drawable.yuan_hui_hei1);
            }else {
                imageView.setImageResource(R.drawable.yuan_bai_hei1);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60,60));
            imageView.setPadding(10,10,10,10);
            Linear.addView(imageView);
        }

    }

    class Pageradapter extends FragmentPagerAdapter{
        public Pageradapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private void initView(View view) {
        viewpager = view.findViewById(R.id.viewpager);
        Linear = view.findViewById(R.id.Linear);
        title = getActivity().findViewById(R.id.title);
    }
}
