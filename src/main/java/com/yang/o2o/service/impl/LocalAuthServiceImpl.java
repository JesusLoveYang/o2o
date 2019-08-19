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
		return localAuthDao.getLocalAuthByUsernameAndPwd(username, MD5.getMd5(password)); // 对密码进行MD5加密
	}

	@Override
	public LocalAuth getLocalAuthByUserId(Long userId) {
		return localAuthDao.getLocalAuthByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution blindLocalAuth(LocalAuth localAuth) {
		// 空值判断，传入的localAuth 帐号密码，用户信息特别是userId不能为空，否则直接返回错误
		if (localAuth == null || localAuth.getLocatAuthName() == null || localAuth.getPassWord() == null
				|| localAuth.getPersoninfo().getUserId() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}

		// 到这一步都不为空， 查询此用户是否已绑定过平台帐号
		LocalAuth tempAuth = localAuthDao.getLocalAuthByUserId(localAuth.getPersoninfo().getUserId());
		if (tempAuth != null) {
			// 说明已经绑定过，
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}

		// 如果之前没有绑定过平台帐号，则创建一个平台帐号与该用户绑定
		try {
			localAuth.setCreateTime(new Date());
			localAuth.setAlterTime(new Date());
			// 对密码进行MD5加密
			localAuth.setPassWord(MD5.getMd5(localAuth.getPassWord()));

			int effecedNum = localAuthDao.insertLocalAuth(localAuth);
			// 判断创建是否成功
			if (effecedNum <= 0) {
				throw new RuntimeException("绑定用户失败");
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
		// 非空判断，判断传入的用户Id,帐号,新旧密码是否为空，新旧密码是否相同，若不满足条件则返回错误信息
		if (userId == null || userName == null || password == null || newPassword == null
				|| password.equals(newPassword)) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		} else {
			try {
				// 都不为空了，开始更新密码，并对新密码进行MD5加密
				int effectedNum = localAuthDao.updateLocalAuth(userId, userName, MD5.getMd5(password),
						MD5.getMd5(newPassword), new Date());
				// 判断更新是否成功
				if (effectedNum <=  0) {
					throw new RuntimeException("更新密码失败");
				}else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new RuntimeException("modifyLocalAuth error:" + e.getMessage());
			}
		}
	}
	
}
