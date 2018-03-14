package ssm.library.po;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ssm.library.validation.LoginValidation;
import ssm.library.validation.RegistValidation;

public class User {
	private int id;
	private int maxBorrow;
	private int maxOppo;
	private Date createTime;
	
	@Pattern(regexp="^[0-9]{12}$", message="{regist.username.error}", groups={RegistValidation.class})
	@Size(min=1,message="{login.username.null}", groups={LoginValidation.class})
	private String username;
	
	@Pattern(regexp="^[A-Za-z0-9]{6,10}$", message="{regist.passwd.error}", groups={RegistValidation.class})
	@Size(min=1,message="{login.passwd.null}", groups={LoginValidation.class, RegistValidation.class})
	private String passwd;
	
	@Size(min=1, max=12, message="{regist.nickname.error}", groups={RegistValidation.class})
	private String nickname;
	private String identity;
	private String pic;
	
	
	public User() {
		super();
	}
	
	public int getMaxBorrow() {
		return maxBorrow;
	}

	public void setMaxBorrow(int maxBorrow) {
		this.maxBorrow = maxBorrow;
	}

	public int getMaxOppo() {
		return maxOppo;
	}

	public void setMaxOppo(int maxOppo) {
		this.maxOppo = maxOppo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

}
