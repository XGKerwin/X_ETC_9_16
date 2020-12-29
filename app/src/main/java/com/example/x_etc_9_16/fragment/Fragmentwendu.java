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

public class Fragmentwendu extends Fragment {

    private List<Wendu> wenduList;
    private LineChart Linchart;
    private TextView txtWdfenzhong;
    private List<Entry> entities;
    private List<String> strings;
    private List<Integer> integers;

    public Fragmentwendu(List<Wendu> wenduList) {
        this.wenduList = wenduList;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("cccccccccc", "handleMessage: ");
            getdata();
            return false;
        }
    });



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wd, null);
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

    private void getdata() {
        Log.d("vvvvvv", "getdata: "+wenduList.size());
        if (wenduList.size() == 0){
            return;
        }
        Log.d("vvvvvv", "getdata: "+wenduList.size());
        if (entities == null){
            entities = new ArrayList<>();
            strings = new ArrayList<>();
            integers = new ArrayList<>();
        }else {
            integers.clear();
            entities.clear();
            strings.clear();
        }
        for (int i=0;i<wenduList.size();i++){
            Wendu wendu = wenduList.get(i);
            entities.add(new Entry(i,wendu.getTemperature()));
            strings.add(((i+1)*3)+"");
            integers.add(wendu.getTemperature());
        }
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        txtWdfenzhong.setText("过去1分钟最高气温："+integers.get(integers.size()-1)+"，最低气温"+integers.get(0));
        LineDataSet dataSet = new LineDataSet(entities,"");
        dataSet.setCircleColor(Color.GRAY);
        dataSet.setCircleHoleRadius(5f);
        dataSet.setColor(Color.GRAY);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(4);
        dataSet.setDrawCircleHole(false);
        LineData data = new LineData(dataSet);

        YAxis yAxis = Linchart.getAxisRight();
        yAxis.setEnabled(false);
        YAxis yAxis1 = Linchart.getAxisLeft();
        yAxis1.setAxisMinimum(0);
        yAxis1.setAxisMaximum(40);
        yAxis1.setDrawAxisLine(false);

        XAxis xAxis = Linchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(15);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(strings.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        Linchart.setData(data);
        Linchart.getLegend().setEnabled(false);
        Linchart.getDescription().setText("(S)");
        Linchart.setScaleEnabled(false);
        Linchart.setTouchEnabled(false);
        Linchart.invalidate();

    }

    private void initView(View view) {
        Linchart = view.findViewById(R.id.Linchart);
        txtWdfenzhong = view.findViewById(R.id.txt_wdfenzhong);
    }
}
