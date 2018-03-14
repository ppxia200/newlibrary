package ssm.library.po;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Book {
	
	private int id;
//	@Size(min=1, message="{insert.book.bookname.error}")
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.bookname.error}")
	private String bookname;
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.writer.error}")
	private String writer;
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.version.error}")
	private String version;
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.company.error}")
	private String company;
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.address.error}")
	private String address;
	@Pattern(regexp="^.*[^\\s]+.*$",message="{edit.book.state.error}")
	private String state;
	
	
	
	public Book() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
