package ssm.library.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*
 * 用户异常处理类（全局）
 */
public class UserExceptionHandler implements HandlerExceptionResolver{

	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {
		UserException userException = null;
		
		//判断是否是自定义的错误类型
		if(ex instanceof UserException){
			userException = (UserException) ex;
		}else{
			userException = new UserException("未知错误");
		}
		
		String errorMessage = userException.getMessage();
		
		//将错误信息设置到页面
		ModelAndView  modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", errorMessage);
		modelAndView.setViewName("error");
		
		return modelAndView;
	}
	
}
