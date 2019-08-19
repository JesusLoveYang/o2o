package com.yang.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和Junit整合，junit启动时，加载springIoC容器
 * 
 * @author Yang
 *
 */
// 由于用到的是注解，因此我需要告诉junit 这个spring是用什么类跑这个单元
// 由于我用的是spring和junit整合，因此会用到SpringJUnit4ClassRunner.class这个类去跑
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件位置， 并加载！
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-redis.xml" })
public class BaseTest {

}
