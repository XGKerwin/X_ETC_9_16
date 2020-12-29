package com.example.x_etc_9_16.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class Fragmentshidu extends Fragment {
    private List<Wendu> wenduList;
    private LineChart Linchart;
    private TextView txtWdfenzhong;

    public Fragmentshidu(List<Wendu> wenduList) {
        this.wenduList = wenduList;
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            getdata();
            return false;
        }
    });

    private List<Entry> entries;
    private List<String> strings;
    private List<Integer> integers;

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
        if (wenduList.size() == 0){
            return;
        }
        if (entries == null){
            entries = new ArrayList<>();
            strings = new ArrayList<>();
            integers = new ArrayList<>();
        }else {
            entries.clear();
            strings.clear();
            integers.clear();
        }
        for (int i=0;i<wenduList.size();i++){
            Wendu wendu = wenduList.get(i);
            entries.add(new Entry(i,wendu.getHumidity()));
            integers.add(wendu.getHumidity());
            strings.add(((i+1)*3)+"");
        }
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        txtWdfenzhong.setText("过去1分中最高气温："+integers.get(integers.size()-1)+"℃，最低气温："+integers.get(0)+"℃");
        LineDataSet lineDataSet = new LineDataSet(entries,"");
        lineDataSet.setDrawValues(false);
        lineDataSet.setColor(Color.GRAY);
        lineDataSet.setCircleColor(Color.GRAY);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setLineWidth(4);
        lineDataSet.setValueTextSize(20);
        LineData data = new LineData(lineDataSet);

        YAxis yAxis = Linchart.getAxisRight();
        yAxis.setEnabled(false);
        YAxis yAxis1 = Linchart.getAxisLeft();
        yAxis1.setAxisMaximum(0);
        yAxis1.setAxisMaximum(65);
        yAxis1.setDrawAxisLine(false);

        XAxis xAxis = Linchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(20);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));

        xAxis.setLabelCount(strings.size());

        Linchart.getDescription().setText("(S)");
        Linchart.setData(data);
        Linchart.getLegend().setEnabled(false);
        Linchart.setScaleEnabled(false);
        Linchart.setTouchEnabled(false);
        Linchart.invalidate();

    }

    private void initView(View view) {
        Linchart = view.findViewById(R.id.Linchart);
        txtWdfenzhong = view.findViewById(R.id.txt_wdfenzhong);
    }
}
