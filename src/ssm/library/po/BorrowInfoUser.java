package ssm.library.po;

import java.util.Date;

//借书记录的扩展类
public class BorrowInfoUser extends BorrowInfo{
	
	private String bookname;
	private String writer;
	private String company;
	private String address;
	private String state;
	
	
	public BorrowInfoUser() {
		super();
	}
	
	
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
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
	
	
}
