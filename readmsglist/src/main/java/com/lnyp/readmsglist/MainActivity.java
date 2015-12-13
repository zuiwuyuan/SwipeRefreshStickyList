package com.lnyp.readmsglist;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {

    // 方案列表
    @Bind(R.id.listViewMyMsgs)
    public SwipeMenuListView listViewMyMsgs;

    private MyMsgAdapter msgAdapter;

    // 消息集合
    private List<Msg> msgs;

    // 要删除的数据
    private Msg dMsg;

    // 要修改的数据
    private int oPos;

    private Msg oMyMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        msgs = new ArrayList<Msg>();

        Msg msg1 = new Msg();
        msg1.id = 1;
        msg1.title = "上邪";
        msg1.desc = "我欲与君相知，长命无绝衰";
        msg1.isRead = false;

        Msg msg2 = new Msg();
        msg2.id = 2;
        msg2.title = "爱在记忆中找你";
        msg2.desc = "如果可以恨你，全力痛恨你，连遇上亦要躲避";
        msg2.isRead = true;

        msgs.add(msg1);
        msgs.add(msg2);

        msgAdapter = new MyMsgAdapter(this, msgs);
        listViewMyMsgs.setAdapter(msgAdapter);

        createMenu();
    }

    /**
     * 删除某个消息
     *
     * @param position
     */
    private void deleteMsg(int position) {
        // DoDeleteMsgRequest(String id,Handler mHandler, int reqCode)
        dMsg = msgs.get(position);
        if (dMsg != null) {
            Toast.makeText(MainActivity.this, "删除 ： " + dMsg, Toast.LENGTH_SHORT).show();

            msgs.remove(dMsg);
            msgAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 标记已读和未读
     *
     * @param position
     */
    private void readMsg(int position) {
        //DoReadMsgRequest(String id, String isRead, Handler mHandler, int reqCode)
        oPos = position;
        oMyMsg = msgs.get(position);
        if (oMyMsg != null) {
            msgs.get(position).isRead = !msgs.get(position).isRead;

            msgAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 查询我的消息
     */
    private void qryDataFromServer() {

    }

    private void updateData() {
        msgAdapter.notifyDataSetChanged();
    }

    private void createMenu() {
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 0:// 未读
                        createMenu1(menu);
                        break;
                    case 1:// 已读
                        createMenu2(menu);
                        break;
                }
            }

            private void createMenu1(SwipeMenu menu) {
                SwipeMenuItem unreadItem = new SwipeMenuItem(
                        getApplicationContext());
                unreadItem.setId(1);
                unreadItem.setBackground(new ColorDrawable(Color.parseColor("#555555")));
                unreadItem.setWidth(dp2px(90));
                unreadItem.setTitle("标为已读");
                unreadItem.setTitleSize(16);
                unreadItem.setTitleColor(Color.parseColor("#FFFFFF"));
                menu.addMenuItem(unreadItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setId(0);
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#EF4B3A")));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.parseColor("#FFFFFF"));
                menu.addMenuItem(deleteItem);
            }

            private void createMenu2(SwipeMenu menu) {
                SwipeMenuItem readedItem = new SwipeMenuItem(
                        getApplicationContext());
                readedItem.setId(2);
                readedItem.setBackground(new ColorDrawable(Color.parseColor("#555555")));
                readedItem.setWidth(dp2px(90));
                readedItem.setTitle("标记未读");
                readedItem.setTitleSize(16);
                readedItem.setTitleColor(Color.parseColor("#FFFFFF"));
                menu.addMenuItem(readedItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                deleteItem.setId(0);
                deleteItem.setBackground(new ColorDrawable(Color.parseColor("#EF4B3A")));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(16);
                deleteItem.setTitleColor(Color.parseColor("#FFFFFF"));
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listViewMyMsgs.setMenuCreator(creator);

        // step 2. listener item click event
        listViewMyMsgs.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                SwipeMenuItem menuItem = menu.getMenuItem(index);
                int id = menuItem.getId();
                switch (id) {
                    case 0:
                        LogUtils.e("删除 :" + position);
                        deleteMsg(position);
                        break;
                    case 1:
                        LogUtils.e("标记未读 :" + position);
                        readMsg(position);
                        break;
                    case 2:
                        LogUtils.e("标为已读 :" + position);
                        readMsg(position);
                        break;
                }
                return false;
            }
        });
    }

    @OnItemClick(R.id.listViewMyMsgs)
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        Msg myMsg = (Msg) parent.getAdapter().getItem(
                position);
        if (myMsg != null) {

            if (myMsg.isRead) { // 如果是未读，需要标记为已读
                readMsg(position);
            }
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}