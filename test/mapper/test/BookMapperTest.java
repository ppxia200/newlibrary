package mapper.test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ssm.library.mapper.BookMapper;
import ssm.library.po.Book;
import ssm.library.po.BookVo;

public class BookMapperTest {

	ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext = new 
				ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	}

	@Test
	public void test() throws Exception {
		BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
		
		//Book book = bookMapper.findBookById(1);
		
		Book book = bookMapper.findBookById(1);
		
		book.setCompany("红帽出版社");
		book.setAddress("A201");
		
		bookMapper.updateBook(book);
		
		System.out.println(book.getBookname());
	}
	
	@Test
	public void updateBookTest() throws Exception {
		BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
		
		//Book book = bookMapper.findBookById(1);
		
		Book book = bookMapper.findBookById(1);
		
		book.setCompany("草帽出版社");
		book.setAddress("A201");
		book.setId(0);
		bookMapper.insertBook(book);
		
//		System.out.println(book.getBookname());
	}
	
	@Test
	public void findBooksTest() throws Exception {
		BookMapper bookMapper = (BookMapper) applicationContext.getBean("bookMapper");
		
		Book book = new Book();
		book.setBookname("红");
		book.setCompany("s");
		BookVo bookVo = new BookVo();
		bookVo.setBook(book);
		
		List<Book> findBooks = bookMapper.findBooks(bookVo);
		System.out.println(findBooks.get(0).getBookname());
	}
	
	

}
