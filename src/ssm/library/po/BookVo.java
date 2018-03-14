package ssm.library.po;
/*
 * 书本的包装类
 */
public class BookVo {
	
	private Book book;
	private String searchType;	//查询类别
	private String searchContent;//输入的查询内容

	public BookVo() {
		super();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	
	
}
