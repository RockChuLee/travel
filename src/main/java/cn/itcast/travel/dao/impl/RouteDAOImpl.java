package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDAO;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:36
 */
public class RouteDAOImpl implements RouteDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid) {
        String sql = "SELECT count(*) FROM tab_route WHERE cid = ?";
        return template.queryForObject(sql, Integer.class, cid);
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize) {
        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT ?,?";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, start, pageSize);
    }
}
