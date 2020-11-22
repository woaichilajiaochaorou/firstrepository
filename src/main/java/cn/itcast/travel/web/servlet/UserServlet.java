package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.userservice;
import cn.itcast.travel.service.impl.userserviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    public void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是一个test ");
    }
    //用户退出
    public void exituser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();//销毁session

        response.sendRedirect(request.getContextPath()+"/index.html");
    }

@SuppressWarnings("all")//注册方法
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");

        if ( checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info=new ResultInfo();
            ObjectMapper mapper=new ObjectMapper();

            info.setErrorMsg("验证码错误!");
            info.setFlag(false);
            response.setContentType("application/json;charset=utf-8");
            String json = mapper.writeValueAsString(info);
            response.getWriter().write(json);
            return;

        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        /*调用service 完成注册*/
        userserviceImpl service=new userserviceImpl();
        boolean flag=service.regist(user);
        System.out.println(flag+"  在servlet里面" );


        ResultInfo info=new ResultInfo();
        if (flag){
            info.setFlag(flag);
        }else {
            info.setErrorMsg("注册失败,用户名已存在。");
            info.setFlag(false);
        }
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        /*返回json给前端*/
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    //登陆方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        boolean flag = checkcode_server.equalsIgnoreCase(check);
        ResultInfo info=new ResultInfo();
        if (!flag){
            //验证码错误
            info.setErrorMsg("验证码错误");
            info.setFlag(false);
        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        userservice service=new userserviceImpl();
        //查询用户
        User u=service.login(user);

        if(u==null){

            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }

        if (u!=null&&!"Y".equalsIgnoreCase(u.getStatus())){
            //用户未激活
            info.setErrorMsg("用户尚未激活");
            info.setFlag(false);

        }

        if (u!=null&&"Y".equalsIgnoreCase(u.getStatus())){
            //登陆成功
            info.setFlag(true);
            info.setErrorMsg("登陆成功");
            request.getSession().setAttribute("userinfo",u);
        }

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");

        mapper.writeValue(response.getWriter(),info);
    }

    public void returnuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user =(User) request.getSession().getAttribute("userinfo");

        ObjectMapper mapper=new ObjectMapper();

        String json = mapper.writeValueAsString(user);
        response.setContentType("application/json;charset=utf-8");

        response.getWriter().write(json);
    }

    @SuppressWarnings("all")
    public void activeuser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code!=null) {
            userservice service = new userserviceImpl();
            boolean flag=service.active(code);//激活用户
            String msg=null;
            if (flag){
                //激活成功
                msg="激活成功请<a href='login.html'>登录</a>";
            }else {
                msg="激活失败。。。";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }





}
