package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_9_16.R;
import com.example.x_etc_9_16.bean.Wendu;

import java.util.List;

public class X_SHZS_adapter extends BaseAdapter {
    private List<Wendu> wenduList;

    public X_SHZS_adapter(List<Wendu> wenduList) {
        this.wenduList = wenduList;
    }

    @Override
    public int getCount() {
        return 5;
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_shzs, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Wendu wendu = wenduList.get(wenduList.size()-1);
        switch (position){
            case 0:
                holder.img.setImageResource(R.drawable.zhiwaixianzhishu);
                if (wendu.getIllumination()<1000){
                    holder.txtTitle.setText("弱("+wendu.getIllumination()+")");
                    holder.txtMsg.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                }else if (wendu.getIllumination()>3000){
                    holder.txtTitle.setText("强("+wendu.getIllumination()+")");
                    holder.txtMsg.setText("尽量减少外出，需要涂抹高倍数防晒霜");
                }else {
                    holder.txtTitle.setText("中等("+wendu.getIllumination()+")");
                    holder.txtMsg.setText("涂擦SPF大于15、PA+防晒护肤品");
                }
                break;
            case 1:
                holder.img.setImageResource(R.drawable.ganmaozhisu);
                if (wendu.getTemperature()<8){
                    holder.txtTitle.setText("较易发("+wendu.getTemperature()+")");
                    holder.txtMsg.setText("温度低，风较大，较易发生感冒，注意防护");
                }{
                    holder.txtTitle.setText("少发("+wendu.getTemperature()+")");
                    holder.txtMsg.setText("无明显降温，感冒机率较低");
                }
                break;
            case 2:
                holder.img.setImageResource(R.drawable.chuanyizhisu);
                if (wendu.getTemperature()<12){
                    holder.txtTitle.setText("冷("+wendu.getTemperature()+")");
                    holder.txtMsg.setText("建议穿长袖衬衫、单裤等服装");
                }else if (wendu.getTemperature()>21){
                    holder.txtTitle.setText("热("+wendu.getTemperature()+")");
                    holder.txtMsg.setText("适合穿T恤、短薄外套等夏季服装");
                }else {
                    holder.txtTitle.setText("舒适("+wendu.getTemperature()+")");
                    holder.txtMsg.setText("建议穿短袖衬衫、单裤等服装");
                }
                break;
            case 3:
                holder.img.setImageResource(R.drawable.yundongzhisu);
                if (wendu.getCo2()<3000){
                    holder.txtTitle.setText("适宜("+wendu.getCo2()+")");
                    holder.txtMsg.setText("气候适宜，推荐您进行户外运动");
                }else if (wendu.getCo2()>6000){
                    holder.txtTitle.setText("较不宜("+wendu.getCo2()+")");
                    holder.txtMsg.setText("空气氧气含量低，请在室内进行休闲运动");
                }else {
                    holder.txtTitle.setText("中("+wendu.getCo2()+")");
                    holder.txtMsg.setText("易感人群应适当减少室外活动");
                }
                break;
            case 4:
                holder.img.setImageResource(R.drawable.yundongzhisu);
                if (wendu.getPm25()<30){
                    holder.txtTitle.setText("优("+wendu.getCo2()+")");
                    holder.txtMsg.setText("空气质量非常好，非常适合户外活动，趁机出去多呼吸新鲜空气");
                }else if (wendu.getCo2()>100){
                    holder.txtTitle.setText("污染("+wendu.getCo2()+")");
                    holder.txtMsg.setText("空气质量差，不适合户外活动");
                }else {
                    holder.txtTitle.setText("良("+wendu.getCo2()+")");
                    holder.txtMsg.setText("易感人群应适当减少室外活动");
                }
                break;
        }

        return convertView;
    }


    class ViewHolder {
        private ImageView img;
        private TextView txtTitle;
        private TextView txtMsg;

        public ViewHolder(View view) {
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txtMsg = view.findViewById(R.id.txt_msg);
        }
    }
}
