package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.Userdao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import javax.servlet.annotation.WebServlet;


public class userserviceImpl implements userservice{
    @Override
    public User login(User user) {

        Userdao dao=new Userdao();

        User u = dao.findbyUsernameAndpassword(user.getUsername(),user.getPassword());
        return u;
    }

    @Override
    public boolean active(String code) {
        Userdao dao=new Userdao();
        User user=dao.findbycode(code);
        dao.updatestatus(user);
        return true;
    }

    @Override
    public boolean regist(User user) {
        //查询用户对象
        Userdao userdao=new Userdao();
        User u = userdao.findbyUsername(user.getUsername());
        if (u!=null){
            //已注册
            return false;
        }else {

            //保存用户信息 用户不存在注册成功
            String code = UuidUtil.getUuid();//绑定激活码
            user.setCode(code);
            user.setStatus("N");
            userdao.save(user);

            //发送邮件
            String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击以激活</a>";
            MailUtils.sendMail(user.getEmail(),content,"点击以激活");
            return true;
        }


    }
}
