package ssm.library.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.library.po.User;
import ssm.library.po.UserExtend;
import ssm.library.service.UserService;

/*
 * 用户的各类页面操作
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	/**
	 * 跳转到我的图书馆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toMyLibrary")
	public String toMyLibrary()throws Exception{
		return "users/my_library";
	}
	
	/**
	 * 查询用户的基本信息
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryMyInfo")
	public @ResponseBody UserExtend queryMyInfo(HttpSession session)throws Exception{
		
		User user = (User) session.getAttribute("user");
		UserExtend userExtend = userService.findUserExtend(user);
		
		return userExtend;
	}
}
