package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/23 16:46
 */
public interface UserDAO {

    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 保存用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据激活码查询用户
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 调用DAO修改激活状态
     * @param user
     */
    void modifyStatus(User user);
}
