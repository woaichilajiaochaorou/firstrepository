package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Categorydao implements Categoryinterface{
    JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findlist() {
        String sql="select * from tab_category";
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return list;
    }
}
