package blog.login.vo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import blog.login.validator.GroupValidate;
import blog.login.validator.GroupValidate2;
import blog.login.validator.Min;

public class User {
    private String id;
    private String[] nameList;
   
    private int age;
	private String userName;

    private String userCode;

    private String userPassword;

    private String cookiePassword;

    private String userType;

    private String phoneNumber;

    private Date createTime;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	@NotEmpty(message = "{userName.isEmpty}", groups = {GroupValidate2.class})
	public String getUserName() {
		return userName;
	}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getCookiePassword() {
        return cookiePassword;
    }

    public void setCookiePassword(String cookiePassword) {
        this.cookiePassword = cookiePassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    @Min(value=8, message="{age.min.error}",groups = { GroupValidate.class})
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
    
    public String[] getNameList() {
		return nameList;
	}

	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}
}