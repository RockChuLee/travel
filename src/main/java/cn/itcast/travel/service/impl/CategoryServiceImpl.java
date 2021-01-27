package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDAO;
import cn.itcast.travel.dao.impl.CategoryDAOImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 19:14
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
}
