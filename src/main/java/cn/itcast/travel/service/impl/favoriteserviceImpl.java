package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.favoriteDao;
import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;

public class favoriteserviceImpl implements favoriteservice {
    private favoriteDao dao=new favoriteDaoImpl();
    @Override
    public boolean isfavorite(String rid, int uid) {
        Favorite favorite = dao.findbyuidAndUid(Integer.parseInt(rid), uid);

        return favorite==null? false:true;
    }

    @Override
    public void add(String rid, int uid) {
        dao.add(Integer.parseInt(rid),uid);

    }
}
