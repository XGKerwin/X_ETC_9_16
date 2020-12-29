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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Fragment_sjfx_2 extends Fragment {

    private int dayu1, dengyu1;
    private TextView txtTitle;
    private PieChart piechart;
    private List<Integer> colors;
    private List<PieEntry> pieEntries;

    public Fragment_sjfx_2(int f1, int f2) {
        this.dengyu1 = f1;
        this.dayu1 = f2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_1, null);
        initView(view);
        txtTitle.setText("有无“重复违章记录的车辆”的占比统计");

        getData();

        return view;
    }

    private void getData() {
        if (pieEntries == null){
            pieEntries = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            pieEntries.clear();
            colors.clear();
        }
        pieEntries.add(new PieEntry(dengyu1,"无重复违章"));
        pieEntries.add(new PieEntry(dayu1,"有重复违章"));
        colors.add(Color.parseColor("#A94643"));
        colors.add(Color.parseColor("#4572A7"));

        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(colors);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.7f);
        dataSet.setValueLinePart2Length(0.1f);
        dataSet.setValueLinePart1OffsetPercentage(100);
        dataSet.setValueLineColor(Color.BLACK);

        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                DecimalFormat format = new DecimalFormat("0.0");
                return format.format(value)+"%";
            }
        });

        dataSet.setSliceSpace(10f);
        dataSet.setValueTextSize(20);
        PieData data = new PieData(dataSet);
        Legend legend = piechart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(25);
        legend.setFormSize(25);
        piechart.setData(data);
        piechart.getDescription().setEnabled(false);
        piechart.setDrawHoleEnabled(false);
        piechart.setEntryLabelTextSize(20);
        piechart.setEntryLabelColor(Color.BLACK);
        piechart.setUsePercentValues(true);
        piechart.setTouchEnabled(false);


    }

    private void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        piechart = view.findViewById(R.id.piechart);
    }
}
