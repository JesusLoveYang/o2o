package com.yang.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {
	
	/**
	 * ���˺�ҳ��ǰ��·��
	 * @return
	 */
	@RequestMapping(value = "/accountbind", method = RequestMethod.GET)
	private String getAccountBind() {
		return "local/accountbind";
	}
	
	/**
	 * ��¼ҳ��·��
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String login() {
		return "local/login";
	}
	
	/**
	 * ��¼ҳ��·��
	 * @return
	 */
	@RequestMapping(value = "/changepsw", method = RequestMethod.GET)
	private String changePSW() {
		return "local/changepsw";
	}
}
