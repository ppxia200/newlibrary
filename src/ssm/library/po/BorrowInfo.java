package ssm.library.po;

import java.util.Date;

public class BorrowInfo {
	private int id;
	private int bookId;
	private int userId;
	private Date borrowDate;
	private boolean isReturn;
	private Date shouldReturnDate;
	private int renewCount;
	private Date returnDate;
	
	public BorrowInfo() {
		super();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getRenewCount() {
		return renewCount;
	}

	public void setRenewCount(int renewCount) {
		this.renewCount = renewCount;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getShouldReturnDate() {
		return shouldReturnDate;
	}

	public void setShouldReturnDate(Date shouldReturnDate) {
		this.shouldReturnDate = shouldReturnDate;
	}
	
}
