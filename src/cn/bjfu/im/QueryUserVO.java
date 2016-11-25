package cn.bjfu.im;

public class QueryUserVO {
	
	String usr, pwd;
	
	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public QueryUserVO(String usr, String pwd) {
		this.usr = usr;
		this.pwd = pwd;
	}

	public static void main(String[] args) {

	}

}
