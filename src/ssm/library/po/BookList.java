package ssm.library.po;

import java.util.List;

import javax.validation.Valid;
//用于辅助添加图书
public class BookList {
	
	@Valid
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
}
