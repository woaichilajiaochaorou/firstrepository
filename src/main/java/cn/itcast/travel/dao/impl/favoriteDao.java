package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;

public interface favoriteDao {

    public Favorite findbyuidAndUid(int rid, int uid);

    public void add(int rid, int uid);
}
