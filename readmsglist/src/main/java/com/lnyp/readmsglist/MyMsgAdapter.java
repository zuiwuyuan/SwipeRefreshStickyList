package com.lnyp.readmsglist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * my msgs list adapter
 */
public class MyMsgAdapter extends BaseAdapter {

    private Activity mContext;

    private LayoutInflater mInflater;

    private List<Msg> mDatas;

    public MyMsgAdapter(Activity context, List<Msg> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return (mDatas != null ? mDatas.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return (mDatas != null ? mDatas.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Msg myMsg = mDatas.get(position);
        boolean isRead = myMsg.isRead;
        if (isRead) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            // 下拉项布局
            convertView = mInflater.inflate(R.layout.list_item_my_msg, null);

            holder = new ViewHolder();

            holder.img_msg = (ImageView) convertView.findViewById(R.id.img_msg);
            holder.text_msg_user_name = (TextView) convertView.findViewById(R.id.text_msg_user_name);
            holder.text_msg_infos = (TextView) convertView.findViewById(R.id.text_msg_infos);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Msg myMsg = mDatas.get(position);

        if (myMsg != null) {

            if (myMsg.isRead) {//未读
                holder.img_msg.setImageResource(R.mipmap.readed_msg_img);
            } else {
                holder.img_msg.setImageResource(R.mipmap.unread_msg_img);
            }

            holder.text_msg_user_name.setText(myMsg.title);
            holder.text_msg_infos.setText(myMsg.desc);
        }

        return convertView;
    }

    class ViewHolder {

        ImageView img_msg;

        TextView text_msg_user_name;

        TextView text_msg_infos;
    }
}
