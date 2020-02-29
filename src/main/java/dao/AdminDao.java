package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Admin_User;
import po.User_Info;

@Repository("adminDao")
@Mapper
public interface AdminDao {

	public List getAllAdmin();
	public Admin_User getAdminById(int admin_id);
	public String getRoleById(int admin_id);
	public void addAdmin(Admin_User admin);
	public void editAdmin(Admin_User admin);
	public void deleteAdmin(int admin_id);
	public void changeAdminPassword(int admin_id, String newpassword);
	public void changeAdminState(Admin_User admin);
	public int getAdminUnReadInformation(int admin_id);

}
