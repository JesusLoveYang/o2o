package com.yang.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.LocalAuth;

public interface LocalAuthDao {

	/**
	 * ͨ���˺� �� ���� ��ȡƽ̨�˺���Ϣ
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(@Param("userName") String userName, @Param("password") String passsord);

	/**
	 * ͨ���û���id��ȡƽ̨�˺���Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * ���ƽ̨�˺�
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * ͨ��userId��userName��password��������
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @param alterTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("userName") String userName,
			@Param("password") String password, @Param("newPassword") String newPassword,
			@Param("alterTime") Date alterTime);
}
