package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 19:16
 */
public interface CategoryDAO {

    List<Category> findAll();
}
