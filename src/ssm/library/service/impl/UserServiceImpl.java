package ssm.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ssm.library.mapper.UserMapper;
import ssm.library.po.User;
import ssm.library.po.UserExtend;
import ssm.library.po.UserList;
import ssm.library.po.UserVo;
import ssm.library.service.UserService;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	//根据条件查找用户
	@Override
	public User findUser(User user) throws Exception {
		return userMapper.findUser(user);
	}

	//向数据库中添加用户：注册
	@Override
	public void insertUser(User user) throws Exception {
		userMapper.insertUser(user);
	}

	//根据用户名查找用户
	@Override
	public User findUserByUsername(String username)throws Exception{
		return userMapper.findUserByUsername(username);
	}

	/*
	 *综合查询用户基本信息 
	 */
	@Override
	public UserExtend findUserExtend(User user)throws Exception{
		
		return userMapper.findUserExtend(user);
	}

	/*
	 * 综合查询用户
	 */
	@Override
	public List<User> findUsers(UserVo userVo) throws Exception {
		 
		return userMapper.findUsers(userVo);
	}

	/*
	 * 修改用户信息
	 */
	@Override
	public void editUsers(UserList userList) throws Exception {
		if(userList != null){
			for(User user : userList.getUsers()){
				userMapper.updateUser(user);
			}
		}
		
	}

	/*
	 * 修改用户的身份
	 */
	@Override
	public void setUserIdentity(User user) throws Exception {
		
			userMapper.updateUserIdentity(user);
	}
	
	

}
