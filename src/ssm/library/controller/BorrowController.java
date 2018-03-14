package ssm.library.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ssm.library.po.BorrowInfo;
import ssm.library.po.BorrowInfoUser;
import ssm.library.po.BorrowInfoVo;
import ssm.library.po.User;
import ssm.library.service.BorrowService;
import ssm.library.service.impl.BorrowServiceImpl;

/*
 * 该类实现书本的借还
 */

@Controller
public class BorrowController {

	@Autowired
	private BorrowService borrowService;
	
	/**
	 * 借书
	 * @param session
	 * @param bookId
	 * @return
	 * @throws Exception
	 * produces="text/html;charset=UTF-8":解决返回字符串中文乱码
	 */
	@RequestMapping(value="/borrowBook",produces="text/html;charset=UTF-8")
	public @ResponseBody String borrowBook(HttpSession session, BorrowInfo borrowInfo)throws Exception{

		User user = (User) session.getAttribute("user");
		//构建当前借书信息
		borrowInfo.setUserId(user.getId());
		borrowInfo.setIsReturn(false);
		borrowInfo.setBorrowDate(new Date());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);//一个月后的时间
		Date shouldReturnDate = cal.getTime();
		borrowInfo.setShouldReturnDate(shouldReturnDate);
		
		String isBorrowSuccess = borrowService.borrowBook(borrowInfo);
				
		return isBorrowSuccess;	//返回最终借阅操作的信息
	}
	
	/**
	 * 查询我的当前借阅
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryMyBorrow")
	public ModelAndView queryMyBorrow(HttpSession session)throws Exception{
		
		User user = (User) session.getAttribute("user");
		List<BorrowInfoUser> borrowInfoUserList = borrowService.findBorrowInfoUserByUserId(user.getId());
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("borrowInfoUserList", borrowInfoUserList);
		modelAndView.setViewName("users/my_library");
		
		return modelAndView;
	}
	
	/**
	 * 续借当前图书
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/renewBook")
	public @ResponseBody void renewBook(BorrowInfo borrowInfo)throws Exception{
		
		borrowService.renewBook(borrowInfo);
		
	}
	
	/**
	 * 还书
	 * @param borrowInfo
	 * @throws Exception
	 */
	@RequestMapping("/returnBook")
	public @ResponseBody void returnBook(BorrowInfo borrowInfo)throws Exception{
		
		borrowService.returnBook(borrowInfo);
		
	}
	
	/**
	 * 查找借书历史
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryBorrowHistory")
	public @ResponseBody List<BorrowInfoUser> queryBorrowHistory(HttpSession session)throws Exception{
		
		User user = (User) session.getAttribute("user");
		
		List<BorrowInfoUser> borrowHistoryList = borrowService.findBorrowHistoryByUserId(user.getId());
		
		return borrowHistoryList;
	}
	
	
}
