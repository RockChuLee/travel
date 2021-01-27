package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDAO;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 19:16
 */
public class CategoryDAOImpl implements CategoryDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM tab_category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
