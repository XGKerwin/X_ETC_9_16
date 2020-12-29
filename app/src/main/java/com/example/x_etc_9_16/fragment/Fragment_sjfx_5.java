package com.example.x_etc_9_16.fragment;

import android.graphics.Color;
import android.os.Bundle;
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

public class Fragment_sjfx_5 extends Fragment {

    private int nanyes, nvyes, nanno, nvno;
    private TextView tvTitle;
    private BarChart barchart;

    public Fragment_sjfx_5(int nanyes, int nanno, int nvyes, int nvno) {
        this.nanyes = nanyes;
        this.nanno = nanno;
        this.nvno = nvno;
        this.nvyes = nvno;
    }

    private List<BarEntry> barEntries;
    private List<String> strings;
    private float yesnan,yesnv,nonan,nonv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_4, null);
        initView(view);

        tvTitle.setText("平台上男性和女性有无车辆违章的占比统计");

        getdata();

        return view;
    }

    private void getdata() {
        if (barEntries == null) {
            barEntries = new ArrayList<>();
            strings = new ArrayList<>();
        } else {
            barEntries.clear();
            strings.clear();
        }
        getda();
        barEntries.add(new BarEntry(0, new float[]{nvno, nvyes}));
        barEntries.add(new BarEntry(1, new float[]{nanno, nanyes}));
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        dataSet.setColors(new int[]{Color.parseColor("#6A9800"), Color.parseColor("#EA7208")});
        dataSet.setStackLabels(new String[]{"有违章", "无违章"});
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("00.0");
                return format.format(value * 0.1) + "%";
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
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setFormSize(30);
        legend.setDrawInside(true);

        strings.add("女性");
        strings.add("男性");
        XAxis xAxis = barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(strings));
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(2);
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
                return format.format(value * 10);
            }
        });
        yAxis1.setLabelCount(7);

        barchart.getDescription().setEnabled(false);
        barchart.setTouchEnabled(false);

    }

    private void getda() {
        int sun = nanyes + nanno + nvyes + nvno;
        yesnan = nanyes/sun;
        yesnv = nvyes/sun;
        nonan = nanno/sun;
        nonv = nvno/sun;
    }

    private void initView(View view) {
        tvTitle = view.findViewById(R.id.tv_title);
        barchart = view.findViewById(R.id.barchart);
    }
}
