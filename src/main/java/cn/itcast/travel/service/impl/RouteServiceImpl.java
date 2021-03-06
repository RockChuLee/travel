package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDAO;
import cn.itcast.travel.dao.RouteDAO;
import cn.itcast.travel.dao.RouteImgDAO;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDAOImpl;
import cn.itcast.travel.dao.impl.RouteDAOImpl;
import cn.itcast.travel.dao.impl.RouteImgDAOImpl;
import cn.itcast.travel.dao.impl.SellerDAOImpl;
import cn.itcast.travel.domain.*;
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
    RouteImgDAO routeImgDAO = new RouteImgDAOImpl();
    SellerDao sellerDao = new SellerDAOImpl();
    FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装PageBean
        PageBean<Route> pageBean = new PageBean<>();
        //设置当前页码
        pageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        pageBean.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDAO.findTotalCount(cid,rname);
        pageBean.setTotalCount(totalCount);
        //设置当前页现实的数据集合
        int start = (currentPage - 1) * pageSize;
        List<Route> routes = new ArrayList<>();
        routes = routeDAO.findByPage(cid, start, pageSize,rname);
        pageBean.setList(routes);
        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public Route findOne(int rid) {
        //1.根据id去route里面查询route信息
        Route route = routeDAO.findOne(rid);

        //2. 根据route的id查询图片集合信息
        List<RouteImg> imgs = routeImgDAO.findByRid(rid);
        route.setRouteImgList(imgs);

        //3. 根据route的sid查询卖家信息
        Seller seller = sellerDao.findSellerBySid(route.getSid());
        route.setSeller(seller);

        //4. 根据rid查询收藏次数
        int count = favoriteDAO.findCountByRid(rid);
        route.setCount(count);

        return route;
    }
}
