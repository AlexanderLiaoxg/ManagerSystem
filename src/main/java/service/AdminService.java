package service;

import org.springframework.stereotype.Service;

import po.Admin_User;

public interface AdminService {
	public Admin_User selectUserById(int admin_id);
	public String selectRoleById(int admin_id);
	public void addAdmin(Admin_User admin);
	public void editAdmin(Admin_User admin);
	public void deleteAdmin(int admin_id);
	public void changeAdminPassword(int admin_id, String newpassword);

}
