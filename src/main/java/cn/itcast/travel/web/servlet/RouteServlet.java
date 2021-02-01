package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:28
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    RouteService routeService = new RouteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收参数
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //接受线路名称
        String rname = request.getParameter("rname");
        if (rname != null && rname.length() != 0) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }

        int cid = 0;
        //2. 处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1;//当前页码，默认第一页
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        }

        int pageSize = 5;//每页显示行数，默认五条
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //3. 调用service查询pageBean
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);
        //4. 将pageBean序列化返回
        writeValue(pageBean, response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取参数
        String ridStr = request.getParameter("rid");
        //1.1 处理数据
        int rid = 0;
        if (ridStr != null && ridStr.length() > 0 && !"null".equals(ridStr)) {
            rid = Integer.parseInt(ridStr);
        }

        //调用service方法
        Route route = routeService.findOne(rid);

        //3.转为json写回客户端
        writeValue(route, response);
    }
}
