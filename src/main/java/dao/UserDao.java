package dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Admin_User;
import po.User_Info;

@Repository("userDao")
@Mapper
public interface UserDao {
	public List getAllUser();
	public void changeUserState(User_Info user);
	public void addUser(User_Info user);
	public User_Info gettUserById(int uid);
	public void editUser(User_Info user);
	public void deleteUser(int uid);
	public void changeUserPassword(int uid, String newpassword);
	public List<User_Info> getDateFindUser(@Param("logMax")String logMax, @Param("logMin")String logMin);

}
