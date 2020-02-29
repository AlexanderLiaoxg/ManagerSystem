package controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import dao.AdminDao;
import net.sf.json.JSONObject;
import po.Admin_User;
import po.User_Info;
import service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private  AdminDao adminDao;
	@Autowired
	private  AdminService adminService;
	
	@RequestMapping("/login")
	//RedirectAttributes
	public String login(Admin_User admin,Model model,HttpServletRequest request) {

		/**
		 * 使用shiro编写认证操作
		 */
		//1.获取Subject
		Subject subject = SecurityUtils.getSubject();
		
		//2.封装用户数据
		/**
		 * lxg:我这里是用id登陆，封装到token里面，以后可以尝试多种方式传输（登陆）
		 */
		System.out.print("controller中：" + admin.getAdmin_id() + admin.getAdmin_password());
		UsernamePasswordToken token = new UsernamePasswordToken(Integer.toString(admin.getAdmin_id()),admin.getAdmin_password());
		//3.执行登陆方法
		try {
			subject.login(token);
		} catch(UnknownAccountException e) {
			//登陆失败，用户名不存在
			model.addAttribute("msg","用户名不存在");
			return "redirect:/toLogin";   /*重定向controller*/
		}catch(IncorrectCredentialsException e) {
			//登陆失败：密码错误
			model.addAttribute("msg","密码错误");
			return "redirect:/toLogin";
		}
		//*session记录登陆用户*//
		HttpSession session = request.getSession();
		session.setAttribute("this_id",admin.getAdmin_id() );
		admin = adminDao.getAdminById(admin.getAdmin_id());
		session.setAttribute("this_name",admin.getAdmin_name());
		int unReadNums = adminDao.getAdminUnReadInformation(admin.getAdmin_id());
		session.setAttribute("this_unReadNums", unReadNums);
		String role = adminDao.getRoleById(admin.getAdmin_id());
		session.setAttribute("this_role",role);
		System.out.println("session 记录了名字：" + admin.getAdmin_name() + "     角色：" + role + "       id：" + admin.getAdmin_id());
		/*存储未读*/
		/*临时去前后缀：forward和redirection*/
		return "index";
	
	}
	
	@ResponseBody
	@RequestMapping("/toAddAdmin")
	public JSONObject toAddAdmin(Admin_User admin,Model model)
	{
		System.out.println("传进来了toAddAdmin"+admin.toString());
		adminService.addAdmin(admin);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toDeleteAdmin")
	public JSONObject toDeleteAdmin(int admin_id,Model model)
	{
		System.out.println("传进来了toDeleteAdmin"+admin_id);
		adminService.deleteAdmin(admin_id);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toEditAdmin")
	public JSONObject toEditAdmin(Admin_User admin,Model model)
	{
		System.out.println("传进来了toEditAdmin"+admin.toString());
		adminService.editAdmin(admin);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toChangeAdminState")
	public JSONObject toChangeAdminState(Admin_User admin,Model model)
	{
		adminDao.changeAdminState(admin);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
}
