package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.RouteImg;

import java.awt.*;
import java.util.List;

public interface routeImageDao {
    public abstract List<RouteImg> findByrid(int rid);
}
