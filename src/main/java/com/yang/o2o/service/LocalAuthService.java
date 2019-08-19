package com.yang.o2o.service;

import org.springframework.stereotype.Service;

import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;

@Service
public interface LocalAuthService {

	/**
	 * 通过用户名和密码获取平台信息
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	/**
	 * 通过用户id获取平台信息
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(Long userId);

	/**
	 * 生产平台专属账号
	 * 
	 * @param localAuth
	 * @return
	 */
	LocalAuthExecution blindLocalAuth(LocalAuth localAuth);

	/**
	 * 通过用户名和密码 修改密码
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword);
}
