package com.yang.o2o.web.local;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yang.o2o.dto.LocalAuthExecution;
import com.yang.o2o.entity.LocalAuth;
import com.yang.o2o.entity.PersonInfo;
import com.yang.o2o.enums.LocalAuthStateEnum;
import com.yang.o2o.service.LocalAuthService;
import com.yang.o2o.util.CodeUtil;
import com.yang.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping(value = "/local", method = { RequestMethod.GET, RequestMethod.POST })
public class LocalAuthController {

	@Autowired
	private LocalAuthService localAuthService;

	/**
	 * ��ƽ̨�˺���Ϣ���û���Ϣ��
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> bindLocalAuth(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// ��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}

		// ��ȡ������ʺ�
		String username = HttpServletRequestUtil.getString(request, "userName");
		// ��ȡ���ڵ�����
		String password = HttpServletRequestUtil.getString(request, "password");
		// ��session�л�ȡ��ǰ�û���Ϣ(�û�һ��(ͨ��΢��)��¼֮�󣬱��ܻ�ȡ���û�����Ϣ)
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

		// �ж���Ϣ�Ƿ�Ϊ��
		if (username != null && password != null && user != null && user.getUserId() != null) {
			// ����LocalAuth���󲢸�ֵ
			LocalAuth localAuth = new LocalAuth();
			localAuth.setLocatAuthName(username);
			localAuth.setPassWord(password);
			localAuth.setPersoninfo(user);
			// ���ʺ�
			LocalAuthExecution lae = localAuthService.blindLocalAuth(localAuth);
			// �жϰ��Ƿ�ɹ�
			if (lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�û��������벻��Ϊ��");
		}
		return modelMap;
	}

	/**
	 * ͨ���û��� ԭ���� �޸�����
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		// ��֤��У��
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}

		// ��ȡ�ʺ�
		String username = HttpServletRequestUtil.getString(request, "userName");
		// ��ȡԭ�ȵ�����
		String password = HttpServletRequestUtil.getString(request, "password");
		// ��ȡ������
		String passwordnew = HttpServletRequestUtil.getString(request, "passwordNew");
		// ��session�л�ȡ��ǰ�û���Ϣ(�û�һ��(ͨ��΢��)��¼֮�󣬱��ܻ�ȡ���û�����Ϣ)
		PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

		// �ǿ��жϣ�Ҫ���ʺ��¾������Լ���ǰ���û�session�ǿգ����¾����벻��ͬ
		if (username != null && password != null && passwordnew != null && user != null && user.getUserId() != null
				&& !password.equals(passwordnew)) {
			try {
				// �鿴ԭ���ʺţ�������������ʺ��Ƿ�һ�£���һ������Ϊ�ǷǷ�����
				LocalAuth la = localAuthService.getLocalAuthByUserId(user.getUserId());
				if (la == null || !la.getLocatAuthName().equals(username)) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "�û�����һ��");
					return modelMap;
				}
				// �޸�ƽ̨�ʺŵ��û�����
				LocalAuthExecution lae = localAuthService.modifyLocalAuth(user.getUserId(), username, password,
						passwordnew);
				// �ж��Ƿ��޸ĳɹ�
				if (lae.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "�޸�ʧ��:" + lae.getStateInfo());
				}
			} catch (Exception e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "changeLocalPwd:" + e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�û���������Ϊ��");
		}
		return modelMap;
	}

	/**
	 * ��¼��֤
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logincheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		
		// ��ȡ�Ƿ���Ҫ������֤��У��ı�ʶ��
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if (needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		// ��ȡ������ʺ�
		String userName = HttpServletRequestUtil.getString(request, "userName");
		// ��ȡ���������
		String password = HttpServletRequestUtil.getString(request, "password");
		// �ǿ�У��
		if (userName != null && password != null) {
			// �����ʺź�����ȥ��ȡƽ̨�ʺ���Ϣ
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
			if (localAuth != null) {
				// ����ȡ���ʺ���Ϣ���¼�ɹ�
				modelMap.put("success", true);
				// ͬʱ��session�������û���Ϣ
				request.getSession().setAttribute("user", localAuth.getPersoninfo());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "�û������������");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�û��������������Ϊ��");
		}
		return modelMap;
	}

	/**
	 * ���û�����ǳ���ť��ʱ��ע��session
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// ���û�session��Ϊ��
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
