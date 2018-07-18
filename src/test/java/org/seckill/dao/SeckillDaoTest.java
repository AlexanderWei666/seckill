package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/*
com.mchange.v2.resourcepool.BasicResourcePool$AcquireTask@7539476a -- Acquisition Attempt Failed!!!
Clearing pending acquires. While trying to acquire a needed new resource,
we failed to succeed more than the maximum number of allowed acquisition attempts (2).
Last acquisition attempt exception:
java.sql.SQLException: Access denied for user 'Alexander'@'localhost' (using password: YES)
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class SeckillDaoTest {

    //注入Dao实现依赖类
    @Resource
    private SeckillDao seckillDao;


    @Test
    public void testReduceNumber() throws ParseException {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date killTime = dateFormat2.parse("2015-11-01 22:00:00");
        int updateCount = seckillDao.reduceNumber(1000L,killTime);

    }

    @Test
    public void testQueryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    /*
    org.mybatis.spring.MyBatisSystemException:
    nested exception is org.apache.ibatis.binding.BindingException:
    Parameter 'offset' not found. Available parameters are [0, 1, param1, param2]
    java 没有保存形参的记录：queryAll(int offset, int limit) -> queryAll(arg0, arg1)
    形参只有在方法内才有名字和对应域，方法外不知道名字。
    也就是只有顺序没有实际名称，而这里mybatis是根据名字去找参数的
     */
    @Test
    public void testQueryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill : seckills){
            System.out.println(seckill);
        }
    }
}