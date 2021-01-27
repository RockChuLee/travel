package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDAO;
import cn.itcast.travel.dao.impl.UserDAOImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

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
        //2.1 设置激活码（唯一）
        user.setCode(UuidUtil.getUuid());
        //2.2 设置激活状态
        user.setStatus("N");
        userDAO.saveUser(user);

        //3. 激活邮件发送
        //如果是发布的项目就不能使用localhost，而是项目的域名
        String content = "<a href='http://localhost/travel/user/active?code=" + user.getCode() + "'>激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        //1. 根据激活码查询用户
        User user = userDAO.findByCode(code);
        if (user != null) {
            // 2. 调用DAO修改激活状态
            userDAO.modifyStatus(user);
            return true;
        }
        return false;
    }

    /**
     *登陆方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        User u = userDAO.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return u;
    }
}
