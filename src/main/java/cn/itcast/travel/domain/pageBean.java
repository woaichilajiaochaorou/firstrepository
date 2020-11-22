package cn.itcast.travel.domain;

import java.awt.*;
import java.util.List;

public class pageBean<T> {
    /*
    * 分页对象
    * */
                 //总页数
private int totalpage;

private int totalcount;     //总记录数
private int currentpage;    //当前页码
private int pagesize;       //每页显示的条数
private List <T>list; //每页显示的数据集合

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public int getPagesize() {
        return pagesize;
    }

    public List getList() {
        return list;
    }

    public pageBean() {
    }

    public pageBean(int totalpage, int totalcount, int currentpage, int pagesize, List list) {
        this.totalpage = totalpage;
        this.totalcount = totalcount;
        this.currentpage = currentpage;
        this.pagesize = pagesize;
        this.list = list;
    }
}
