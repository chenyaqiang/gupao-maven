package blog.login.vo;

import java.util.Date;

public class User2 {
    private String id;

    private String[] nameList;
    
    public String[] getNameList() {
		return nameList;
	}

	public void setNameList(String[] nameList) {
		this.nameList = nameList;
	}

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
}