package ssm.library.mapper;

import java.util.List;

import ssm.library.po.OppoInfo;
import ssm.library.po.OppoInfoExtend;

public interface OppoMapper {
	
	//添加一本书
	public void insertOppoInfo(OppoInfo oppoInfo)throws Exception;
	
	//根据id删除一本书
	public void deleteOppoInfoByIds(OppoInfo oppoInfo)throws Exception;
	
	//根据用户id查找
	public List<OppoInfoExtend> findOppoInfoByUserId(Integer id)throws Exception;
	
	//综合查找：根据书本id查找
	public List<OppoInfoExtend> findOppoInfoByBookId(Integer id)throws Exception;
	
	//根据id查找
	public OppoInfo findOppoInfoByUserIds(OppoInfo oppoInfo)throws Exception;

	//修改书本信息
	public void updateOppoInfo(OppoInfo oppoInfo)throws Exception;
	
	//根据书本信息id查找预约记录
	public List<OppoInfo> findOppoHistoryByBookId(Integer id)throws Exception;
	
}
