package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.HLD;

import java.util.List;

public class X_hld_adapter extends BaseAdapter {
    private List<HLD> hldList;

    private MyHLD myHLD;

    public interface MyHLD{

        void seton(int position, int i, boolean isChecked);
    }
    public void setHLD(MyHLD myHLD){
        this.myHLD = myHLD;
    }

    public X_hld_adapter(List<HLD> hldList) {
        this.hldList = hldList;
    }

    @Override
    public int getCount() {
        return hldList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hld, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HLD hld = hldList.get(position);
        holder.lukou.setText(hld.getNumber()+"");
        holder.hong.setText(hld.getRed()+"");
        holder.huang.setText(hld.getYellow()+"");
        holder.lv.setText(hld.getGreen()+"");

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myHLD.seton(position,1,isChecked);
            }
        });

        holder.btnShezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHLD.seton(position,2, false);
            }
        });

        return convertView;
    }

    class ViewHolder {
        private TextView lukou;
        private TextView hong;
        private TextView huang;
        private TextView lv;
        private Button btnShezhi;
        private CheckBox checkBox;

        public ViewHolder(View view) {
            checkBox = view.findViewById(R.id.che);
            lukou = view.findViewById(R.id.lukou);
            hong = view.findViewById(R.id.hong);
            huang = view.findViewById(R.id.huang);
            lv = view.findViewById(R.id.lv);
            btnShezhi = view.findViewById(R.id.btn_shezhi);
        }
    }
}
