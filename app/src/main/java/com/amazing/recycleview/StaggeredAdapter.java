package com.amazing.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xgj on 2015/10/8.
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<String> mDatas;

    private ArrayList<Integer> mHeights;

    public StaggeredAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mInflater = LayoutInflater.from(context);

        mHeights = new ArrayList<Integer>();
        for(int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random()*300));  //设置随机高度
        }
    }

    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup arg0, int i) {

        View view = mInflater.inflate(R.layout.item_single_textview, arg0, false);
        StaggeredAdapter.MyViewHolder viewHolder = new StaggeredAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StaggeredAdapter.MyViewHolder holder, int pos) {

        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        lp.height = mHeights.get(pos);
        holder.itemView.setLayoutParams(lp);

        holder.tv.setText(mDatas.get(pos));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View arg0) {
            super(arg0);

            tv = (TextView) arg0.findViewById(R.id.id_tv);
        }
    }
}

