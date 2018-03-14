package mapper.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.library.mapper.UserMapper;
import ssm.library.po.User;

public class UserMapperTest {
	
	//spring容器
	private ApplicationContext applicationContext;

	//在该方法中得到spring容器
	@Before
	public void setUp() throws Exception {
		applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	}

	@Test
	public void findUserByIdTest() throws Exception {
		
		//mapper的id userMapper是由MapperScannerConfigurer扫描根据mapper名生成的
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		
		User user = userMapper.findUserById(1);
		
		System.out.println(user.getNickname());
	}
	
	
	@Test
	public void insertUserTest()throws Exception{
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		
		User user = new User();
		user.setUsername("111");
		user.setPasswd("000");
		
		userMapper.insertUser(user);
		
	}
	
	@Test
	public void deleteUserByIdTest()throws Exception{
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		userMapper.deleteUserById(5);;
		
	}
	
	@Test
	public void updateUserTest()throws Exception{
		UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
		
		User user = new User();
		user.setIdentity("管理员");
		user.setNickname("xiaoliang");
		user.setUsername("123446");
		user.setId(6);
		
		userMapper.updateUser(user);
		
	}

}
