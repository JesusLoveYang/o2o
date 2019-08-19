package com.yang.o2o.dao;

import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.entity.LocalAuth;
import com.yang.o2o.entity.PersonInfo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {

	@Autowired
	private LocalAuthDao localAuthDao;

	private static final String testusername = "testusername";
	private static final String testpassword = "testpassword";

	@Test
	@Ignore
	public void testAInsertLocalAuth() {
		// ����һ��ƽ̨�˺���Ϣ
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		// ��ƽ̨���û���Ϣ
		localAuth.setPersoninfo(personInfo);
		localAuth.setLocatAuthName(testusername);
		localAuth.setPassWord(testpassword);
		localAuth.setCreateTime(new Date());

		int effectNum = localAuthDao.insertLocalAuth(localAuth);
		System.out.println(effectNum);
	}

	@Test
	public void testBGetLocalAuthByUsernameAndPwd() {
		LocalAuth la = localAuthDao.getLocalAuthByUsernameAndPwd(testusername, testpassword);
		System.out.println(la.getLocatAuthName());
		System.out.println(la.getCreateTime());
	}

	@Test
	public void testCGetLocalAuthByUserId() {
		LocalAuth la = localAuthDao.getLocalAuthByUserId(1L);
		System.out.println(la.getLocatAuthName());
	}

	@Test
	public void testDUpdateLocalAuth() {
		// �޸��˺�����
		Date date = new Date();
		int effectNum = localAuthDao.updateLocalAuth(1L, testusername, testpassword, testpassword + "new", date);
		System.out.println(effectNum);

		// ��ѯ������Ϣ
		LocalAuth la = localAuthDao.getLocalAuthByUserId(1L);
		System.out.println(la.getPassWord());
	}
}
