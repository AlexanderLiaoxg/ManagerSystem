package service;

import  java.util.List;

import po.Admin_User;
import po.User_Info;

public interface UserService {
	public User_Info getUserById(int uid);
	public void	changeUserState(User_Info user);
	public void addUser(User_Info user);
	public void editUser(User_Info user);
	public void deleteUser(int uid);
	public void changeUserPassword(int uid, String newpassword);
}
