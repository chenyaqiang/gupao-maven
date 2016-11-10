package blog.base.sms.vo;

import javax.management.ImmutableDescriptor;

import com.google.common.collect.ImmutableMap;

/**
 * 发送短信内容参数
 * @author wangye
 *
 */
public class SmsParam {
	String blog;
	String code;
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		String string = ImmutableMap.of("blog",blog,"code",code).toString();
		string=string.replace("{", "{\\\"").replace("}", "\\\"}")
					 .replace("=", "\\\":\\\"").replace(",", "\\\",\\\"")
					 .replaceAll(" ","");
		return string;
	}
	
	
}
