package com.yang.o2o.service;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.yang.o2o.BaseTest;
import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.enums.LocalAuthStateEnum;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthServiceTest extends BaseTest {

	@Autowired
	private LocalAuthService localAuthService;

	@Test
	@Ignore
	public void testABlindLocalAuth() {
		String username = "testusername";
		String password = "testpassword";

		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		// ����ƽ̨��Ϣ���ĸ��û���
		localAuth.setPersoninfo(personInfo);
		// �����˺�
		localAuth.setLocatAuthName(username);
		// ��������
		localAuth.setPassWord(password);
		// ��ʼ��
		LocalAuthExecution lae = localAuthService.blindLocalAuth(localAuth);
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(), lae.getState());
	}

	@Test
	public void testBModifyLocalAuth() {
		String username = "testusername";
		String password = "testpasswordnew";

		LocalAuthExecution lae = localAuthService.modifyLocalAuth(1L, username, password, password + "loc");
		
		assertEquals(LocalAuthStateEnum.SUCCESS.getState(), lae.getState());
	}

}
