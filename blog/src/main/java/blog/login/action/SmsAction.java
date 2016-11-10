package blog.login.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.base.sms.service.SmsService;
import blog.base.sms.vo.SmsParam;

@Controller  
@RequestMapping("/sms")  
public class SmsAction {
	@Autowired
	private SmsService smsService;
	@RequestMapping("/send")
	public String send(String phone, Model model) {
		SmsParam smsParam = new SmsParam();
		smsParam.setBlog("µß¸²»î¶¯");
		smsParam.setCode("s12312sd");
		phone="15811490643";
		smsService.sendMsg(phone, smsParam);
		model.addAttribute("key1","value1");
		return "login";
	}
}
