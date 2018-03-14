package ssm.library.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ssm.library.mapper.OppoMapper;
import ssm.library.po.OppoInfo;
import ssm.library.po.OppoInfoExtend;
import ssm.library.service.OppoInfoService;

public class OppoInfoServiceImpl implements OppoInfoService{

	public final static String IS_OPPOED = "isOppoed";
	public final static String OPPO_SUCCESS = "oppoSuccess";
	
	@Autowired
	private OppoMapper oppoInfoMapper;
	
	/*
	 * 查询用户预约图书
	 */
	@Override
	public List<OppoInfoExtend> findOpposByUserId(Integer id) throws Exception {
		
		List<OppoInfoExtend> oppos = oppoInfoMapper.findOppoInfoByUserId(id);
		
		return oppos;
	}

	/*
	 * 添加预约记录
	 */
	@Override
	public String oppoBook(OppoInfo oppoInfo) throws Exception {
		
		List<OppoInfoExtend> infos = 
				oppoInfoMapper.findOppoInfoByBookId(oppoInfo.getBookId());
		
		if(infos != null && infos.size() > 0)		//已被预约
			return IS_OPPOED;
		
		oppoInfoMapper.insertOppoInfo(oppoInfo);
		return OPPO_SUCCESS;
	}

	/*
	 * 删除预约记录
	 */
	@Override
	public void cancelOppoInfo(OppoInfo oppoInfo) throws Exception {
		
		oppoInfoMapper.deleteOppoInfoByIds(oppoInfo);
	}
	
}
