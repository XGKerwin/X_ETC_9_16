package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.CZJL;

import java.util.List;

public class X_CZJL_adapter extends BaseAdapter {
    private List<CZJL> czjls;


    public X_CZJL_adapter(List<CZJL> czjls) {
        this.czjls = czjls;
    }

    @Override
    public int getCount() {
        return czjls.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_czjl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        CZJL czjl = czjls.get(position);
        holder.time1.setText(czjl.getTime1());
        holder.czr.setText("充值人："+czjl.getCzr());
        holder.cph.setText("车牌号："+czjl.getCph());
        holder.txtChongzhi.setText("充值："+czjl.getChongzhi()+"");
        holder.txtYue.setText("余额："+czjl.getYue()+"");
        holder.txtCztime.setText(czjl.getChongzhitime());

        return convertView;
    }

    class ViewHolder {
        private TextView time1;
        private TextView czr;
        private TextView cph;
        private TextView txtChongzhi;
        private TextView txtYue;
        private TextView txtCztime;

        public ViewHolder(View view) {
            time1 = view.findViewById(R.id.time1);
            czr = view.findViewById(R.id.czr);
            cph = view.findViewById(R.id.cph);
            txtChongzhi = view.findViewById(R.id.txt_chongzhi);
            txtYue = view.findViewById(R.id.txt_yue);
            txtCztime = view.findViewById(R.id.txt_cztime);
        }
    }
}
