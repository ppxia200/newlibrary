package ssm.library.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ssm.library.mapper.BookMapper;
import ssm.library.mapper.BorrowInfoMapper;
import ssm.library.mapper.OppoMapper;
import ssm.library.po.Book;
import ssm.library.po.BookList;
import ssm.library.po.BookVo;
import ssm.library.po.BorrowInfo;
import ssm.library.po.OppoInfo;
import ssm.library.service.BookService;

public class BookServiceImpl implements BookService{
	
	public final static String IS_BORROWED = "isBorrowed";
	public final static String DELETE_SUCCESS = "deleteSuccess";
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private BorrowInfoMapper borrowInfoMapper;
	
	@Autowired
	private OppoMapper oppoMapper;

	//综合查找书本
	@Override
	public List<Book> findBooks(BookVo bookVo) throws Exception {
		
		return bookMapper.findBooks(bookVo);
	}

	/*
	 *添加本书 
	 */
	@Override
	public void insertBooks(List<Book> books) throws Exception {
		for(Book book : books)
			bookMapper.insertBook(book);
	}

	/*
	 * 修改书本信息
	 */
	@Override
	public void editBooks(BookList bookList) throws Exception {
		if(bookList.getBooks() != null){
			for(Book book : bookList.getBooks()){
				bookMapper.updateBook(book);
			}
		}
	}

	/*
	 * 删除图书
	 */
	@Override
	public String deleteBook(Book book) throws Exception {
		
		BorrowInfo borrowInfo = borrowInfoMapper.findBorrowInfoByBookId(book.getId());
		if(borrowInfo != null)	//该书处于借阅状态，不可删除
			return IS_BORROWED;
		
		//清除有关该书的所有记录:包括借阅历史记录和预约历史记录（除了处于借阅中的）
		List<BorrowInfo> borrowInfos = borrowInfoMapper.findBorrowHistoryByBookId(book.getId());
		for(BorrowInfo info : borrowInfos){
			borrowInfoMapper.deleteBorrowInfoByBookId(info.getBookId());
		}
		List<OppoInfo> oppos = oppoMapper.findOppoHistoryByBookId(book.getId());
		for(OppoInfo oppoInfo : oppos){
			oppoMapper.deleteOppoInfoByIds(oppoInfo);
		}
		//删除book中的相关记录
		bookMapper.deleteBookById(book.getId());//删除成功
		return DELETE_SUCCESS;
	}
	
}
