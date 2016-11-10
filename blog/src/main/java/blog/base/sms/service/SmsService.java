package blog.base.sms.service;

import org.springframework.stereotype.Service;

import blog.base.sms.vo.SmsParam;
public interface SmsService {
	/**
	 * 发送短信接口
	 * @param phone
	 * @param smsParam
	 */
	void sendMsg(String phone,SmsParam smsParam );
}
