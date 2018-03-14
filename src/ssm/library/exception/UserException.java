package ssm.library.exception;
/*
 * 异常处理类
 */
public class UserException extends Exception{
	private String message;		//异常消息

	public UserException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
