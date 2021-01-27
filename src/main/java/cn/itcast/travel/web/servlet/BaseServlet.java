package cn.itcast.travel.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 17:09
 */
//@WebServlet(name = "BaseServlet")
//不需要被访问到
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseServlet的service方法被执行了");
        //完成方法分发
        //1. 获取请求路径
        String uri = req.getRequestURI();
//        System.out.println(uri);
        //2. 获取请求方法名称
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
//        System.out.println(methodName);
        //3. 获取方法对象Method
        //谁调用我，我代表谁
//        System.out.println(this);//UserServlet的对象
        try {
//            //getDeclaredMethod()忽略访问权限修饰符，来获取方法
//            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
//            //4. 执行方法
//            //暴力反思
//            method.setAccessible(true);
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
