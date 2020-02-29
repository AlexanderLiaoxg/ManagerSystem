package po;

import java.util.Date;

public class User_Info {
	private int uid;
	private String uname;
	private String usex;
	private String uphone;
	private String uemail;
	private String uaddress;
	private Date ucreate_time;
	private String ustate;
	public int getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public Date getUcreate_time() {
		return ucreate_time;
	}
	public void setUcreate_time(Date ucreat_time) {
		this.ucreate_time = ucreat_time;
	}
	public String getUstate() {
		return ustate;
	}
	public void setUstate(String state) {
		this.ustate = state;
	}
	@Override
	public String toString() {
		return "User_Info [uid=" + uid + ", uname=" + uname + ", usex=" + usex + ", uphone=" + uphone + ", uemail="
				+ uemail + ", uaddress=" + uaddress + ", ucreat_time=" + ucreate_time + ", state=" + ustate + "]";
	}
	
}
