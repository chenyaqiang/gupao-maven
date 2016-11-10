package blog.base.sms.service.impl;

import org.springframework.stereotype.Service;

import blog.base.sms.service.SmsService;
import blog.base.sms.vo.SmsParam;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
@Service
public class SmsServiceImpl implements SmsService{

	@Override
	public void sendMsg(String phone, SmsParam smsParam) {
//		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		//将参数信息配置通过xml保存配置更好，例alidayu_config.xml
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23527088", "e2211e30053968e4cc20ed30cafe553e");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName("验证码");
		req.setSmsParamString(JSON.toJSONString(smsParam));
		req.setRecNum(phone);
		req.setSmsTemplateCode("SMS_25725473");
		AlibabaAliqinFcSmsNumSendResponse rsp=null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
		
	}
	
	
}
