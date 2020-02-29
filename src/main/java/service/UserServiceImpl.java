package service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import po.Admin_User;
import po.User_Info;

@Service("userService")

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	public User_Info getUserById(int uid) {
		// TODO Auto-generated method stub
		return userDao.gettUserById(uid);
	}
	public void changeUserState(User_Info user) {
		// TODO Auto-generated method stub
		System.out.print("service success");
		userDao.changeUserState(user);
	}
	public void addUser(User_Info user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}
	public void editUser(User_Info user) {
		// TODO Auto-generated method stub
		userDao.editUser(user);
	}
	public void deleteUser(int uid) {
		// TODO Auto-generated method stub
		userDao.deleteUser(uid);
	}
	public void changeUserPassword(int uid, String newpassword) {
		// TODO Auto-generated method stub
		
	}

}
