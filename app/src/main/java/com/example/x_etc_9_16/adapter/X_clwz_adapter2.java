package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.WZxx1;

import java.util.List;

public class X_clwz_adapter2 extends BaseAdapter {
    private List<WZxx1> wzxx1;
    private MyJK myJK;

    public interface MyJK{

        void setjian(String daolu);
    }

    public void setjk(MyJK myjk){
        this.myJK = myjk;
    }

    public X_clwz_adapter2(List<WZxx1> wZxx1s) {
        this.wzxx1 = wZxx1s;
    }

    @Override
    public int getCount() {
        if (wzxx1.size()==0)return 0;
        return wzxx1.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cxjg_2, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final WZxx1 wZxx1 = wzxx1.get(position);
        holder.time.setText(wZxx1.getTime());
        holder.txtXuayuan.setText(wZxx1.getDaolu());
        holder.txtMsg.setText(wZxx1.getMsg());
        holder.txtKoufen.setText("扣分： "+wZxx1.getKoufen()+"             罚款："+wZxx1.getFakuan()+" 元");

        holder.lin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myJK.setjian(wZxx1.getDaolu());
            }
        });


        return convertView;
    }


    class ViewHolder {
        private LinearLayout lin3;
        private TextView time;
        private TextView txtXuayuan;
        private TextView txtMsg;
        private TextView txtKoufen;

        public ViewHolder(View view) {
            lin3 = view.findViewById(R.id.lin3);
            time = view.findViewById(R.id.time);
            txtXuayuan = view.findViewById(R.id.txt_xuayuan);
            txtMsg = view.findViewById(R.id.txt_msg);
            txtKoufen = view.findViewById(R.id.txt_koufen);
        }
    }

}
