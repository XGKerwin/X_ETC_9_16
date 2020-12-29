package com.example.x_etc_9_16.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.x_etc_9_16.bean.GJCX;
import com.example.x_etc_9_16.R;

import java.util.List;
import java.util.Map;

public class X_Elist_adapter extends BaseExpandableListAdapter {
    private Map<String, List<GJCX>> map;
    private String[] arr = {"中医院站", "联想大厦站"};


    public X_Elist_adapter(Map<String, List<GJCX>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(arr[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderFu holderFu;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gjcxfu, null);
            holderFu = new ViewHolderFu(convertView);
            convertView.setTag(holderFu);
        } else {
            holderFu = (ViewHolderFu) convertView.getTag();
        }
        holderFu.textView.setText(arr[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderZi holderZi;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_gjcxzi, null);
            holderZi = new ViewHolderZi(convertView);
            convertView.setTag(holderZi);
        } else {
            holderZi = (ViewHolderZi) convertView.getTag();
        }
        GJCX gjcx = map.get(arr[groupPosition]).get(childPosition);
        holderZi.txtNumber.setText(gjcx.getBus()+"号    ( "+gjcx.getPerson()+"人)");
        holderZi.txtTime.setText(gjcx.getDistance()/5/60+"分钟到达");
        holderZi.txtJvli.setText("距离站台"+gjcx.getDistance()+"米");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class ViewHolderFu {
        private TextView textView;

        public ViewHolderFu(View convertView) {
            textView = convertView.findViewById(R.id.fu_txt);
        }
    }

    class ViewHolderZi {
        private TextView txtNumber;
        private TextView txtTime;
        private TextView txtJvli;

        public ViewHolderZi(View view) {
            txtNumber = view.findViewById(R.id.txt_number);
            txtTime = view.findViewById(R.id.txt_time);
            txtJvli = view.findViewById(R.id.txt_jvli);
        }
    }

}
