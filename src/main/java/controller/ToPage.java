package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.cj.xdevapi.JsonArray;

import dao.AdminDao;
import dao.ResourceDao;
import dao.UserDao;
import net.sf.json.JSONArray;
import po.Admin_User;
import po.Information_toUser;
import po.ResourceCategory;
import po.Resource_Info;
import po.User_Info;

@Controller
public class ToPage {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	@RequestMapping("/toLogin")
	public String toLogin(Model model,String msg) {
		model.addAttribute("msg",msg);
		System.out.print(msg+"进入tologin");
		return "login";
	}

	@RequestMapping("/toPage")
	public String toPage(HttpServletRequest request, Model model) {
		String url = request.getParameter("url");
		System.out.println("跳转到" + url);

		model.addAttribute("msg", "test");
		return url;
	}

	@RequestMapping("/member-list")
	public String memberList(Model model) {
		List users = userDao.getAllUser();
		System.out.println(users);
		System.out.println("test: " + ((User_Info) users.get(0)).getUname());
		model.addAttribute("users", users);
		return "member-list";
	}

	@RequestMapping("/member-show")
	public String toMemberShow(Model model) {
		return "member-show";
	}

	@RequestMapping("/change-password")
	public String toChangePassword(Model model) {
		return "change-password";
	}

	@RequestMapping("/member-add")
	public String toMemberAdd(Model model) {
		return "member-add";
	}

	@RequestMapping("/member-del")
	public String toMemberDel(Model model) {
		return "member-del";
	}

	@RequestMapping("/member-edit")
	public String toMemberEdit(Model model) {
		return "member-edit";
	}

	/* super-admin */
	@RequestMapping("/refuse")
	public String toRefuse() {
		return "refuse";
	}

	@RequestMapping("/admin-list")
	public String adminList(Model model) {
		List admins = adminDao.getAllAdmin();
		System.out.println("进入到admin_list");

		Iterator<Admin_User> iterator = admins.iterator();
		while (iterator.hasNext()) {
			Admin_User admin = iterator.next();
			//System.out.println(" admin_list " + admin.toString());
			String role = adminDao.getRoleById(admin.getAdmin_id());
			admin.setAdmin_role(role);
			//System.out.println(admin.getAdmin_role() + " ADMINLIST");
		}
		model.addAttribute("admins", admins);
		return "admin-list";
	}

	@RequestMapping("/admin-permission")
	public String toAdminPermission(Model model) {
		return "admin-permission";
	}

	@RequestMapping("/admin-role")
	public String toAdminRole(Model model) {
		return "admin-role";
	}
	
	@RequestMapping("/admin-add")
	public String toAdminAdd(Model model) {
		return "admin-add";
	}
	@RequestMapping("/admin-edit")
	public String toAdminEdit(Model model) {
		return "admin-edit";
	}
	
	@RequestMapping("/product-category")
	public String toProductCategory(Model model) {
		List<ResourceCategory> list = resourceDao.getAllCategory();
//		JSONArray zNodes = new JSONArray();
//		
//		for(ResourceCategory info:list) {
//			Map<String,Object> zNode = new HashMap<String,Object>();
//			zNode.put("id", info.getId());
//			zNode.put("pid", info.getPid());
//			zNode.put("name", info.getName());
//			zNodes.add(zNode);
//			System.out.println(zNode.toString());
//		}
		JSONArray zNodes = JSONArray.fromObject(list);
		//System.out.println(" 分类信息：  " + zNodes);
		model.addAttribute("zNodes", zNodes);
		return "product-category";
	}
	
	
	@RequestMapping("/product-list")
	public String toProductList(Model model) {
		List<ResourceCategory> list = resourceDao.getAllCategory();
		JSONArray zNodes = JSONArray.fromObject(list);
		System.out.println(" product里面的分类信息：  " + zNodes);
		model.addAttribute("zNodes", zNodes);
		
//		/*资源信息*/
//		List resources = resourceDao.getAllResource();
//		model.addAttribute("resources", resources);
//		System.out.println(" product里面的资源信息：  " + resources);
		return "product-list";
	}
	
	@RequestMapping("/product-add")
	public String toAddProduct(Model model) {
		return "product-add";
	}
	
	@RequestMapping("/product-edit")
	public String toEditProduct(Model model) {
		return "product-edit";
	}
	
	
	@RequestMapping("/article-list")
	public String toArticleList(Model model,HttpServletRequest request) {
		String cname;
		List<Information_toUser> infos = resourceDao.getAllInfomation();
		/*根据id信息补充具体的文字信息比如admin_id、bu_category_id补充admin,bu_catergory*/
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("this_role");
		
		Iterator<Information_toUser> iterator = infos.iterator();
		 while (iterator.hasNext())
		 {
			 /*使用iterator是为了编辑ArrayList，foreach直接remove(obj)会出实现BUG*/
			 Information_toUser info = iterator.next();
			 /**判断是否为管理员通知，来判断用户类别 ; 用一张表存储俩张表的用户信息，故有逻辑性判断依据就是category_id设置为1**/
				if(info.getBu_category_id()==1&&(role.equals("admin")||role.equals("管理员"))) {
					iterator.remove();
				}else {
				cname = resourceDao.getInformationCategoryByCategoryId(info.getBu_category_id());
				Admin_User admin = adminDao.getAdminById(info.getCreater_id());
				info.setBu_category(cname);
				info.setCreater_name(admin.getAdmin_name());
				}
		 }
		model.addAttribute("infos", infos);
		return "article-list";
	}

	
}
