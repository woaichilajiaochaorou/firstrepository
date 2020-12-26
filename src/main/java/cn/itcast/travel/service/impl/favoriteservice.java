package cn.itcast.travel.service.impl;

public interface favoriteservice {
    public boolean isfavorite(String rid,int uid);

    void add(String rid, int uid);
}
