package blog.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Value;

import blog.login.service.UserService;
import blog.login.validator.GroupValidate;
import blog.login.vo.User;
@Controller  
@RequestMapping("/user")  
public class LoginAction {
	@Resource
	private UserService userService;

	/**
	 * 通过model向前台传递数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String toIndex(HttpServletRequest request, Model model) {
		model.addAttribute("key1","value1");
		return "login";
	}
	
	/**
	 * 与ajax交互，前台传递的参数自动封住到对象中，数组要特殊处理
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody
	Map login(Model model, HttpServletRequest request, User user,
			BindingResult result, @RequestParam("names[]") List names) {
		Map map = new HashMap();
		map.put("msg", user.getUserName() + ":" + names);
		return map;
	}
	
	
	
	
}
