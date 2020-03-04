package po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Admin_User {
	private int admin_id;
	private int admin_role_id;
	private String admin_name;
	private String admin_phone;
	private String admin_password;
	private String admin_email;
	private String admin_role;
	private String admin_state;
	private Date admin_create_time;
	SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_phone() {
		return admin_phone;
	}
	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public String getAdmin_role() {
		return admin_role;
	}
	public void setAdmin_role(String admin_role) {
		this.admin_role = admin_role;
	}
	public String getAdmin_state() {
		return admin_state;
	}
	public void setAdmin_state(String admin_state) {
		this.admin_state = admin_state;
	}
	public String getAdmin_create_time() {
		return format.format(admin_create_time);
	}
	public void setAdmin_create_time(Date admin_create_time) {
		this.admin_create_time = admin_create_time;
	}
	public int getAdmin_role_id() {
		return admin_role_id;
	}
	public void setAdmin_role_id(int admin_role_id) {
		this.admin_role_id = admin_role_id;
	}
	@Override
	public String toString() {
		return "Admin_User [admin_id=" + admin_id + ", admin_name=" + admin_name + ", admin_phone=" + admin_phone
				+ ", admin_password=" + admin_password + ", admin_email=" + admin_email + ", admin_role=" + admin_role
				+ ", admin_state=" + admin_state + ", admin_create_time=" + admin_create_time + "]";
	}
	
	
}
