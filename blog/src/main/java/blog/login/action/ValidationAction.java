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
 * ��֤������
 * 1��ʹ��hibernate��֤�ڽ�����֤
 * 2��������֤����������
 * 3��ͨ�������ļ����ô�����Ϣ(spring-mvc�����ļ�)
 * 4���Զ�����֤��
 * 
 * TODO 
 * У����Ϣ��̬չ��
 * �ܷ�ԷǶ����������У��
 * 
 * @author wangye
 *
 */
@Controller
@RequestMapping("/validate") 
public class ValidationAction {
	/**
	 * ʹ��hibernate�ṩ��֤�����Զ�����֤��Min
	 * ���ã�1��spring-mvc.xml������<mvc:annotation-driven/>
	 * 	   2��������Ӧjar��hibernate-validator-5.1.3.Final.jar��
	 * 	      validation-api-1.1.0.Final.jar
	 * ע�⣺1 У�������BindingResultҪ����
	 * 	   2 У��ע��д��get������
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
	 * ����У��
	 * ʹ�ó�����ʹ����ͬVOУ�������ͬ����У�鲻ͬ�ֶ�
	 * ͨ���ӿڷ��飬�ӿ��в���д�κη���
	 * ע�⣺У��ʱvaldatedû�мӷ��飬��ôֻУ��û�з�����ֶ�
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
	 * ��������У��
	 * ע�⣺���ǰ������֤ʧ�ܣ�������齫��������֤
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
