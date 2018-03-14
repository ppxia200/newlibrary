package ssm.library.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ssm.library.po.User;

/*
 * url访问的拦截器
 */
public class ReachInterceptor implements HandlerInterceptor{

		//进入 Handler方法之前执行
		//用于身份认证、身份授权
		//比如身份认证，如果认证通过表示当前用户没有登陆，需要此方法拦截不再向下执行
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
				Object handler) throws Exception {
			
			//若请求管理员的url，
			String url = request.getRequestURI();
			if(url.indexOf("toManager.action") >= 0 || 
					url.indexOf("toEditBook.action") >= 0 || url.indexOf("toEditUser.action") >= 0
					|| url.indexOf("insertBooks.action") >= 0|| url.indexOf("editBooks.action") >= 0 
					|| url.indexOf("queryEditBooks.action") >= 0
					|| url.indexOf("queryEditUsers.action") >= 0 || url.indexOf("deleteBook.action") >= 0
																 || url.indexOf("setManager.action") >= 0){
				User user = (User) request.getSession().getAttribute("user");
				if(user != null && user.getIdentity().equals("管理员"))	//若是管理员，即放行
					return true;
				request.getRequestDispatcher("/WEB-INF/jsp/users/login.jsp").forward(request, response);
				return false;
			}
			
			//需要用户登录才能访问的url
			if(url.indexOf("borrowBook.action") >= 0 || 
					url.indexOf("queryMyBorrow.action") >= 0 || url.indexOf("renewBook.action") >= 0
					|| url.indexOf("returnBook.action") >= 0 || url.indexOf("queryBorrowHistory.action") >= 0
					|| url.indexOf("toMyLibrary.action") >= 0 || url.indexOf("queryMyInfo.action") >= 0
					|| url.indexOf("oppoBook.action") >= 0 || url.indexOf("queryOppos.action") >= 0
					|| url.indexOf("cancelOppoBook.action") >= 0){
				User user = (User) request.getSession().getAttribute("user");
				if(user != null)	//若已登录，即放行
					return true;
				request.getRequestDispatcher("/WEB-INF/jsp/users/login.jsp").forward(request, response);
				return false;
			}
			
			//若请求我的图书馆的url，
			if(url.indexOf("toMyLibrary.action") >= 0){
				User user = (User) request.getSession().getAttribute("user");
				if(user != null)
					return true;
				request.getRequestDispatcher("/WEB-INF/jsp/users/login.jsp").forward(request, response);
				return false;
			}
			
			
			return true;
		}
		
		//进入Handler方法之后，返回modelAndView之前执行
		//应用场景从modelAndView出发：将公用的模型数据(比如菜单导航)在这里传到视图，也可以在这里统一指定视图
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response,
				Object handler, ModelAndView modelAndView)throws Exception {
//			System.out.println("LoginInterceptor......postHandle");
			
		}
		
		//执行Handler完成执行此方法
		//应用场景：统一异常处理，统一日志处理（用于同一日志处理的拦截器应配置在第一个，并且preHandle方法放行）
		@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
				Object handler, Exception ex)throws Exception {
//			System.out.println("LoginInterceptor......afterCompletion");
			
		}
}
