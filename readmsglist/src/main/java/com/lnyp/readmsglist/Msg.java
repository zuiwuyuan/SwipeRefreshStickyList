package com.lnyp.readmsglist;


public class Msg {

    public int id;

    public String title;

    public String desc;

    // false是未读；true是已读
    public boolean isRead;

    @Override
    public String toString() {
        return "Msg{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
