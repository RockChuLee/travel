package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:36
 */
public interface RouteDAO {

    int findTotalCount(int cid);

    List<Route> findByPage(int cid, int start, int pageSize);
}
