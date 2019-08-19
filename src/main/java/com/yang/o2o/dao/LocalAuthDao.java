package com.yang.o2o.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.yang.o2o.entity.LocalAuth;

public interface LocalAuthDao {

	/**
	 * 通过账号 和 密码 获取平台账号信息
	 * 
	 * @param userName
	 * @param passWord
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(@Param("userName") String userName, @Param("password") String passsord);

	/**
	 * 通过用户的id获取平台账号信息
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(long userId);

	/**
	 * 添加平台账号
	 * 
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过userId、userName、password更改密码
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
