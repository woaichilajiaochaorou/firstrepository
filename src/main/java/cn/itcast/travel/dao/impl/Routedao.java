package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface Routedao {
    public abstract int findtotalcount(int cid, String content);

    List<Route> findByPage(int cid, int start, int pagesize, String content);


    Route findone(String rid);

    int findByCount(int rid);
}
