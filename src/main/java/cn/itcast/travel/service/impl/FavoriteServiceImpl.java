package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDAO;
import cn.itcast.travel.dao.impl.FavoriteDAOImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/2 9:54
 */
public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    public Boolean isFavorite(int rid, int uid) {
        Favorite favorite = favoriteDAO.findByRidAndUid(rid, uid);
        if (favorite != null) {
            return true;
        }else{
            return false;
        }
    }
}
