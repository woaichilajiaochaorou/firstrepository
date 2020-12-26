package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Userdao implements Userdaointerface{
    private static JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findbycode(String code) {
        String sql = "select * from tab_user where code = ? ";
        User user = null;
        try {


            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), code);
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    @Override
    public void updatestatus(User user) {//修改用户状态
        String sql="update tab_user set status ='Y' where uid=?";
         jdbcTemplate.update(sql, user.getUid());
    }

    @Override
    public User findbyUsername(String Username) {
        String sql="select * from tab_user where username= ? ";
        User user =null;
        try {
             user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), Username);
        }catch (Exception e){

        }

        return user; }

    @Override
    public void save(User user) {//保存用户
            String sql="insert into tab_user (username,password,name,birthday,sex,telephone,email,status,code)" +
                    " values(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),user.getBirthday(),
                user.getSex(),user.getTelephone(),
                user.getEmail()
        ,user.getStatus(),user.getCode());

    }


    public User findbyUsernameAndpassword(String username,String password) {
        String sql="select * from tab_user where username= ? and password = ? ";
        User user =null;
        try {
            user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username,password);
        }catch (Exception e){
            System.out.println(e);
        }

        return user;
    }
}


