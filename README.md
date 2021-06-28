# 【黑马旅游网】总结
以下，是笔者项目中的笔记

[【黑马旅游网】准备工作](https://www.cnblogs.com/zllk/p/14367737.html)

[【黑马旅游网】登录模块](https://www.cnblogs.com/zllk/p/14367747.html)

[【黑马旅游网】Servlet优化](https://www.cnblogs.com/zllk/p/14367758.html) 

[【黑马旅游网】旅游模块](https://www.cnblogs.com/zllk/p/14367764.html)

[【黑马旅游网】总结](https://www.cnblogs.com/zllk/p/14367820.html)

体验过底层的JDBC Template操作，才能明白Mybatis的便利；使用过Servlet才能真正体会MVC的真谛。这就是我这个项目最真实的体验。

其实项目中使用的还不是最底层的操作JDBCUtil已经对数据库的链接、调用和关闭进行了一定程度的封装，但是通过这个你就可以理解SpringBoot在整合Mybits时大致做了什么。无外乎建立链接，预处理SQL，执行SQL。同样的，项目中的HTTP请求也是经过了Spring的封装，让我们省去了很多XML的操作，最后作者还进行了BaseServlet的抽取，学习过SpringMVC的朋友们应该都不难看出这就是SpringMVC的核心DispatcherServlet的原理，只不过SpringMVC做的更加强大。

项目中还有很多有意思的小东西，比如redis的使用，这里建议同学们详细学习redis之后，再来使用，笔者也将代码中相关部分注释了，因为不了解redis的朋友们很难去解决和理解这个问题。在SpringBoot中RedisTemplate继续优化了对redis的操作，有兴趣的同学可以学习。

[RedisTemplate操作Redis，这一篇文章就够了（一）](https://blog.csdn.net/lydms/article/details/105224210)

项目中还是用了自定义的MailUtil来发送邮件，而SpringBoot继续整合JavaMailSender，进行优化。

[Spring Boot使用JavaMailSender发送邮件](https://www.cnblogs.com/wxc-xiaohuang/p/9532631.html)

过滤器其实就是特殊的servlet，它与servlet和listener并称JavaWeb三大组件。

[Java Web之过滤器（Filter）](https://yuzhiqiang.blog.csdn.net/article/details/81288912)、[JavaWeb之监听器Listener](https://yuzhiqiang.blog.csdn.net/article/details/81324953)

不过在现在开发中，由于SpringBoot的盛行，我们还会使用一个叫拦截器的组件，他的功能和Filter相似。而监听器在web开发中已经很少使用了。

[拦截器和过滤器的执行顺序和区别](https://www.cnblogs.com/juanzila/p/11276067.html)



除此之外还有Md5，Uuid，动态生成验证码，都很有趣。是一个麻雀虽小，五脏俱全的项目。

# Bug处理
第一次运行，可以会出现如图bug。

![](https://upload-images.jianshu.io/upload_images/21656169-63e12c44ddcee939.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

解决方法：找到MailUtils，点击右下角将文字编码格式由UTF-8转换成GBK后，再换回UTF-8即可。注意选择convert方式，别点load。

参考文献：https://blog.csdn.net/Soinice/article/details/86169875
