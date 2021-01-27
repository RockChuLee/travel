package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 17:07
 */
@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    private UserService service = new UserServiceImpl();

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check = request.getParameter("check");
        //从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //为了保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");

        //不区分大小写的比较
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            //验证码错误
            ResultInfo result = new ResultInfo();
            result.setFlag(false);
            result.setErrorMsg("验证码错误");
            //将result对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service完成注册
//        UserService service = new UserServiceImpl();
        Boolean flag = service.regist(user);

        ResultInfo result = new ResultInfo();
        //4.相应结果
        if (flag) {
            //注册成功
            result.setFlag(true);
        } else {
            //注册失败
            result.setFlag(false);
            result.setErrorMsg("注册失败！");
        }

        //将result对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);

        //将json写会输出端
        //设置content-text
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2. 封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3. 调用service查询
//        UserService service = new UserServiceImpl();
        User u = service.login(user);

        ResultInfo result = new ResultInfo();

        //4. 判断用户对象是否为null
        if (u == null) {
            //用户名密码错误
            result.setFlag(false);
            result.setErrorMsg("用户名或密码错误");
        }
        //5. 判断用户是否激活
        if (u != null && !"Y".equals(u.getStatus())) {
            result.setFlag(false);
            result.setErrorMsg("您尚未激活，请激活");
        }
        //6. 判断登录成功
        if (u != null && "Y".equals(u.getStatus())) {
            //登录成功
            result.setFlag(true);
            request.getSession().setAttribute("user", u);
        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), result);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从Session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), user);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 销毁session
        request.getSession().invalidate();

        //2. 跳转登录页面
        //重定向需要使用虚拟路径
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取激活码
        String code = request.getParameter("code");
        if (code != null) {
            //2. 调用service完成激活
//            UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            //3. 判断标志
            String msg = null;
            if (flag) {
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                // 激活失败
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);

        }
    }
}
