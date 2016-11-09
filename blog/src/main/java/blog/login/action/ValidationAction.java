package blog.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.login.validator.GroupValidate;
import blog.login.validator.GroupsSequenceValidate;
import blog.login.vo.User;

/**
 * 验证器例子
 * 1、使用hibernate验证期进行验证
 * 2、分组验证（有序、无序）
 * 3、通过配置文件配置错误信息(spring-mvc配置文件)
 * 4、自定义验证器
 * 
 * TODO 
 * 校验信息动态展现
 * 能否对非对象参数进行校验
 * 
 * @author wangye
 *
 */
@Controller
@RequestMapping("/validate") 
public class ValidationAction {
	/**
	 * 使用hibernate提供验证器、自定义验证期Min
	 * 配置：1、spring-mvc.xml上配置<mvc:annotation-driven/>
	 * 	   2、下载相应jar包hibernate-validator-5.1.3.Final.jar、
	 * 	      validation-api-1.1.0.Final.jar
	 * 注意：1 校验参数与BindingResult要相邻
	 * 	   2 校验注解写在get方法上
	 * @param user
	 * @param result
	 * @return
	 */
	@RequestMapping("/hibValidate")
	@ResponseBody
	public Map hibernateValidate(@Valid User user, BindingResult result){
		Map errorMsg = new HashMap();
		if (result.hasErrors()) {
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		return errorMsg;
	}
	
	/**
	 * 分组校验
	 * 使用场景：使用相同VO校验规则相同，但校验不同字段
	 * 通过接口分组，接口中不用写任何方法
	 * 注意：校验时valdated没有加分组，那么只校验没有分组的字段
	 * @param user
	 * @param br
	 * @return
	 */
	@RequestMapping("/groupValidate")
	@ResponseBody
	public Map groupValidate(@Validated(value={GroupValidate.class}) User user,
			BindingResult br) {
		Map errorMsg = new HashMap();
		if (br.hasErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		return errorMsg;
	}
	/**
	 * 分组有序校验
	 * 注意：如果前面组验证失败，后面的组将不进行验证
	 * @param user
	 * @param br
	 * @return
	 */
	@RequestMapping("/groupSequence")
	@ResponseBody
	public Map groupSequence(@Validated(value={GroupsSequenceValidate.class}) User user,
			BindingResult br) {
		Map errorMsg = new HashMap();
		if (br.hasErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				errorMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
		}
		return errorMsg;
	}
	
	
}
