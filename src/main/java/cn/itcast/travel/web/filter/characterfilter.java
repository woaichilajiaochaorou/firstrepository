package cn.itcast.travel.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class characterfilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
        String method = httpServletRequest.getMethod();

        if (method.equalsIgnoreCase("post")){
            httpServletRequest.setCharacterEncoding("utf-8");
        }

        //处理响应乱码
        httpServletResponse.setContentType("text/html;charset=utf-8");

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
