package ssm.library.mapper;

import java.util.List;

import ssm.library.po.BorrowInfo;
import ssm.library.po.BorrowInfoUser;
import ssm.library.po.BorrowInfoVo;

public interface BorrowInfoMapper {
	
	//添加一条借书记录
	public void insertBorrowInfo(BorrowInfo borrowInfo)throws Exception;
	
	//根据id删除一本书
	public void deleteBorrowInfoByIds(BorrowInfoVo borrowInfoVo)throws Exception;
	
	//根据id查找
	public List<BorrowInfo> findBookByUserId(Integer id)throws Exception;
	
	//根据两个id查找
	public BorrowInfo findBorrowInfoByIds(BorrowInfoVo borrowInfoVo)throws Exception;

	//修改书本信息
	public void updateBorrowInfoByIds(BorrowInfoVo borrowInfoVo)throws Exception;
	
	//根据用户id综合查询借书记录（包括book的内容和book_user)的内容）
	public List<BorrowInfoUser> findBorrowInfoUserByUserId(Integer id)throws Exception;

	//根据用户id查找借书历史
	public List<BorrowInfoUser> findBorrowHistoryByUserId(Integer id)throws Exception;

	//根据书本id查找借阅信息
	public BorrowInfo findBorrowInfoByBookId(Integer id)throws Exception;

	//根据书本id查找所有借书记录
	public List<BorrowInfo> findBorrowHistoryByBookId(int id)throws Exception;

	//根据书本id删除借书记录
	public void deleteBorrowInfoByBookId(Integer bookId)throws Exception;

}
