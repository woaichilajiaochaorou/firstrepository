package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;

public interface Userdaointerface {
    public abstract User findbyUsername(String Username);
    public abstract  void  save(User user);

    User findbycode(String code);

    void updatestatus(User user);
}
