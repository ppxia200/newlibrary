package ssm.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import ssm.library.po.User;
import ssm.library.service.UserService;
import ssm.library.validation.LoginValidation;

/*
 * 用户的操作
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @param session 可用于保存用户的登录信息
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	//指定登录时的校验规则
	//model用于显示错误信息(同时进行数据回显)
	@RequestMapping("/login")
	public String login(Model model,HttpSession session, @Validated(value={LoginValidation.class})
		User queryUser, BindingResult bindingResult,String inputVercode)throws Exception{
		
		String vercode = (String) session.getAttribute("code");//获取系统中的验证码	
		//判断验证码是否正确
		if(inputVercode == null || !inputVercode.equalsIgnoreCase(vercode)){	//验证码出错	
			model.addAttribute("user", queryUser);//用于数据回显
			model.addAttribute("inputVercode", inputVercode);
			model.addAttribute("vercodeError", "验证码输入错误");
			return "users/login";
		}
		
		if(bindingResult.hasErrors()){		//账号或密码没输入
			model.addAttribute("inputVercode", inputVercode);
			model.addAttribute("user", queryUser);//用于数据回显
			//解析错误
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for(FieldError err : fieldErrors){
				model.addAttribute(err.getField(), err.getDefaultMessage());
			}
			return "users/login";
		}
		
		User user = userService.findUser(queryUser);
		if(user != null){	//登录成功：登录的用户存在
			session.setAttribute("user", user);
			return "redirect:/index.action";
		}else{		//登录失败
			model.addAttribute("loginError", "账号或密码错误");
			return "users/login";
		}
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session)throws Exception{
		//清除用户信息
		session.invalidate();
		return "redirect:/index.action";
	}
	
	/**
	 * 跳转到登录界面的连接
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toLogin")
	public String toLogin()throws Exception{
		return "users/login";
	}
	
}
