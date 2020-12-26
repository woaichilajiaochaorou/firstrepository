package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoimpl  implements sellerDao{
    private JdbcTemplate template =new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller FindSeller(int sid) {

        String sql="select * from tab_seller where sid = ? ";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid);
        System.out.println(seller.toString());
        return seller;
    }
}
