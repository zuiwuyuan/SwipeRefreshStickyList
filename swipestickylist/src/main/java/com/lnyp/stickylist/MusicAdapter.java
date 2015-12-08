package com.lnyp.stickylist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 李宁 on 2015/12/8.
 */
public class MusicAdapter extends BaseAdapter {

    private List<Music> mDatas;

    private Context mContext;

    public MusicAdapter(List<Music> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Music getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_item_music, null);
            new ViewHolder(convertView);
        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        Music music = mDatas.get(position);

        holder.textTitle.setText(music.title);
        holder.textDesc.setText(music.desc);

        return convertView;
    }

    class ViewHolder {
        TextView textTitle;
        TextView textDesc;

        public ViewHolder(View view) {
            textTitle = (TextView) view.findViewById(R.id.textTitle);
            textDesc = (TextView) view.findViewById(R.id.textDesc);
            view.setTag(this);
        }
    }
}
