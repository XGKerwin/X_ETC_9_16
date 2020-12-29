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

public class Fragment_sjfx_1 extends Fragment {

    private int yes, no;
    private TextView txtTitle;
    private PieChart piechart;
    private List<PieEntry> pieEntries;
    private List<Integer> colors;

    public Fragment_sjfx_1(int no, int yes) {
        this.yes = yes;
        this.no = no;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_1, null);
        Log.d("bbbbbb", "onCreateView: yes " + yes);
        Log.i("bbbbbb", "onCreateView: no  " + no);
        initView(view);
        txtTitle.setText("平台上有违章车辆和没违章车辆的占比统计");
        getdata();

        return view;
    }

    private float you,meiyou;
    private void getdata() {

        int sum = yes + no;
        meiyou = (float) no/(float) sum;
        you = 1 - meiyou;

        float s = Math.round(meiyou*1000);
        float s1 = Math.round(you*1000);
        float yes = s1/1000;
        float no = s/1000;

        if (pieEntries == null){
            pieEntries = new ArrayList<>();
            colors = new ArrayList<>();
        }else {
            pieEntries.clear();
            colors.clear();
        }
        pieEntries.add(new PieEntry(yes,"有违章"));
        pieEntries.add(new PieEntry(no,"无违章"));
        colors.add(Color.parseColor("#4572A6"));
        colors.add(Color.parseColor("#A94643"));
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(colors);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(1f);
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

        dataSet.setSliceSpace(5f);
        dataSet.setValueTextSize(25);
        PieData data = new PieData(dataSet);
        Legend legend = piechart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setTextSize(25);
        legend.setFormSize(25);
        legend.getFormLineWidth();
        piechart.setData(data);
        piechart.getDescription().setEnabled(false);
        piechart.setDrawHoleEnabled(false);
        piechart.setEntryLabelTextSize(15);
        piechart.setEntryLabelColor(Color.BLACK);
        piechart.setUsePercentValues(true);
        piechart.setTouchEnabled(false);

    }

    private void initView(View view) {
        txtTitle = view.findViewById(R.id.txt_title);
        piechart = view.findViewById(R.id.piechart);
    }
}
