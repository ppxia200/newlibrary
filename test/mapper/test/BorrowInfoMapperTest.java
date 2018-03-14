package mapper.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.library.mapper.BorrowInfoMapper;
import ssm.library.po.Book;
import ssm.library.po.BorrowInfo;
import ssm.library.po.BorrowInfoVo;
import ssm.library.po.User;

public class BorrowInfoMapperTest {

	private ApplicationContext applicationContext;
	
	@Before
	public void setUp() throws Exception {
		applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	}

	@Test
	public void test() throws Exception {
		BorrowInfoMapper mapper = (BorrowInfoMapper) applicationContext.getBean("borrowInfoMapper");
		
//		BorrowInfo borrowInfo = new BorrowInfo();
//		borrowInfo.setBookId(1);
//		borrowInfo.setUserId(1);
//		borrowInfo.setBorrowDate(new Date());
//		borrowInfo.setShouldReturnDate(new Date());
//		mapper.insertBorrowInfo(borrowInfo);
		
//		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
//		BorrowInfo borrowInfo = new BorrowInfo();
//		borrowInfo.setBookId(2);
//		borrowInfo.setUserId(1);
//		borrowInfoVo.setBorrowInfo(borrowInfo);
//		
//		BorrowInfo info = mapper.findBookByIds(borrowInfoVo);
		
//		List<BorrowInfo> list = mapper.findBookByUserId(1);
//		System.out.println(info.getBookId());
	}
	
	@Test
	public void updateBorrowInfoTest()throws Exception{
		
		BorrowInfoMapper mapper = (BorrowInfoMapper) applicationContext.getBean("borrowInfoMapper");
		
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		BorrowInfo info = new BorrowInfo();
		info.setBookId(2);
		info.setUserId(1);
		borrowInfoVo.setBorrowInfo(info);
		
		BorrowInfo borrowInfo = mapper.findBorrowInfoByIds(borrowInfoVo);
		
//		borrowInfo.setIsReturn(true);
//		borrowInfo.setBorrowDate(new Date());
//		borrowInfo.setShouldReturnDate(new Date());
//		borrowInfoVo.setBorrowInfo(borrowInfo);
//		mapper.updateBorrowInfoByIds(borrowInfoVo);
		
	}
	
	@Test
	public void deleteBorrowInfoTest()throws Exception{
		BorrowInfoMapper mapper = (BorrowInfoMapper) applicationContext.getBean("borrowInfoMapper");
		
		BorrowInfoVo borrowInfoVo = new BorrowInfoVo();
		BorrowInfo borrowInfo = new BorrowInfo();
		borrowInfo.setBookId(1);
		borrowInfo.setUserId(1);
		borrowInfoVo.setBorrowInfo(borrowInfo);
		mapper.deleteBorrowInfoByIds(borrowInfoVo);
	}

}
