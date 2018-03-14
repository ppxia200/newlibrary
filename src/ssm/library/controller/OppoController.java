package ssm.library.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.library.po.OppoInfo;
import ssm.library.po.OppoInfoExtend;
import ssm.library.po.User;
import ssm.library.service.OppoInfoService;
import ssm.library.service.impl.OppoInfoServiceImpl;

/*
 * 预约的处理类
 */
@Controller
public class OppoController {

	@Autowired
	private OppoInfoService oppoInfoService;
	/**
	 * 预约书本
	 * @param session
	 * @param borrowInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/oppoBook",produces="text/html;charset=UTF-8")
	public @ResponseBody String oppoBook(HttpSession session, OppoInfo oppoInfo)throws Exception{
		
		User user = (User) session.getAttribute("user");
		oppoInfo.setUserId(user.getId());
		
		//设置日期
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date endDate = cal.getTime();
		oppoInfo.setOppoDate(new Date());
		oppoInfo.setEndDate(endDate);
		
		String result = oppoInfoService.oppoBook(oppoInfo);
		
		if(result.equals(OppoInfoServiceImpl.IS_OPPOED))
			return "该书已被预约，请下次再预约";
		
		return "预约成功";
		
	}
	
	/**
	 * 查询预约记录
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryOppos")
	public @ResponseBody List<OppoInfoExtend> queryOppos(HttpSession session)throws Exception{
		
		User user = (User) session.getAttribute("user");
		
		List<OppoInfoExtend> oppoList = oppoInfoService.findOpposByUserId(user.getId());
		return oppoList;
	}
	
	/**
	 * 取消预约
	 * @param session
	 * @param borrowInfo
	 * @throws Exception
	 */
	@RequestMapping("/cancelOppoBook")
	public @ResponseBody void cancelOppoBook(HttpSession session, OppoInfo oppoInfo)throws Exception{
		
		User user = (User) session.getAttribute("user");
		oppoInfo.setUserId(user.getId());
		oppoInfoService.cancelOppoInfo(oppoInfo);
	}
	
}
