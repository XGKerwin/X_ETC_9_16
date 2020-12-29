package com.example.x_etc_9_16.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.net.OkHttpLo;
import com.example.x_etc_9_16.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Fragment_yzsz extends Fragment {

    private TextView txtDangqian;
    private EditText edYuzhi;
    private Button btnShezhi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_yzsz, null);
        initView(view);

        getOkHttp();

        getEdit();
        btn();

        return view;
    }

    private void getEdit() {
        edYuzhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("0")){
                    edYuzhi.setText("");
                    Toast.makeText(getContext(),"不能输入0",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btn() {
        btnShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edYuzhi.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getContext(),"请输入设置阈值",Toast.LENGTH_SHORT).show();
                }else {
                    setOkhttp(s);
                }
            }
        });

    }

    private void setOkhttp(String s) {
        /**
         * {"UserName":"user1","threshold":"200"}
         */
        new OkHttpTo()
                .setUrl("set_car_threshold")
                .setJsonObject("UserName","user1")
                .setJsonObject("threshold",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Toast.makeText(getContext(),"设置成功",Toast.LENGTH_SHORT).show();
                        edYuzhi.setText("");
                        getOkHttp();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        /**
         * {"UserName":"user1"}
         *     "ROWS_DETAIL": [
         *         {
         *             "threshold": 200
         *         }
         */

        new OkHttpTo()
                .setUrl("get_car_threshold")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                        txtDangqian.setText("当前1-4号小车账户余额告阈值为"+jsonObject1.optString("threshold")+"元");
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        txtDangqian = view.findViewById(R.id.txt_dangqian);
        edYuzhi = view.findViewById(R.id.ed_yuzhi);
        btnShezhi = view.findViewById(R.id.btn_shezhi);
    }
}
