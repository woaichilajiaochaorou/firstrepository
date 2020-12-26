package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.pageBean;

//线路service
public interface routeservice {
    //分页查询
    public pageBean<Route> pageQuery(int cid, int currentpage, int pagesize, String content);

    Route FindOne(String rid);
}
