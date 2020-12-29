package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.WZBH;
import com.example.x_etc_9_16.bean.WZXX;
import com.example.x_etc_9_16.bean.WZnumber;

import java.util.List;

public class X_clwz_adapter1 extends BaseAdapter {
    private List<WZBH> wzbhList;
    private List<WZnumber> numberlist;
    private List<WZXX> wzxxList;
    private MyCXJG myCXJG;

    public interface MyCXJG{

        void setcxjg(int position, String i);
    }

    public void setCXJG(MyCXJG myCXJG){
        this.myCXJG = myCXJG;
    }

    public X_clwz_adapter1(List<WZnumber> numberlist, List<WZBH> wzbhList, List<WZXX> wzxxList) {
        this.wzbhList = wzbhList;
        this.wzxxList = wzxxList;
        this.numberlist = numberlist;
    }

    @Override
    public int getCount() {
        if (wzbhList.size()==0)return 0;
        return wzbhList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cxjg_1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        WZBH wzbh = wzbhList.get(position);
        holder.cph.setText(wzbh.getCph());
        holder.txtWcl.setText("未处理违章    "+wzbh.getWcl()+" 次");
        holder.txtKoufen.setText("扣 "+wzbh.getKoufen()+" 分         罚款"+wzbh.getFakuan()+" 元");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCXJG.setcxjg(position,"btn");
            }
        });

        holder.img_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCXJG.setcxjg(position,"jian");
            }
        });

        return convertView;
    }


    class ViewHolder {
        private TextView cph;
        private TextView txtWcl;
        private TextView txtKoufen;
        private LinearLayout linearLayout;
        private ImageView img_jian;

        public ViewHolder(View view) {
            img_jian = view.findViewById(R.id.img_jian);
            linearLayout = view.findViewById(R.id.lin2);
            cph = view.findViewById(R.id.cph);
            txtWcl = view.findViewById(R.id.txt_wcl);
            txtKoufen = view.findViewById(R.id.txt_koufen);
        }
    }
}
