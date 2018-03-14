package ssm.library.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ssm.library.mapper.BookMapper;
import ssm.library.mapper.BorrowInfoMapper;
import ssm.library.po.Book;
import ssm.library.po.BorrowInfo;
import ssm.library.po.BorrowInfoUser;
import ssm.library.po.BorrowInfoVo;
import ssm.library.service.BorrowService;

public class BorrowServiceImpl implements BorrowService{
	public final static String IS_BORROWED = "isBorrowed";
	
	@Autowired
	private BorrowInfoMapper borrowInfoMapper;
	
	@Autowired
	private BookMapper bookMapper;
	/*
	 * 借书
	 */
	@Override
	public String borrowBook(BorrowInfo borrowInfo) throws Exception {
		
		//判断当前书本是否已经被借
		BorrowInfo info = borrowInfoMapper.findBorrowInfoByBookId(borrowInfo.getBookId());
		if(info != null)	//已被借
			return IS_BORROWED;
		
		//借书成功
		borrowInfoMapper.insertBorrowInfo(borrowInfo);
		Book book = bookMapper.findBookById(borrowInfo.getBookId());
		book.setState("已被借");
		bookMapper.updateBook(book);
		return book.getBookname();
	}
	
	//根据用户id综合查找借书记录
	@Override
	public List<BorrowInfoUser> findBorrowInfoUserByUserId(Integer id) throws Exception {
		
		List<BorrowInfoUser> borrowInfoUserList = borrowInfoMapper.findBorrowInfoUserByUserId(id);
		
		return borrowInfoUserList;
	}

	//续借书本
	@Override
	public void renewBook(BorrowInfo borrowInfo)throws Exception {
		
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		borrowInfoVo.setBorrowInfo(borrowInfo);
		
		BorrowInfo info = borrowInfoMapper.findBorrowInfoByIds(borrowInfoVo);
		
		//更新续借量
		info.setRenewCount(1);
		
		//更新应还日期
		Calendar cal = Calendar.getInstance();
		cal.setTime(info.getShouldReturnDate());
		cal.add(Calendar.MONTH, 1);
		Date shouldReturnDate = cal.getTime();
		info.setShouldReturnDate(shouldReturnDate);
		borrowInfoVo.setBorrowInfo(info);
		
		borrowInfoMapper.updateBorrowInfoByIds(borrowInfoVo);
	}

	//还书
	@Override
	public void returnBook(BorrowInfo borrowInfo) throws Exception {
		
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		borrowInfoVo.setBorrowInfo(borrowInfo);
		
		//更新设置isReturn为true，设置还书日期
		BorrowInfo info = borrowInfoMapper.findBorrowInfoByIds(borrowInfoVo);
		
		info.setIsReturn(true);
		info.setReturnDate(new Date());
		borrowInfoVo.setBorrowInfo(info);
		
		borrowInfoMapper.updateBorrowInfoByIds(borrowInfoVo);
		
		//更新书本的状态为可借
		Book book = bookMapper.findBookById(info.getBookId());
		book.setState("可借");
		bookMapper.updateBook(book);
	}

	/*
	 * 根据用户id查找借书历史
	 */
	@Override
	public List<BorrowInfoUser> findBorrowHistoryByUserId(Integer id) throws Exception {
		
		return borrowInfoMapper.findBorrowHistoryByUserId(id);
	}

}
