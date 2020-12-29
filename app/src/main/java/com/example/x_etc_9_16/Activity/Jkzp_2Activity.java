package com.example.x_etc_9_16.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_9_16.R;

public class Jkzp_2Activity extends AppCompatActivity {

    private String string;
    private ImageView caidan;
    private TextView title;
    private TextView biaotiCzjl;
    private TextView biaotiPlcz;
    private ImageView imgZhu;
    private Button btnFanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jkzp_2);
        initView();
        title.setText("监控抓拍");
        string = getIntent().getStringExtra("1");
        btn();
        switch (string){
            case "1":
                imgZhu.setImageResource(R.drawable.weizhang1);
                break;
            case "2":
                imgZhu.setImageResource(R.drawable.weizhang2);
                break;
            case "3":
                imgZhu.setImageResource(R.drawable.weizhang3);
                break;
            case "4":
                imgZhu.setImageResource(R.drawable.weizhang4);
                break;
            case "5":
                imgZhu.setImageResource(R.drawable.weizhang5);
                break;
            case "6":
                imgZhu.setImageResource(R.drawable.weizhang6);
                break;
            case "7":
                imgZhu.setImageResource(R.drawable.weizhang7);
                break;
            case "8":
                imgZhu.setImageResource(R.drawable.weizhang8);
                break;
            case "9":
                imgZhu.setImageResource(R.drawable.weizhang9);
                break;
            case "10":
                imgZhu.setImageResource(R.drawable.weizhang10);
                break;
        }

    }

    private void btn() {
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        biaotiCzjl = findViewById(R.id.biaoti_czjl);
        biaotiPlcz = findViewById(R.id.biaoti_plcz);
        imgZhu = findViewById(R.id.img_zhu);
        btnFanhui = findViewById(R.id.btn_fanhui);
    }
}