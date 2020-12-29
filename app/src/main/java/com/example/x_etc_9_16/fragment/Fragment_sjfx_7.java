package com.example.x_etc_9_16.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fragment_sjfx_7 extends Fragment {

    private TextView txtTitle;
    private HorizontalBarChart barChart;
    private int sum;


    private List<BarEntry> barEntries;
    private List<Integer> colors;
    private List<String> strings;
    private List<Map.Entry<String,Integer>> ma;

    public Fragment_sjfx_7(List<Map.Entry<String, Integer>> mas, int size) {
        this.ma = mas;
        this.sum = size;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_3, null);
        initView(view);
        txtTitle.setText("排名前十位的交通违法行为的占比统计");
        getdata();

        return view;
    }

    private void getdata() {
        Log.i("ccccccccccccc", "getdata: "+sum);

        if (barEntries == null){
            barEntries = new ArrayList<>();
            strings = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            barEntries.clear();
            strings.clear();
            colors.clear();
        }

        for (int i=0;i<ma.size();i++){
            float a1 = ma.get(i).getValue();
            float aa = a1/sum*100;
            barEntries.add(new BarEntry(i,aa));
        }

        colors.add(Color.parseColor("#98B1D1"));
        colors.add(Color.parseColor("#BDB3CE"));
        colors.add(Color.parseColor("#F8AB75"));
        colors.add(Color.parseColor("#B7CF83"));
        colors.add(Color.parseColor("#FCC009"));
        colors.add(Color.parseColor("#5E4A7F"));
        colors.add(Color.parseColor("#DE6F12"));
        colors.add(Color.parseColor("#79923C"));
        colors.add(Color.parseColor("#4D80BA"));
        colors.add(Color.parseColor("#BD0502"));


        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value * 1) + "%";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.7f);
        data.setValueTextColor(Color.RED);
        data.setValueTextSize(30);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextSize(25f);
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        yAxis.setLabelCount(10);
        yAxis.setTextSize(10f);
        yAxis.setAxisMaximum(100);
        YAxis yAxis1 = barChart.getAxisRight();
        yAxis1.setStartAtZero(true);
        yAxis1.setValueFormatter(new PercentFormatter());
        yAxis1.setLabelCount(10);
        yAxis1.setAxisMaximum(100);
        yAxis1.setTextSize(10f);

        for (int i=0;i<ma.size();i++){
            strings.add(ma.get(i).getKey());
        }

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setTextSize(20f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);

        barChart.setData(data);
        barChart.setTouchEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();
        barChart.getDescription().setEnabled(false);

    }

    private void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        barChart = view.findViewById(R.id.bar_chart);
    }
}
