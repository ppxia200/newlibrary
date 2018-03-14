package ssm.library.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import beans.ResponseData;
import ssm.library.po.Book;
import ssm.library.po.BookVo;
import ssm.library.po.User;
import ssm.library.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//根据书名查找书本
	@RequestMapping("/queryBooks")
	public ModelAndView queryBooks(BookVo bookVo)throws Exception{
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
		
		modelAndView.setViewName("books/book_search");
		
		return modelAndView;
		
		
	}
	
	//跳转到读者服务页面
	@RequestMapping("/toBookSearch")
	public String toBookSearch()throws Exception{
		return "books/book_search";
	}
	
	//主页地址
	@RequestMapping("/index")
	public String index()throws Exception{
		return "index";
	}
	
	
}
