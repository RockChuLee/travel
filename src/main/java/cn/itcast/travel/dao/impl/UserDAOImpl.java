package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDAO;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/23 16:46
 */
public class UserDAOImpl implements UserDAO {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            // 1. 定义sql
            String sql = "SELECT * FROM tab_user WHERE username = ?";
            // 2. 执行Sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
        }

        return user;
    }

    /**
     * 保存用户
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        // 1. 定义sql
        String sql = "INSERT INTO tab_user(username, password, name, birthday, sex, telephone, email) VALUES (?,?,?,?,?,?,?) ";
        // 2. 执行sql
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(),
                user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail());
    }
}
