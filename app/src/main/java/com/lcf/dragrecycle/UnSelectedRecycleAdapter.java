package com.lcf.dragrecycle;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lichunfu on 2018/2/7.
 */

public class UnSelectedRecycleAdapter extends RecyclerView.Adapter<UnSelectedRecycleAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mDatas;

    public interface OnItemClickListener {
        public void onItemClick(MyViewHolder holder, int pos);

        public void onItemLongClickListener(MyViewHolder viewHolder, int pos);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public UnSelectedRecycleAdapter(Context mContext, List<String> datas) {
        this.mContext = mContext;
        this.mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, parent, false));
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        holder.ivDelete.setImageDrawable(mContext.getDrawable(R.drawable.add));
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(holder, holder.getLayoutPosition());
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClickListener(holder, position);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(String data, int pos) {
        mDatas.add(pos, data);
        notifyItemInserted(pos);
    }

    public void removeData(int pos) {
        mDatas.remove(pos);

        notifyDataSetChanged();
        notifyItemRemoved(pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView ivDelete;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv = (TextView) itemView.findViewById(R.id.tv);
            ivDelete = (ImageView) itemView.findViewById(R.id.delelte);
        }
    }
}

