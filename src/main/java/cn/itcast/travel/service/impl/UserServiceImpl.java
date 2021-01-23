package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDAO;
import cn.itcast.travel.dao.impl.UserDAOImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/23 16:46
 */
public class UserServiceImpl implements UserService {

    private UserDAO userDAO = new UserDAOImpl();

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean regist(User user) {
        //1. 根据用户名查询用户对象（如果存在返回false）
        User u = userDAO.findByUsername(user.getUsername());

        //判断u是否为null
        if (u != null) {
            //用户名存在，注册失败
            return false;
        }
        //2. 保存用户信息
        userDAO.saveUser(user);
        return true;
    }
}
