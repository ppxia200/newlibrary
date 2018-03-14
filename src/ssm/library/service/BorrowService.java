package ssm.library.service;

import java.util.List;

import ssm.library.po.BorrowInfo;
import ssm.library.po.BorrowInfoUser;
import ssm.library.po.BorrowInfoVo;

public interface BorrowService {

	/**
	 * 用户借书
	 * @param bookId
	 * @param user
	 * @throws Exception
	 */
	public String borrowBook(BorrowInfo borrowInfo)throws Exception;

	/**
	 * 根据用户id综合查找借书记录
	 * @param user
	 * @return
	 */
	public List<BorrowInfoUser> findBorrowInfoUserByUserId(Integer id)throws Exception;

	/**
	 * 续借书本，根据主键
	 * @param userId
	 * @param bookId
	 */
	public void renewBook(BorrowInfo borrowInfo)throws Exception;

	/**
	 * 还书
	 * @param borrowInfo
	 * @throws Exception
	 */
	public void returnBook(BorrowInfo borrowInfo)throws Exception;

	/**
	 * 查找借书历史
	 * @param id
	 * @return
	 */
	public List<BorrowInfoUser> findBorrowHistoryByUserId(Integer id)throws Exception;

}
