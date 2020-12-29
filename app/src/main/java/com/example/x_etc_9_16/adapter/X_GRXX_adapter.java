package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_9_16.R;

import java.util.List;

public class X_GRXX_adapter extends BaseAdapter {
    private List<String> strings;

    public X_GRXX_adapter(List<String> strings) {
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grxx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = strings.get(position);

        holder.txtCp.setText(s);
        switch (s){
            case "鲁A10001":
                holder.chebiao.setImageResource(R.drawable.benci);
                holder.txtCp.setText(s);
                break;
            case "鲁A10002":
                holder.chebiao.setImageResource(R.drawable.bmw);
                holder.txtCp.setText(s);
                break;
            case "鲁A10003":
                holder.chebiao.setImageResource(R.drawable.zhonghua);
                holder.txtCp.setText(s);
                break;
            case "鲁A10004":
                holder.chebiao.setImageResource(R.drawable.audi);
                holder.txtCp.setText(s);
                break;
        }

        return convertView;
    }


    class ViewHolder {
        private ImageView chebiao;
        private TextView txtCp;

        public ViewHolder(View view) {
            chebiao = view.findViewById(R.id.chebiao);
            txtCp = view.findViewById(R.id.txt_cp);
        }
    }
}
