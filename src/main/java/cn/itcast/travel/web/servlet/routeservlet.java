package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.domain.pageBean;
import cn.itcast.travel.service.impl.favoriteserviceImpl;
import cn.itcast.travel.service.impl.routeservice;
import cn.itcast.travel.service.impl.routeserviceimpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class routeservlet extends BaseServlet {
    routeservice service=new routeserviceimpl();

    public void querypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        String cid = request.getParameter("cid");
        String content = request.getParameter("content");



        //处理参数
        int int_cid =0;
        if (cid!=null&&cid.length()>0&&!"null".equals(cid)){
            //
             int_cid = Integer.parseInt(cid);

        }

        int int_currentpage;
        if (currentPage!=null&&currentPage.length()>0){
             int_currentpage = Integer.parseInt(currentPage);

        }else {
            int_currentpage=1;
        }

        int int_pageSize;
        if (pageSize!=null&&pageSize.length()>0){
            int_pageSize = Integer.parseInt(pageSize);

        }else {
            int_pageSize=5;//默认每页五条记录
        }
        //把路线查询的结果返回
        try {

            pageBean<Route> routepageBean = service.pageQuery(int_cid, int_currentpage, int_pageSize,content);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(routepageBean);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

            System.out.println(json);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //根据id查询一个旅游线路的详细信息
    public void findone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受id
        String rid = request.getParameter("rid");


        try {
            Route route = service.FindOne(rid);
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(route);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            System.out.println(json);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void isfavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("userinfo");
        int uid=0;

        if (user==null){
            //用户尚未登录
            uid=0;
        }else {
            //用户已经登陆
            uid=user.getUid();
        }
        //调用service查看是否收藏
        favoriteserviceImpl impl=new favoriteserviceImpl();
        boolean isfavorite = impl.isfavorite(rid, uid);
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(isfavorite);

        response.getWriter().write(json);
    }

    public void addfavorite(HttpServletRequest request, HttpServletResponse response){
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("userinfo");
        int uid=0;
        if(user==null){
            //用户未登录 给出提示信息
            return;

        }else{
            //用户已经登陆       直接收藏线路
            uid=user.getUid();
        }
        favoriteserviceImpl favoriteservice =new favoriteserviceImpl();
        try {
            favoriteservice.add(rid, uid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
