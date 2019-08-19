package com.yang.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yang.o2o.dao.LocalAuthDao;
import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;
import com.yang.o2o.enums.LocalAuthStateEnum;
import com.yang.o2o.service.LocalAuthService;
import com.yang.o2o.util.MD5;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.getLocalAuthByUsernameAndPwd(username, MD5.getMd5(password)); // ���������MD5����
	}

	@Override
	public LocalAuth getLocalAuthByUserId(Long userId) {
		return localAuthDao.getLocalAuthByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution blindLocalAuth(LocalAuth localAuth) {
		// ��ֵ�жϣ������localAuth �ʺ����룬�û���Ϣ�ر���userId����Ϊ�գ�����ֱ�ӷ��ش���
		if (localAuth == null || localAuth.getLocatAuthName() == null || localAuth.getPassWord() == null
				|| localAuth.getPersoninfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}

		// ����һ������Ϊ�գ� ��ѯ���û��Ƿ��Ѱ󶨹�ƽ̨�ʺ�
		LocalAuth tempAuth = localAuthDao.getLocalAuthByUserId(localAuth.getPersoninfo().getUserId());
		if (tempAuth != null) {
			// ˵���Ѿ��󶨹���
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}

		// ���֮ǰû�а󶨹�ƽ̨�ʺţ��򴴽�һ��ƽ̨�ʺ�����û���
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setAlterTime(new Date());
			// ���������MD5����
			localAuth.setPassWord(MD5.getMd5(localAuth.getPassWord()));

			int effecedNum = localAuthDao.insertLocalAuth(localAuth);
			// �жϴ����Ƿ�ɹ�
			if (effecedNum <= 0) {
				throw new RuntimeException("���û�ʧ��");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertLocalAuth error:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String userName, String password, String newPassword) {
		// �ǿ��жϣ��жϴ�����û�Id,�ʺ�,�¾������Ƿ�Ϊ�գ��¾������Ƿ���ͬ���������������򷵻ش�����Ϣ
		if (userId == null || userName == null || password == null || newPassword == null
				|| password.equals(newPassword)) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		} else {
			try {
				// ����Ϊ���ˣ���ʼ�������룬�������������MD5����
				int effectedNum = localAuthDao.updateLocalAuth(userId, userName, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				// �жϸ����Ƿ�ɹ�
				if (effectedNum <=  0) {
					throw new RuntimeException("��������ʧ��");
				}else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyLocalAuth error:" + e.getMessage());
			}
		}
	}
	
}
