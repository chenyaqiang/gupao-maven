package blog.shell.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blog.shell.service.ShellService;

@Controller
@RequestMapping("/shell") 
public class ShellAction {
	@Autowired
	private ShellService shellService;
	/**
	 * 通过model向前台传递数据
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/test")
	public String test(HttpServletRequest request, Model model) throws Exception {
		String result=null;
		result = shellService.test();
		
		model.addAttribute("test",result);
		return "shell";
	}
}
