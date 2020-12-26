package com.itcast.travel.daoTest;


import cn.itcast.travel.dao.impl.favoriteDaoImpl;
import org.junit.Test;

public class favoriteDaoTest {

    @Test
    public void addFavoriteTest(){
        favoriteDaoImpl favoriteDao = new favoriteDaoImpl();
        favoriteDao.add(1,7);
    }
}
