package ssm.library.controller;

import java.util.Date;
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
import ssm.library.validation.RegistValidation;

@Controller
public class RegistController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regist")
	public String regist(Model model, HttpSession session, @Validated(value={RegistValidation.class})User user, 
			BindingResult bindingResult, String confirmPass, String inputVercode)throws Exception{
		
		//判断验证码输入是否正确(附带数据回显)
		String vercode = (String) session.getAttribute("code");
		if(inputVercode == null || !inputVercode.equalsIgnoreCase(vercode)){
			model.addAttribute("user", user);
			model.addAttribute("vercoeError", "验证码输入有误");
			model.addAttribute("confirmPass",confirmPass);
			return "users/regist";
		}
		
		//判断输入参数是否正确
		if(bindingResult.hasErrors()){
			model.addAttribute("confirmPass",confirmPass);
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for(FieldError err : fieldErrors)
				model.addAttribute(err.getField(), err.getDefaultMessage());
			
			return "users/regist";
		}
		
		//判断两次密码输入是否一致
		if(confirmPass == null || !confirmPass.equals(user.getPasswd())){
			model.addAttribute("user", user);
			model.addAttribute("confirmPass",confirmPass);
			model.addAttribute("comfirmPassError", "两次密码输入不一致");
			return "users/regist";
		}
		
		//判断用户是否已经存在
		User getUser = userService.findUserByUsername(user.getUsername());
		if(getUser != null){
			model.addAttribute("user", user);
			model.addAttribute("confirmPass",confirmPass);
			model.addAttribute("userExistError", "该账号用户已经存在");
			return "users/regist";
		}
		
		//注册成功
		user.setIdentity("学生");//默认用户身份
		user.setMaxBorrow(6);	//默认最大借阅量
		user.setMaxOppo(1);	//默认最大预约量
		user.setCreateTime(new Date());
		userService.insertUser(user);
		model.addAttribute("username", user.getUsername());
		return "users/regist_success";
		
	}
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/toRegist")
	public String toRegist(){
		return "users/regist";
	}

}
