package com.example.x_etc_9_16.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.Wendu;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragment_eyht extends Fragment {
    private List<Wendu> wenduList;
    private LineChart LinchartLan;
    private TextView txtWdfenzhong;

    public Fragment_eyht(List<Wendu> wenduList) {
        this.wenduList = wenduList;
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getData();
            return false;
        }
    });

    private List<Entry> entryList;
    private List<Integer> integers;
    private List<String> strings;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_eyht, null);
        initView(view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }while (true);
            }
        }).start();



        return view;
    }

    private void getData() {
        Log.d("cccccccccaaaaaa", "getData: ssss1");
        if (wenduList.size() == 0){
            return;
        }
        if (entryList == null){
            entryList = new ArrayList<>();
            strings = new ArrayList<>();
            integers = new ArrayList<>();
        }else {
            entryList.clear();
            strings.clear();
            integers.clear();
        }

        for (int i=0;i<wenduList.size();i++){
            Wendu wendu = wenduList.get(i);
            entryList.add(new Entry(i,wendu.getCo2()));
            strings.add(((i+1)*3)+"");
            integers.add(wendu.getCo2());
        }
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        txtWdfenzhong.setText("过去1分中最大相对浓度："+integers.get(integers.size()-1));
        LineDataSet lineDataSet = new LineDataSet(entryList,"");
        lineDataSet.setCircleColor(Color.WHITE);
        lineDataSet.setColor(Color.WHITE);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setLineWidth(4);
        lineDataSet.setValueTextSize(20);


        LineData lineData = new LineData(lineDataSet);

        YAxis yAxis = LinchartLan.getAxisRight();
        yAxis.setEnabled(false);
        YAxis yAxis1 = LinchartLan.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(10000);
        yAxis1.setTextColor(Color.WHITE);
        yAxis1.setDrawAxisLine(false);
        XAxis xAxis = LinchartLan.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(20);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setLabelCount(strings.size());
        xAxis.setGranularity(1);
        Log.d("cccccccccaaaaaa", "getData: ssss2");

        LinchartLan.getAxisRight().setEnabled(false);
        LinchartLan.setData(lineData);
        LinchartLan.setTouchEnabled(false);
        LinchartLan.getDescription().setText("(S)");
        Log.d("cccccccccaaaaaa", "getData: ssss");
        LinchartLan.invalidate();

    }


    private void initView(View view) {
        LinchartLan = view.findViewById(R.id.Linchart_lan);
        txtWdfenzhong = view.findViewById(R.id.txt_wdfenzhong);
    }
}
