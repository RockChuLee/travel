package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 19:14
 */
public interface CategoryService {

    List<Category> findAll();
}
