package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.adapter.X_CZJL_adapter;
import com.example.x_etc_9_16.bean.CZJL;

import org.litepal.LitePal;

import java.util.List;

public class Fragment_grzx_czjl extends Fragment {

    private ImageView imgTouxiang;
    private TextView txtSum;
    private ListView listview;
    private List<CZJL> czjls;
    private X_CZJL_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_czjl, null);
        initView(view);
        czjls = LitePal.findAll(CZJL.class);
        int sum = 0;
        for (int i=0;i<czjls.size();i++){
            CZJL czjl = czjls.get(i);
            sum += czjl.getChongzhi();
        }
        txtSum.setText(sum+".00");

        adapter = new X_CZJL_adapter(czjls);
        listview.setAdapter(adapter);


        return view;
    }

    private void initView(View view) {
        imgTouxiang = view.findViewById(R.id.img_touxiang);
        txtSum = view.findViewById(R.id.txt_sum);
        listview = view.findViewById(R.id.listview);
    }
}
