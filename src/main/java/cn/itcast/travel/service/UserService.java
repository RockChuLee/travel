package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/23 16:45
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    Boolean regist(User user);

    /**
     * 激活用户
     * @param code
     * @return
     */
    boolean active(String code);
}
