package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.impl.Categorydao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.categoryservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;

@WebServlet("/Category/*")
public class CategoryServlet extends BaseServlet {
    //查询所有category
    public void test(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.getWriter().write("hello world ");
        categoryservice service =new categoryservice();
        List<Category> categories = service.FindAll();

    }

    public void list(HttpServletRequest request,HttpServletResponse response){
       categoryservice service=new categoryservice();

        ObjectMapper mapper=new ObjectMapper();
        String json = null;
        try {
            List<Category> categories = service.FindAll();
             json = mapper.writeValueAsString(categories);
             response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
