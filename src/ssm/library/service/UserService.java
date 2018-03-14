package ssm.library.service;

import java.util.List;

import ssm.library.po.User;
import ssm.library.po.UserExtend;
import ssm.library.po.UserList;
import ssm.library.po.UserVo;

public interface UserService {
	
	public User findUser(User user)throws Exception;
	
	public void insertUser(User user)throws Exception;

	public User findUserByUsername(String username) throws Exception;

	//综合查询用户基本信息
	public UserExtend findUserExtend(User user)throws Exception;

	/**
	 * 综合查询用户
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	public List<User> findUsers(UserVo userVo)throws Exception;
	
	/**
	 * 修改用户信息
	 * @param userList
	 * @throws Exception
	 */
	public void editUsers(UserList userList)throws Exception;

	/**
	 * 修改用户的身份
	 * @param users
	 * @throws Exception
	 */
	public void setUserIdentity(User user)throws Exception;
}
