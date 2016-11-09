package blog.login.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import blog.login.service.UserService;
import blog.login.vo.User;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class LoginTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private UserService userService;
	@Test
	public void getUserByIdTest() {
		User user = userService.getUserById("1");
		System.out.println(user);
	}

}
