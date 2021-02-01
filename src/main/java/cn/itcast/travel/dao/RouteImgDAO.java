package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/1 18:27
 */
public interface RouteImgDAO {

    List<RouteImg> findByRid(int rid);
}
