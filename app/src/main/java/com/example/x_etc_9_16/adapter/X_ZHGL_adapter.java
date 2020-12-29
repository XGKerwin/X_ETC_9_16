package com.example.x_etc_9_16.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.ZHGL;

import java.util.List;

public class X_ZHGL_adapter extends BaseAdapter {

    private List<ZHGL> zhglList;
    private int yuzhi;
    private MyOnclick myOnclick;

    public interface MyOnclick{

        void setOn(int position, int i, boolean isChecked, int balance);
    }

    public void setMyOnclick(MyOnclick myOnclick){
        this.myOnclick = myOnclick;
    }

    public X_ZHGL_adapter(List<ZHGL> zhglList, int yuzhi) {
        this.zhglList = zhglList;
        this.yuzhi = yuzhi;
    }

    @Override
    public int getCount() {
        return zhglList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_zhgl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ZHGL zhgl = zhglList.get(position);
        holder.txtBianhao.setText(position+1+"");
        holder.txtCph.setText(zhgl.getPlate());
        holder.txtChezhu.setText(zhgl.getOwner());
        holder.txtYue.setText("余额："+zhgl.getBalance());
        switch (zhgl.getBrand()){
            case "宝马":
                holder.img.setImageResource(R.drawable.bmw);
                break;
            case "奔驰":
                holder.img.setImageResource(R.drawable.benci);
                break;
            case "中华":
                holder.img.setImageResource(R.drawable.zhonghua);
                break;
            case "奥迪":
                holder.img.setImageResource(R.drawable.audi);
                break;
        }
        if (yuzhi<zhgl.getBalance()){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }else {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FDCB01"));
        }

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Log.i("log_checkbox", "onCheckedChanged: "+isChecked);

                myOnclick.setOn(position,1,isChecked,zhgl.getBalance());

            }
        });

        holder.btnChongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnclick.setOn(position,2, false,zhgl.getBalance());
            }
        });

        return convertView;
    }


    class ViewHolder {
        private TextView txtBianhao;
        private ImageView img;
        private TextView txtCph;
        private TextView txtChezhu;
        private TextView txtYue;
        private CheckBox checkbox;
        private Button btnChongzhi;
        private LinearLayout linearLayout;

        public ViewHolder(View view) {
            txtBianhao = view.findViewById(R.id.txt_bianhao);
            img = view.findViewById(R.id.img);
            txtCph = view.findViewById(R.id.txt_cph);
            txtChezhu = view.findViewById(R.id.txt_chezhu);
            txtYue = view.findViewById(R.id.txt_yue);
            checkbox = view.findViewById(R.id.checkbox);
            btnChongzhi = view.findViewById(R.id.btn_chongzhi);
            linearLayout = view.findViewById(R.id.lin);
        }
    }
}
