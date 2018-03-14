package ssm.library.po;

import java.util.Date;

/*
 * 预约信息
 */
public class OppoInfo {
	private int userId;
	private int bookId;
	private Date oppoDate;
	private Date endDate;
	
	
	public OppoInfo() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getOppoDate() {
		return oppoDate;
	}
	public void setOppoDate(Date oppoDate) {
		this.oppoDate = oppoDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
