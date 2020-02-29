package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;
import net.sf.json.JSONObject;
import po.User_Info;
import service.UserService;
import po.Admin_User;

@Controller
public class UserController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserService userService;
	
	@ResponseBody
	/**ResponseBody注解返回Json数据**/
	@RequestMapping("/toChangeUserState")
	public JSONObject toChangeUserState(User_Info user,Model model)
	{
		userService.changeUserState(user);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toAddUser")
	public JSONObject toAddUser(User_Info user,Model model)
	{
		System.out.println("传进来了toAddUer"+user.toString());
		userService.addUser(user);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toEditUser")
	public JSONObject toEditUser(User_Info user,Model model)
	{
		System.out.println("传进来了toEditUser"+user.toString());
		userService.editUser(user);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toDeleteUser")
	public JSONObject toDelete(User_Info user,Model model)
	{

		userService.deleteUser(user.getUid());
		System.out.println("传进来了toDelete"+user.getUid());
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toChangeUserPassword")
	public JSONObject toChangeUserPassword(String newpassword,int uid,Model model)
	{

		userService.changeUserPassword(uid,newpassword);
		System.out.println("传进来了toDelete"+uid + "password" + newpassword);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
}
