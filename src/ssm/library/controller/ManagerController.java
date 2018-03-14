package ssm.library.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.library.po.Book;
import ssm.library.po.BookList;
import ssm.library.po.BookVo;
import ssm.library.po.User;
import ssm.library.po.UserList;
import ssm.library.po.UserVo;
import ssm.library.service.BookService;
import ssm.library.service.UserService;
import ssm.library.service.impl.BookServiceImpl;

/*
 * 管理员操作
 */
@Controller
public class ManagerController {
	
	public final static String INPUT_ERROR = "inputError";
	public final static String NO_INPUT = "noInput";
	public final static String INSERT_SUCCESS = "insertSuccess";

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	/**
	 * 跳转到管理员页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toManager")
	public String toManager()throws Exception{
		return "users/manager_add_book";
	}
	@RequestMapping("/toEditBook")
	public String toEditBook()throws Exception{
		return "users/manager_edit_book";
	}
	@RequestMapping("/toEditUser")
	public String toEditUser()throws Exception{
		return "users/manager_edit_user";
	}
	
	/**
	 * 判断是否跳转到管理员页面
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/isToMananger")
	public @ResponseBody String isToMananger(HttpSession session)throws Exception{
		User user = (User) session.getAttribute("user");
		if(user == null)	//为登录
			return "noLogin";
		if(!user.getIdentity().equals("管理员"))
			return "noManager";	//非管理员
		
		return "isManager";	//是管理员
	}
	/**
	 * 添加书本
	 * @param bookList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/insertBooks", produces="text/html; charset=UTF-8")
	public @ResponseBody String insertBooks(BookList bookList)throws Exception{
		
		//没有书本信息1
		if(bookList.getBooks() == null)	
			return NO_INPUT;
		//去除已经删除的行
		for(Book book : bookList.getBooks()){		
			if(book.getAddress() == null)
				bookList.getBooks().remove(book);
			else
				book.setState("可借"); 	//默认书本状态为可借
		}
		//判断有无输入是空格的
		for(Book book : bookList.getBooks()){
			if(book.getBookname().trim().equals("") || book.getAddress().trim().equals("") ||
					book.getCompany().trim().equals("") || book.getVersion().trim().equals("") ||
					book.getWriter().trim().equals("")){
				return INPUT_ERROR;
			}
		}
		
		//添加书本
		bookService.insertBooks(bookList.getBooks());
		return INSERT_SUCCESS;
	}
	
	/**
	 * 提交修改
	 * @param bookList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editBooks", produces="text/html; charset=utf-8")
	public @ResponseBody String editBooks(Model model,@Validated BookList bookList, BindingResult bindingResult)throws Exception{
		
		if(bindingResult.hasErrors()){
			return "inputError";
		}
		
		bookService.editBooks(bookList);
		
		model.addAttribute("bookList", null);
		return "成功修改"+bookList.getBooks().size()+"条记录";
	}
	
	/**
	 * 查询要编辑的书本
	 * @param bookVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryEditBooks")
	public ModelAndView queryEditBooks(BookVo bookVo)throws Exception{
		
		//设置查询的内容
		Book book = new Book();
		bookVo.setBook(book);
		if(bookVo.getSearchType() == null){	//没有输入查询内容
			
		}else{
			if(bookVo.getSearchType().equals("书名")){
				bookVo.getBook().setBookname(bookVo.getSearchContent());
			}else if(bookVo.getSearchType().equals("作者")){
				bookVo.getBook().setWriter(bookVo.getSearchContent());
			}else if(bookVo.getSearchType().equals("索书号")){
				bookVo.getBook().setAddress(bookVo.getSearchContent());
			}else{
				bookVo.getBook().setCompany(bookVo.getSearchContent());
			}
		}
		
		List<Book> bookList = bookService.findBooks(bookVo);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("bookList",bookList);
		modelAndView.setViewName("users/manager_edit_book");
		return modelAndView;
	}
	
	/**
	 * 查询需要修改信息的用户
	 * @param userVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryEditUsers")
	public ModelAndView queryEditUsers(UserVo userVo)throws Exception{
		//设置查询的内容
		User user = new User();
		userVo.setUser(user);
		if(userVo.getSearchType() == null){	//没有输入查询内容
			
		}else{
			if(userVo.getSearchType().equals("用户名")){
				userVo.getUser().setNickname(userVo.getSearchContent());
			}else{
				userVo.getUser().setUsername(userVo.getSearchContent());
			}
		}
		
		List<User> userList = userService.findUsers(userVo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList",userList);
		modelAndView.setViewName("users/manager_edit_user");
		return modelAndView;
	}
	
	/**
	 * 编辑用户信息
	 * @param model
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editUsers")
	public String editUsers(Model model, UserList userList)throws Exception{
		
		userService.editUsers(userList);
		model.addAttribute("editSuccessTip", "成功修改"+userList.getUsers().size()+"条记录");
		model.addAttribute("userList", null);
		return "forward:toEditUser.action";
	}
	
	/**
	 * 删除图书
	 * @param book
	 * @throws Exception
	 */
	@RequestMapping("/deleteBook")
	public @ResponseBody String deleteBook(Book book)throws Exception{
		
		String flag = bookService.deleteBook(book);
		if(flag.equals(BookServiceImpl.IS_BORROWED))	//被借，不可删除
			return "isBorrowed";
		else
			return "deleteSuccess";	//删除成功
	}
	
	/**
	 * 将用户提升为管理员
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setManager")
	public @ResponseBody void setManager(User user)throws Exception{
		user.setIdentity("管理员");
		userService.setUserIdentity(user);
	}
}
