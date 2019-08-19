package com.yang.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����spring��Junit���ϣ�junit����ʱ������springIoC����
 * 
 * @author Yang
 *
 */
// �����õ�����ע�⣬�������Ҫ����junit ���spring����ʲô���������Ԫ
// �������õ���spring��junit���ϣ���˻��õ�SpringJUnit4ClassRunner.class�����ȥ��
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�������ļ�λ�ã� �����أ�
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml",
		"classpath:spring/spring-redis.xml" })
public class BaseTest {

}
