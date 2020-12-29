package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_9_16.R;

public class Fragment_grzx extends Fragment {

    private TextView txtGrxx;
    private TextView txtCzjl;
    private TextView txtYzsz;
    private FragmentTransaction fragmentTransaction;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_grzx, null);
        initView(view);
        title.setText("个人中心");

        btn();

        txtGrxx.setBackgroundResource(R.drawable.txt_xia);
        txtCzjl.setBackgroundResource(R.drawable.txt_wu);
        txtYzsz.setBackgroundResource(R.drawable.txt_wu);
        Fragment1(new Fragment_grzx_grxx());

        return view;
    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment16,fragment).commit();
    }

    private void btn() {
        txtGrxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtGrxx.setBackgroundResource(R.drawable.txt_xia);
                txtCzjl.setBackgroundResource(R.drawable.txt_wu);
                txtYzsz.setBackgroundResource(R.drawable.txt_wu);
                Fragment1(new Fragment_grzx_grxx());
            }
        });

        txtCzjl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtGrxx.setBackgroundResource(R.drawable.txt_wu);
                txtCzjl.setBackgroundResource(R.drawable.txt_xia);
                txtYzsz.setBackgroundResource(R.drawable.txt_wu);
                Fragment1(new Fragment_grzx_czjl());
            }
        });

        txtYzsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtGrxx.setBackgroundResource(R.drawable.txt_wu);
                txtCzjl.setBackgroundResource(R.drawable.txt_wu);
                txtYzsz.setBackgroundResource(R.drawable.txt_xia);
                Fragment1(new Fragment_yzsz());

            }
        });

    }

    private void initView(View view) {
        txtGrxx = view.findViewById(R.id.txt_grxx);
        txtCzjl = view.findViewById(R.id.txt_czjl);
        txtYzsz = view.findViewById(R.id.txt_yzsz);
        title = getActivity().findViewById(R.id.title);
    }

}
