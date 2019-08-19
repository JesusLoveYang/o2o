package com.yang.o2o.service;

import org.springframework.stereotype.Service;

import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;

@Service
public interface LocalAuthService {

	/**
	 * ͨ���û����������ȡƽ̨��Ϣ
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	/**
	 * ͨ���û�id��ȡƽ̨��Ϣ
	 * 
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(Long userId);

	/**
	 * ����ƽ̨ר���˺�
	 * 
	 * @param localAuth
	 * @return
	 */
	LocalAuthExecution blindLocalAuth(LocalAuth localAuth);

	/**
	 * ͨ���û��������� �޸�����
	 * 
	 * @param userId
	 * @param userName
	 * @param password
	 * @param newPassword
	 * @return
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword);
}
