package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.Routedao;
import cn.itcast.travel.dao.impl.SellerDaoimpl;
import cn.itcast.travel.dao.impl.routedaoimpl;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.pageBean;

import java.util.List;

public class routeserviceimpl implements routeservice{
    @Override

    //封装pagebean对象
    public pageBean<Route> pageQuery(int cid, int currentpage, int pagesize, String content) {
        Routedao routedao=new routedaoimpl();


        //cid 前端页面返回
        //currentpage 前端当前页面
        //pagesize 页面的数据条数
        pageBean<Route> pageBean=new pageBean<>();

        pageBean.setCurrentpage(currentpage);

        pageBean.setPagesize(pagesize);

        int totalcount=routedao.findtotalcount(cid,content);



        pageBean.setTotalcount(totalcount);

        //设置当前页面的显示数据
        int start=0;


        start=(currentpage-1)*pagesize;


        List<Route> list=routedao.findByPage(cid,start,pagesize,content);
        pageBean.setList(list);
        //设置总页数
        int totalpage=totalcount%pagesize==0 ?totalcount/pagesize : (totalcount/pagesize+1);
        pageBean.setTotalpage(totalpage);
        return pageBean;
    }

    @Override
    //根据id查询route对象
    public Route FindOne(String rid) {
        routedaoimpl dao=new routedaoimpl();
        SellerDaoimpl Sellerdao=new SellerDaoimpl();
        Route route=dao.findone(rid);
        List<RouteImg> routeImgslist = dao.findByrid(Integer.parseInt(rid));
        route.setRouteImgList(routeImgslist);
        Seller seller = Sellerdao.FindSeller(route.getSid());
        route.setSeller(seller);

        return route;
    }
}
