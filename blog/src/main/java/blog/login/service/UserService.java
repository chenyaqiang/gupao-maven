package blog.login.service;

import blog.login.vo.User;

public interface UserService {
	public User getUserById(String userCode);
}
