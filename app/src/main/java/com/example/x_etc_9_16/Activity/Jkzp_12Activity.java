package com.example.x_etc_9_16.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_9_16.R;

public class Jkzp_12Activity extends AppCompatActivity {


    private ImageView caidan;
    private TextView title;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private Button btnFanhui;
    private String daolu;
    private String s1,s2,s3,s4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jkzp_12);
        initView();
        title.setText("监控抓拍");
        daolu = getIntent().getStringExtra("1");
        Log.d("ccccccc", "onCreate: "+daolu);

        switch (daolu){
            case "学院路":
                img1.setImageResource(R.drawable.weizhang1);
                img2.setImageResource(R.drawable.weizhang2);
                img3.setImageResource(R.drawable.weizhang3);
                img4.setImageResource(R.drawable.weizhang4);
                s1 = "1";
                s2 = "2";
                s3 = "3";
                s4 = "4";
                break;
            case "求实路":
                img1.setImageResource(R.drawable.weizhang5);
                img2.setImageResource(R.drawable.weizhang6);
                img3.setImageResource(R.drawable.weizhang7);
                img4.setImageResource(R.drawable.weizhang8);
                s1 = "5";
                s2 = "6";
                s3 = "7";
                s4 = "8";
                break;
            case "红石路":
                img1.setImageResource(R.drawable.weizhang9);
                img2.setImageResource(R.drawable.weizhang10);
                img3.setImageResource(R.drawable.weizhang1);
                img4.setImageResource(R.drawable.weizhang2);
                s1 = "5";
                s2 = "6";
                s3 = "7";
                s4 = "8";
                break;
            case "天桥路":
                img1.setImageResource(R.drawable.weizhang3);
                img2.setImageResource(R.drawable.weizhang4);
                img3.setImageResource(R.drawable.weizhang5);
                img4.setImageResource(R.drawable.weizhang6);
                s1 = "3";
                s2 = "4";
                s3 = "5";
                s4 = "6";
                break;
            case "南湖路":
                img1.setImageResource(R.drawable.weizhang7);
                img2.setImageResource(R.drawable.weizhang8);
                img3.setImageResource(R.drawable.weizhang9);
                img4.setImageResource(R.drawable.weizhang10);
                s1 = "7";
                s2 = "8";
                s3 = "9";
                s4 = "10";
                break;
            case "复兴路":
                img1.setImageResource(R.drawable.weizhang9);
                img2.setImageResource(R.drawable.weizhang8);
                img3.setImageResource(R.drawable.weizhang7);
                img4.setImageResource(R.drawable.weizhang6);
                s1 = "9";
                s2 = "8";
                s3 = "7";
                s4 = "6";
                break;
            case "春华路":
                img1.setImageResource(R.drawable.weizhang5);
                img2.setImageResource(R.drawable.weizhang4);
                img3.setImageResource(R.drawable.weizhang3);
                img4.setImageResource(R.drawable.weizhang2);
                s1 = "5";
                s2 = "4";
                s3 = "3";
                s4 = "2";
        }

        btn();

    }

    private void btn() {
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jkzp_12Activity.this,Jkzp_2Activity.class)
                        .putExtra("1",s1);
                startActivity(intent);

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jkzp_12Activity.this,Jkzp_2Activity.class)
                        .putExtra("1",s2);
                startActivity(intent);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jkzp_12Activity.this,Jkzp_2Activity.class)
                        .putExtra("1",s3);
                startActivity(intent);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jkzp_12Activity.this,Jkzp_2Activity.class)
                        .putExtra("1",s4);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        img1 = findViewById(R.id.img_1);
        img2 = findViewById(R.id.img_2);
        img3 = findViewById(R.id.img_3);
        img4 = findViewById(R.id.img_4);
        btnFanhui = findViewById(R.id.btn_fanhui);
    }
}