package com.lcz.blog.redis;

import com.lcz.blog.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml","classpath:spring/applicationContext.xml","classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-redis.xml","classpath:spring/spring-shiro.xml"})
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;
    private Jedis jedis;

    @Test
    public void test(){
        //测试redis，需要修改配置文件blog.redis.open=true
        redisUtils.set("blog", "cc_blog");
        String blog = redisUtils.get("blog");
        System.out.println(blog);
        redisUtils.delete("blog");
        System.out.println(blog);
    }

    @Test
    public void testDistributeLock(){
        boolean result = redisUtils.tryGetDistributedLock(jedis, "testLock", UUID.randomUUID().toString(), 60 * 60 * 24);
        if(result == true){
            System.out.println("业务逻辑");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            redisUtils.releaseDistributedLock(jedis, "testLock", UUID.randomUUID().toString());
        }else{
            System.out.println("加锁失败");
        }

    }
}
