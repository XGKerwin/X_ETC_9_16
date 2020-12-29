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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragment_sjfx_6 extends Fragment {
    private int t01 = 0, t23 = 0, t45 = 0, t67 = 0, t89 = 0, t1011 = 0, t1213 = 0, t1415 = 0, t1617 = 0, t1819 = 0, t2021 = 0, t2223 = 0;
    private TextView tvTitle;
    private BarChart barchart;
    private int size;

    public Fragment_sjfx_6(int t01, int t23, int t45, int t67, int t89, int t1011, int t1213, int t1415, int t1617, int t1819, int t2021, int t2223, int size) {
        this.t01 = t01;
        this.t23 = t23;
        this.t45 = t45;
        this.t67 = t67;
        this.t89 = t89;
        this.t1011 = t1011;
        this.t1213 = t1213;
        this.t1415 = t1415;
        this.t1617 = t1617;
        this.t1819 = t1819;
        this.t2021 = t2021;
        this.t2223 = t2223;
        this.size = size;
    }

    private List<BarEntry> barEntries;
    private List<String> strings;
    private List<Integer> colors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_4, null);
        initView(view);
        Log.i("getbaifen", "getbaifen: "+t1415);
        tvTitle.setText("每日时段内车辆违章的占比统计");
        getdata();

        return view;
    }

    private void getdata() {
        if (barEntries == null){
            barEntries = new ArrayList<>();
            strings = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            barEntries.clear();
            strings.clear();
            colors.clear();
        }
        getbaifen();
        barEntries.add(new BarEntry(0, x01));
        barEntries.add(new BarEntry(1, x23));
        barEntries.add(new BarEntry(2, x45));
        barEntries.add(new BarEntry(3, x67));
        barEntries.add(new BarEntry(4, x89));
        barEntries.add(new BarEntry(5, x1011));
        barEntries.add(new BarEntry(6, x1213));
        barEntries.add(new BarEntry(7, x1415));
        barEntries.add(new BarEntry(8, x1617));
        barEntries.add(new BarEntry(9, x1819));
        barEntries.add(new BarEntry(10, x2021));
        barEntries.add(new BarEntry(11, x2223));
        BarDataSet dataSet = new BarDataSet(barEntries,"");
        colors.add(Color.parseColor("#416385"));
        colors.add(Color.parseColor("#513E43"));
        colors.add(Color.parseColor("#05048B"));
        colors.add(Color.parseColor("#505C2B"));
        colors.add(Color.parseColor("#B7ADE2"));
        colors.add(Color.parseColor("#7B953E"));
        colors.add(Color.parseColor("#6A2A27"));
        colors.add(Color.parseColor("#FFBC0F"));
        colors.add(Color.parseColor("#325C93"));
        colors.add(Color.parseColor("#E86A0D"));
        colors.add(Color.parseColor("#963478"));
        colors.add(Color.parseColor("#6D232B"));
        dataSet.setColors(colors);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.00");
                return format.format(value * 1) + "%";
            }
        });
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.4f);
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.RED);
        barchart.setData(data);
        Legend legend = barchart.getLegend();
        legend.setTextSize(25);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setFormSize(30);
        legend.setDrawInside(true);

        YAxis yAxis = barchart.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);
        YAxis yAxis1 = barchart.getAxisLeft();
        yAxis1.setTextSize(20f);
        yAxis1.setStartAtZero(true);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat format = new DecimalFormat("0.00");
                return format.format(value * 1) + "%";
            }
        });
        yAxis1.setLabelCount(7);

        strings.add("0:00:01-2:00:00");
        strings.add("2:00:01-4:00:00");
        strings.add("4:00:01-6:00:00");
        strings.add("6:00:01-8:00:00");
        strings.add("8:00:01-10:00:00");
        strings.add("10:00:01-12:00:00");
        strings.add("12:00:01-14:00:00");
        strings.add("14:00:01-16:00:00");
        strings.add("16:00:01-18:00:00");
        strings.add("18:00:01-20:00:00");
        strings.add("20:00:01-22:00:00");
        strings.add("22:00:01-24:00:00");
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setDrawGridLines(false);
        xAxis.setLabelRotationAngle(-50);
        xAxis.setLabelCount(strings.size());
        xAxis.setTextSize(20f);

        barchart.getDescription().setEnabled(false);
        barchart.getLegend().setEnabled(false);
        barchart.setTouchEnabled(false);

    }
    private int x01 = 0, x23 = 0, x45 = 0, x67 = 0, x89 = 0, x1011 = 0, x1213 = 0, x1415 = 0, x1617 = 0, x1819 = 0, x2021 = 0, x2223 = 0;
    private void getbaifen() {
        x01 = t01*100/size;
        x23 = t23*100/size;
        x45 = t45*100/size;
        x67 = t67*100/size;
        x89 = t89*100/size;
        x1011 = t1011*100/size;
        x1213 = t1213*100/size;
        x1415 = t1415*100/size;
        x1617 = t1617*100/size;
        x1819 = t1819*100/size;
        x2021 = t2021*100/size;
        x2223 = t2223*100/size;
        Log.i("getbaifen", "getbaifen: "+x1415);
        Log.i("getbaifen", "getbaifen: "+t1415);
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        barchart = view.findViewById(R.id.barchart);
    }
}
