package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class routedaoimpl implements Routedao,routeImageDao{
    JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findtotalcount(int cid, String content) {
        /*String sql="select count(*) from tab_route where cid = ? ";*/
          String sql="select  count(*) from tab_route where 1=1 ";
          StringBuilder sb=new StringBuilder(sql);
          List params=new ArrayList();

          if (cid!=0){
              sb.append(" and cid =? ");
            params.add(cid);//添加?对应的值
          }
          if (content!=null&&content.length()>0&&!"null".equals(content)){
              sb.append(" and rname like ? ");
            params.add("%"+content+"%");
          }
         sql = sb.toString();
        Integer count = template.queryForObject(sql, Integer.class,params.toArray());

        return count;
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pagesize, String content) {
        /*String sql="select * from tab_route where cid =? and ranme = ? limit ? , ? ";*/
        String sql="select * from tab_route where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();

        if (cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);//添加?对应的值
        }
        if (content!=null&&content.length()>0&&!"null".equals(content)){
            sb.append(" and rname like ? ");
            params.add("%"+content+"%");
        }
        sb.append("limit ?,?");//添加分页条件
        params.add(start);
        params.add(pagesize);
        sql = sb.toString();
        List<Route> routes = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());

        return routes;
    }

    @Override
    public Route findone(String rid) {
        String sql="select * from tab_route where rid =? ";
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), Integer.parseInt(rid));
        return route;
    }

    @Override
    public int findByCount(int rid) {
        String sql= "select count(*) from tab_favorite where rid =? ";
        Integer count = template.queryForObject(sql, Integer.class, rid);
        return count;
    }

    @Override
    public List<RouteImg> findByrid(int rid) {
        String sql="select * from tab_route_img where rid =? ";
        List<RouteImg> routeImgs = template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
        return routeImgs;
    }


}
