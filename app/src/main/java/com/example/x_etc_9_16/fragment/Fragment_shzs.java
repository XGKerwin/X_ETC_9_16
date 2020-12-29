package com.example.x_etc_9_16.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
import com.example.x_etc_9_16.bean.SHZS;
import com.example.x_etc_9_16.bean.Wendu;
import com.example.x_etc_9_16.adapter.X_SHZS_adapter;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fragment_shzs extends Fragment {
    private TextView txtJintianwendu;
    private TextView txtJintian;
    private ImageView ivFlash;
    private LineChart linechart;
    private GridView gridview;
    private ViewPager vierpager;
    private LinearLayout linearLayout;
    private OkHttpTo okHttpTo;
    private List<SHZS> shzsList;
    private List<Wendu> wenduList;
    private X_SHZS_adapter adapter;
    private List<Entry> entryListmax,entryListmin;
    private List<Fragment> fragments;
    private String[] arr = {"空气质量","温度","相对湿度","二氧化碳"};
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_shzs, null);
        initView(view);
        title.setText("实时显示");

        wenduList = new ArrayList<>();

        getOkHttp();

        getfragment();

        return view;
    }

    private void getfragment() {
        fragments = new ArrayList<>();
        fragments.add(new Fragmentkqzl(wenduList));
        fragments.add(new Fragmentwendu(wenduList));
        fragments.add(new Fragmentshidu(wenduList));
        fragments.add(new Fragment_eyht(wenduList));

        vierpager.setAdapter(new Pageradapter(getActivity().getSupportFragmentManager()));
        vierpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int j=0;j<linearLayout.getChildCount();j++){
                    TextView textView = (TextView) linearLayout.getChildAt(j);
                    if (position == j){
                        textView.setBackgroundResource(R.drawable.bg_xian);
                    }else {
                        textView.setBackgroundResource(R.drawable.bg_wu);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        for (int i=0;i<arr.length;i++){
            TextView textView = new TextView(getContext());
            textView.setText(arr[i]);
            textView.setTextSize(25f);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setWidth(300);
            if (i == 0){
                textView.setBackgroundResource(R.drawable.bg_xian);
            }
            linearLayout.addView(textView);
        }
    }


    private void getOkHttp() {
        if (shzsList == null){
            shzsList = new ArrayList<>();
        }
        okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("get_weather_info")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        shzsList.clear();
                        txtJintianwendu.setText(jsonObject.optString("temperature")+"°C");
                        shzsList.addAll((List<SHZS>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SHZS>>(){}.getType()));
                        txtJintian.setText("今天：" +shzsList.get(1).getInterval().replace("~","-")+" ℃");
                        getOkHttp1();
                        showtu();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getOkHttp1() {
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wenduList.add(new Gson().fromJson(jsonObject.toString(),Wendu.class));
                        if (wenduList.size()==21){
                            wenduList.remove(0);
                        }
                        showList();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showtu() {
        entryListmax = new ArrayList<>();
        entryListmin = new ArrayList<>();

        for (int i=0;i<shzsList.size();i++){
            String[] arr = shzsList.get(i).getInterval().split("~");
            entryListmax.add(new Entry(i,Integer.parseInt(arr[1])));
            entryListmin.add(new Entry(i,Integer.parseInt(arr[0])));
        }

        LineDataSet dataSet = new LineDataSet(entryListmax,"");
        LineDataSet dataSet1 = new LineDataSet(entryListmin,"");
        dataSet.setCircleColor(Color.RED);
        dataSet.setColor(Color.RED);
        dataSet.setDrawCircleHole(false);
        dataSet.setCircleHoleRadius(5);
        dataSet.setLineWidth(4);
        dataSet1.setCircleColor(Color.BLUE);
        dataSet1.setColor(Color.BLUE);
        dataSet1.setDrawCircleHole(false);
        dataSet1.setCircleHoleRadius(5);
        dataSet1.setLineWidth(4);
        List<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(dataSet);
        iLineDataSets.add(dataSet1);
        LineData lineData = new LineData(iLineDataSets);
        linechart.setData(lineData);
        linechart.getAxisRight().setEnabled(false);
        YAxis yAxis = linechart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setTextColor(Color.TRANSPARENT);
        linechart.animateXY(1500, 1000);
        linechart.getXAxis().setEnabled(false);
        linechart.getLegend().setEnabled(false);
        linechart.getDescription().setEnabled(false);
        linechart.setTouchEnabled(false);
        linechart.invalidate();
    }

    private void showList() {
        if (adapter == null){
            adapter = new X_SHZS_adapter(wenduList);
            gridview.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initView(View view) {
        txtJintianwendu = view.findViewById(R.id.txt_jintianwendu);
        txtJintian = view.findViewById(R.id.txt_jintian);
        ivFlash = view.findViewById(R.id.iv_flash);
        linechart = view.findViewById(R.id.linechart);
        gridview = view.findViewById(R.id.gridview);
        vierpager = view.findViewById(R.id.vierpager);
        linearLayout = view.findViewById(R.id.linear_layout);
        title = getActivity().findViewById(R.id.title);
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
}
