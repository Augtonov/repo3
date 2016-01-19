package com.example.tony.myapplication.ui.main.tabs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tony.myapplication.R;
import com.example.tony.myapplication.ui.pojo.frag1.Item;

import java.util.ArrayList;

/**
 * Created by tony on 12/22/2015.
 */
public class AdapterFragment1 extends RecyclerView.Adapter<AdapterFragment1.MyViewHolder> {

    private Context context;
    private ArrayList<Item> list;

    public AdapterFragment1(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inflate_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try {
            holder.textViewSubHeading.setText(list.get(position).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSubHeading;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewSubHeading = (TextView) itemView.findViewById(R.id.textHeading);
        }
    }


}
