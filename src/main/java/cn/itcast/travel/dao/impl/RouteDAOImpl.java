package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDAO;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 21:36
 */
public class RouteDAOImpl implements RouteDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
        //1. 定义sql模板
        String sql = "SELECT count(*) FROM tab_route WHERE 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        //参数们
        List params = new ArrayList();
        //2. 判断参数是否有值
        if (cid != 0) {
            sb.append(" AND cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() != 0) {
            sb.append(" AND rname like ? ");
            params.add("%" + rname + "%");
        }

        sql = sb.toString();
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//        String sql = "SELECT * FROM tab_route WHERE cid = ? LIMIT ?,?";
        String sql = "SELECT * FROM tab_route WHERE 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        //参数们
        List params = new ArrayList();
        //2. 判断参数是否有值
        if (cid != 0) {
            sb.append(" AND cid = ? ");
            params.add(cid);
        }

        if (rname != null && rname.length() != 0) {
            sb.append(" AND rname like ? ");
            params.add("%" + rname + "%");
        }
        sb.append(" LIMIT ?,? ");
        params.add(start);
        params.add(pageSize);

        sql = sb.toString();

        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }
}
