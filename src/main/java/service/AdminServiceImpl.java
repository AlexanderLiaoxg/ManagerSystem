package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDao;
import dao.UserDao;
import po.Admin_User;

@Service("adminService")

public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminDao adminDao;
	public Admin_User selectUserById(int admin_id) {
		// TODO Auto-generated method stub
		return adminDao.getAdminById(admin_id);
	}
	public String selectRoleById(int admin_id) {
		// TODO Auto-generated method stub
		return adminDao.getRoleById(admin_id);
	}
	public void addAdmin(Admin_User admin) {
		// TODO Auto-generated method stub
		adminDao.addAdmin(admin);
	}
	public void editAdmin(Admin_User admin) {
		// TODO Auto-generated method stub
		adminDao.editAdmin(admin);
	}
	public void deleteAdmin(int admin_id) {
		// TODO Auto-generated method stub
		adminDao.deleteAdmin(admin_id);
	}
	public void changeAdminPassword(int admin_id, String newpassword) {
		// TODO Auto-generated method stub
		
	}
}
