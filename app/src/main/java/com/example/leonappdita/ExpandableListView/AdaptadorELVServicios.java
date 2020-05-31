package com.example.leonappdita.ExpandableListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.leonappdita.R;

import java.util.ArrayList;
import java.util.Map;

public class AdaptadorELVServicios extends BaseExpandableListAdapter {

    private ArrayList<String> listCategoria;
    private Map<String, ArrayList<String>> mapChild;
    private Context context;

    public AdaptadorELVServicios(ArrayList<String> listCategoria, Map<String, ArrayList<String>> mapChild, Context context) {
        this.listCategoria = listCategoria;
        this.mapChild = mapChild;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listCategoria.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mapChild.get(listCategoria.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listCategoria.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mapChild.get(listCategoria.get(groupPosition)).get(childPosition);
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
        String tituloCategoria = (String)getGroup(groupPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.servicios_elv_group,null);
        TextView txvGroup = (TextView)convertView.findViewById(R.id.servElvTxvGroup);
        txvGroup.setText(tituloCategoria);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String item = (String)getChild(groupPosition,childPosition);
        convertView = LayoutInflater.from(context).inflate(R.layout.servicios_elv_child,null);
        TextView txvChild = (TextView)convertView.findViewById(R.id.servElvTxvChild);
        txvChild.setText(item);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
