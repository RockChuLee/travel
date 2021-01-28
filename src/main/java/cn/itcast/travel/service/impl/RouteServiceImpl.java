package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDAO;
import cn.itcast.travel.dao.impl.RouteDAOImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:30
 */
public class RouteServiceImpl implements RouteService {

    RouteDAO routeDAO = new RouteDAOImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDAO.findTotalCount(cid);
        pageBean.setTotalCount(totalCount);
        //设置当前页现实的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> routes = new ArrayList<>();
        routes = routeDAO.findByPage(cid, start, pageSize);
        pageBean.setList(routes);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
