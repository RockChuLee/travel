package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/1 18:28
 */
public interface SellerDao {

    Seller findSellerBySid(int sid);
}
