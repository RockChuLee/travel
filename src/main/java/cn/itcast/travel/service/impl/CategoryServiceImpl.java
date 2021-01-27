package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDAO;
import cn.itcast.travel.dao.impl.CategoryDAOImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/1/27 19:14
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {
        //1. 从redis中查询
        //1.1 获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //1.2 可使用sortedset排序查询
//        Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3 查询sortedset中的分数（cid）和值（cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        //2. 判断查询的集合是否为空
        List<Category> categoryList = new ArrayList<>();
        if (categorys == null || categorys.size() == 0) {
            System.out.println("从数据库查询");

            //3. 如果为空，需要从数据库查询，再将数据存入redis
            //3.1 从数据库查询
            categoryList = categoryDAO.findAll();
            //3.2将集合存储到redis中
            for (int i = 0; i < categoryList.size(); i++) {
                jedis.zadd("category", categoryList.get(i).getCid(), categoryList.get(i).getCname());
            }
        }else {
            System.out.println("从redis查询");

            //4. 如果不为空，将set的数据存入List
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                categoryList.add(category);
            }
        }
        return categoryList;
    }
}
