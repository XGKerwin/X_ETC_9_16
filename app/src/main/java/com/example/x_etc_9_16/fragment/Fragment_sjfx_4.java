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

public class Fragment_sjfx_4 extends Fragment {

    private int y49, y48, y47, y46, y45;
    private int n49, n48, n47, n46, n45;
    private TextView tvTitle;
    private BarChart barchart;

    public Fragment_sjfx_4(int y49, int y48, int y47, int y46, int y45, int n49, int n48, int n47, int n46, int n45) {
        this.y49 = y49;
        this.y48 = y48;
        this.y47 = y47;
        this.y46 = y46;
        this.y45 = y45;
        this.n45 = n45;
        this.n46 = n46;
        this.n47 = n47;
        this.n48 = n48;
        this.n49 = n49;
    }

    private List<BarEntry> barEntries;
    private List<String> strings;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_4, null);
        initView(view);
        tvTitle.setText("年龄群体车辆违章的占比统计");

        getData();


        return view;
    }

    float y90,y80,y70,y60,y50;
    float n90,n80,n70,n60,n50;

    private void getData() {
        if (barEntries == null){
            barEntries = new ArrayList<>();
            strings = new ArrayList<>();
        }else {
            barEntries.clear();
            strings.clear();
        }
        getda();
        barEntries.add(new BarEntry(0, new float[]{n90,y90}));
        barEntries.add(new BarEntry(1, new float[]{n80,y80}));
        barEntries.add(new BarEntry(2, new float[]{n70,y70}));
        barEntries.add(new BarEntry(3, new float[]{n60,y60}));
        barEntries.add(new BarEntry(4, new float[]{n50,y50}));
        BarDataSet dataSet = new BarDataSet(barEntries,"");
        dataSet.setColors(new int[]{Color.parseColor("#6A9800"),Color.parseColor("#EA7208")});
        dataSet.setStackLabels(new String[]{"有违章","无违章"});
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("00.0");
                return format.format(value * 100)+"%";
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

        strings.add("90后");
        strings.add("80后");
        strings.add("70后");
        strings.add("60后");
        strings.add("50后");
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(5);
        xAxis.setTextSize(10f);


        YAxis yAxis = barchart.getAxisRight();
        yAxis.setStartAtZero(true);
        yAxis.setEnabled(false);

        YAxis yAxis1 = barchart.getAxisLeft();
        yAxis1.setTextSize(20f);
        yAxis1.setStartAtZero(true);
        yAxis1.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                DecimalFormat format = new DecimalFormat("0");
                return format.format(value * 1000);
            }
        });
        yAxis1.setLabelCount(7);

        barchart.getDescription().setEnabled(false);
        barchart.setTouchEnabled(false);
        Log.i("tttttt4", "getData: ");

    }

    private void getda() {

        float yessun,nosun;
        yessun = y49+y48+y47+y46+y45;
        y90 = y49/yessun*1;
        y80 = y48/yessun*1;
        y70 = y47/yessun*1;
        y60 = y46/yessun*1;
        y50 = y45/yessun*1;

        nosun = n49+n48+n47+n46+n45;
        n90 = n49/nosun*1;
        n80 = n48/nosun*1;
        n70 = n47/nosun*1;
        n60 = n46/nosun*1;
        n50 = n45/nosun*1;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        barchart = view.findViewById(R.id.barchart);
    }
}
