package ssm.library.service;

import java.util.List;

import ssm.library.po.Book;
import ssm.library.po.BookList;
import ssm.library.po.BookVo;

public interface BookService {
	/**
	 * 添加本书
	 * @param book
	 * @throws Exception
	 */
	public void insertBooks(List<Book> book)throws Exception;
	
	/**
	 * 综合查询书本
	 * @param book
	 * @return
	 * @throws Exception
	 */
	public List<Book> findBooks(BookVo bookVo)throws Exception;

	/**
	 * 修改书本信息
	 * @param bookList
	 */
	public void editBooks(BookList bookList)throws Exception;
	
	/**
	 * 删除图书
	 * @param bookVo
	 * @throws Exception
	 */
	public String deleteBook(Book book)throws Exception;
}
