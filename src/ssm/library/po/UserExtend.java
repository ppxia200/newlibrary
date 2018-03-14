package ssm.library.po;
/*
 * user类的扩展类
 */
public class UserExtend extends User{

	private int currentBorrowCount;
	private int currentOppoCount;
	
	public UserExtend() {
		super();
	}
	public int getCurrentBorrowCount() {
		return currentBorrowCount;
	}
	public void setCurentBorrowCount(int curentBorrowCount) {
		this.currentBorrowCount = curentBorrowCount;
	}
	public int getCurrentOppoCount() {
		return currentOppoCount;
	}
	public void setCurrentOppoCount(int currentOppoCount) {
		this.currentOppoCount = currentOppoCount;
	}
	
	
}
