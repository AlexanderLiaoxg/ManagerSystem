package controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.UserDao;
import net.sf.json.JSONObject;
import po.User_Info;
import service.UserService;
import util.KMP;
import po.Admin_User;
import po.Information_toUser;

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
	
	@ResponseBody
	@RequestMapping("/toDeleteMembers")
	public JSONObject toDeleteMembers(@RequestParam(value="checked_ids") int[] checked_ids)
	{
		
		for(int i=0 ; i<checked_ids.length; i++)
		{
			userDao.deleteUser(checked_ids[i]);
		}
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@RequestMapping("/toGetDateFindMember")
	public String toGetDateFindMember(Model model,String logMin,String logMax,String name_phone_email) {
		
		List<User_Info> users = userDao.getDateFindUser(logMax, logMin);
		/*截取id和title组成新的MAP信息*/
		Map<Integer,String>names = new HashMap<Integer, String>();
		Map<Integer,String>phones = new HashMap<Integer, String>();
		Map<Integer,String>emails = new HashMap<Integer, String>();
		Iterator<User_Info> iterator = users.iterator();
		 while (iterator.hasNext())
		 {
			 	User_Info user = iterator.next();
			 	names.put(user.getUid(),user.getUname());
			 	phones.put(user.getUid(),user.getUphone());
			 	emails.put(user.getUid(),user.getUemail());
		 }
		 
		KMP kmp_names = new KMP(name_phone_email, names);
		KMP kmp_phones = new KMP(name_phone_email, phones);
		KMP kmp_emails = new KMP(name_phone_email, emails);
		
		List<Integer> admin_ids = kmp_names.Judgement();
		List<Integer> admin_ids_2 = kmp_phones.Judgement();
		List<Integer> admin_ids_3 = kmp_emails.Judgement();
		
		/*利用List和HashSet的特性来去重*/
		admin_ids.addAll(admin_ids_2);
		admin_ids.addAll(admin_ids_3);
		HashSet hash = new HashSet(admin_ids);
		admin_ids.clear();
		admin_ids.addAll(hash);
		System.out.println(admin_ids.toString());
		
		/**根据id返回筛选的结果集**/
		iterator = users.iterator();
		while (iterator.hasNext())
		 {
				User_Info user = iterator.next();
				System.out.print("iterator：" + user.getUname());
			 	if(!admin_ids.contains(user.getUid())) {
			 		iterator.remove();
			 		System.out.print("移除了：" + user.getUname());
			 	}
		 }
		model.addAttribute("users", users);
		return "member-list";
	}
	
}
