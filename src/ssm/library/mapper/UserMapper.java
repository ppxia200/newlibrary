package ssm.library.mapper;

import java.util.List;

import ssm.library.po.User;
import ssm.library.po.UserExtend;
import ssm.library.po.UserVo;

public interface UserMapper {
	
	//插入一条用户记录
	public void insertUser(User user)throws Exception;
	
	//根据id删除一个用户
	public void deleteUserById(Integer id)throws Exception;
	
	//根据id查找用户
	public User findUserById(Integer id)throws Exception;
	
	//根据id修改用户信息
	public void updateUser(User user)throws Exception;
	
	//根据给定条件查找用户
	public User findUser(User user)throws Exception;

	//根据username查找用户
	public User findUserByUsername(String username)throws Exception;

	//综合查询用户基本信息
	public UserExtend findUserExtend(User user)throws Exception;

	//综合查询用户
	public List<User> findUsers(UserVo userVo)throws Exception;

	//修改用户身份
	public void updateUserIdentity(User user)throws Exception;
}
