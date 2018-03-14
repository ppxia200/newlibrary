package ssm.library.mapper;

import java.util.List;

import ssm.library.po.Book;
import ssm.library.po.BookVo;

/*
 * 书本的基本操作
 */
public interface BookMapper {
	
	//添加一本书
	public void insertBook(Book book)throws Exception;
	
	//根据id删除一本书
	public void deleteBookById(Integer id)throws Exception;
	
	//根据id查找
	public Book findBookById(Integer id)throws Exception;

	//修改书本信息
	public void updateBook(Book book)throws Exception;
	
	//根据查询包装类查询书本
	public List<Book> findBooks(BookVo bookVo)throws Exception;
	
}
