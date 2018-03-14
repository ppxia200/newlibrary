package ssm.library.service;

import java.util.List;

import ssm.library.po.OppoInfo;
import ssm.library.po.OppoInfoExtend;

public interface OppoInfoService {
	
	/**
	 * 根据用户id查询预约记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<OppoInfoExtend> findOpposByUserId(Integer id) throws Exception;
	
	/**
	 * 添加预约记录
	 * @param oppoInfo
	 * @throws Exception
	 */
	public String oppoBook(OppoInfo oppoInfo)throws Exception;
	
	/**
	 * 删除一条预约记录
	 * @param oppoInfo
	 * @throws Exception
	 */
	public void cancelOppoInfo(OppoInfo oppoInfo)throws Exception;
}
