package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/2 9:55
 */
public interface FavoriteDAO {

    Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void addForvarite(int rid, int uid);
}
