package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_9_16.bean.GJCX;
import com.example.x_etc_9_16.R;

import java.util.List;

public class X_diaad_apter extends BaseAdapter {
    private List<GJCX> gjcxList;


    public X_diaad_apter(List<GJCX> gjcxList) {
        this.gjcxList = gjcxList;
    }

    @Override
    public int getCount() {
        return gjcxList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_dia, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        GJCX gjcx = gjcxList.get(position);
        holder.number.setText(position+1+"");
        holder.gongjiaoche.setText(gjcx.getBus()+"");
        holder.renshu.setText(gjcx.getPerson()+"");

        return convertView;
    }

    class ViewHolder {
        private TextView number;
        private TextView gongjiaoche;
        private TextView renshu;

        public ViewHolder(View view) {
            number = view.findViewById(R.id.number);
            gongjiaoche = view.findViewById(R.id.gongjiaoche);
            renshu = view.findViewById(R.id.renshu);
        }
    }
}
