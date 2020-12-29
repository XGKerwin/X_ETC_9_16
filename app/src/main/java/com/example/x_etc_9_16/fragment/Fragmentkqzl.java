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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fragmentkqzl extends Fragment {
    private List<Wendu> wenduList;
    private BarChart barchart;
    private TextView txt1fenzhong;

    public Fragmentkqzl(List<Wendu> wenduList) {
        this.wenduList = wenduList;
    }

    private List<BarEntry> barEntries;
    private List<Integer> integers;
    private List<String> strings;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.d("cccccccccccccc", "handleMessage: "+wenduList.size());
            sethuatu();
            return false;
        }
    });


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_kqzl, null);
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

    private void sethuatu() {
        if (wenduList.size() == 0)
            return;
        if (barEntries == null) {
            barEntries = new ArrayList<>();
            integers = new ArrayList<>();
            strings = new ArrayList<>();
        } else {
            barEntries.clear();
            integers.clear();
            strings.clear();
        }
        for (int i=0;i<wenduList.size();i++){
            Wendu wendu = wenduList.get(i);
            barEntries.add(new BarEntry(i,wendu.getPm25()));
            integers.add(wendu.getPm25());
            strings.add(((i+1)*3)+"");
        }
        BarDataSet barDataSet = new BarDataSet(barEntries,"");
        Collections.sort(integers, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        txt1fenzhong.setText("过去1分钟空气质量最差值："+integers.get(integers.size()-1));
        barDataSet.setColor(Color.GRAY);
        BarData data = new BarData(barDataSet);
        barchart.getDescription().setText("(S)");
        barchart.getLegend().setEnabled(false);
        YAxis yAxis = barchart.getAxisLeft();
        yAxis.setDrawAxisLine(false);
        yAxis.setAxisMinimum(0);
        yAxis.setAxisMaximum(300);
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setLabelCount(strings.size());
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        barchart.getAxisRight().setEnabled(false);
        barchart.setData(data);
        barchart.setTouchEnabled(false);
        barchart.invalidate();
    }

    private void initView(View view) {
        barchart = view.findViewById(R.id.barchart);
        txt1fenzhong = view.findViewById(R.id.txt_1fenzhong);
    }
}
