package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class favoriteDaoImpl implements favoriteDao{
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findbyuidAndUid(int rid, int uid) {
        String sql= "select * from tab_favorite where rid=? and uid=? ";
        Favorite favorite=null;
        try {
             favorite = template.queryForObject(sql, new BeanPropertyRowMapper<>(Favorite.class), rid, uid);
        }catch (Exception e){
            System.out.println(e);
        }

        return favorite;
    }

    @Override
    public void add(int rid, int uid) {
        String sql="insert into tab_favorite (rid,uid,date)  values(?,?,?) ";
        template.update(sql,rid,uid,new Date());
    }


}
