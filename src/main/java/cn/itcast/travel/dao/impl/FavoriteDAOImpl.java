package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDAO;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/2/2 9:55
 */
public class FavoriteDAOImpl implements FavoriteDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "SELECT * FROM tab_favorite WHERE rid = ? AND uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "SELECT count(*) FROM tab_favorite WHERE rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    @Override
    public void addForvarite(int rid, int uid) {
        // 1. 定义sql
        String sql = "INSERT INTO tab_favorite(rid, date, uid) VALUES (?,?,?) ";
        // 2. 执行sql
        template.update(sql, rid, new Date(), uid);
    }
}
