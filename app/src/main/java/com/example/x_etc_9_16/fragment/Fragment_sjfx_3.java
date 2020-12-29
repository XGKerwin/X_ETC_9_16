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

public class Fragment_sjfx_3 extends Fragment {

    private int t2, t3, t5;
    private TextView txtTitle;
    private HorizontalBarChart barChart;
    private List<BarEntry> entryList;
    private List<Integer> colors;
    private float f1,f2,f3;

    public Fragment_sjfx_3(int t2, int t3, int t5) {
        this.t2 = t2;
        this.t3 = t3;
        this.t5 = t5;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_sjfx_3, null);
        initView(view);
        txtTitle.setText("违章车辆的违章次数占比分布图统计");
        getData();

        return view;
    }

    private void getData() {

        int sun = t2+t3+t5;
        f1 = (float) t2/(float)sun;
        f2 = (float) t3/(float)sun;
        f3 = (float) t5/(float)sun;

        Log.i("vvvvvvvvvvvvvvv", "getData: "+f1);
        Log.i("vvvvvvvvvvvvvvv", "getData: "+f2);
        Log.i("vvvvvvvvvvvvvvv", "getData: "+f3);

        float f11 = f1*100;
        float f22 = f2*100;
        float f33 = f3*100;

        if (entryList == null){
            entryList = new ArrayList<>();
            colors = new ArrayList<>();
        } else {
            entryList.clear();
            colors.clear();
        }
        entryList.add(new BarEntry(0, f11));
        entryList.add(new BarEntry(1, f22));
        entryList.add(new BarEntry(2, f33));

        BarDataSet dataSet = new BarDataSet(entryList, "");
        dataSet.setColors(new int[]{Color.parseColor("#7DC53E"),Color.parseColor("#4068AE"),Color.parseColor("#A8000A")});
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.3f);
        data.setValueFormatter(new PercentFormatter());
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

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"1-2条违章","3-5条违章","5条以上违章"}));
        xAxis.setTextSize(20f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(3);

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
