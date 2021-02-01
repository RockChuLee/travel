package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDAO;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/1 18:27
 */
public class RouteImgDAOImpl implements RouteImgDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "SELECT * FROM tab_route_img where rid = ?";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}
