package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:29
 */
public interface RouteService {

    PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname);

    Route findOne(int rid);
}
