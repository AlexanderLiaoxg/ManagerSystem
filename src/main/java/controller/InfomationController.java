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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.AdminDao;
import dao.ResourceDao;
import net.sf.json.JSONObject;
import po.Admin_User;
import po.InformationCategory;
import po.Information_toUser;
import service.AdminService;
import util.KMP;

@Controller
public class InfomationController {
	@Autowired
	private  AdminDao adminDao;
	@Autowired
	private  ResourceDao resourceDao;
	
	@ResponseBody
	@RequestMapping("/toChangeInformationState")
	private JSONObject toChangeInformationState(Information_toUser info) {	
		resourceDao.changeInfomationState(info);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toDeleteInformation")
	private JSONObject toDeleteInformation(int bu_id) {	
		resourceDao.deleteInformation(bu_id);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	
	/*视图解析器只负责解析并非转发*/
	@RequestMapping("/article-detail")
	public String toArticleDetail(int bu_id,HttpServletRequest request) {
		/*信息其实是页面传输给页面，这里做一个已阅操作*/
		HttpSession session = request.getSession();
		int admin_id = (int) session.getAttribute("this_id");
		resourceDao.setReaded(bu_id,admin_id);
		return "article-detail";
	}
	
	@RequestMapping("/article-add")
	public String toArticleAdd(Model model,HttpServletRequest request) {
		/*根据角色显示add页面的类别选择*/
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("this_role");
		List categorys = resourceDao.getAllInformationCategory();
		/**为加载页面添加动态的目录信息并去除不符合权限的选项**/
		Iterator<InformationCategory> iterator = categorys.iterator();
		 while (iterator.hasNext())
		 {
			 InformationCategory category = iterator.next();
			 if(category.getCid()==1&&(role.equals("admin")||role.equals("管理员"))) {
					iterator.remove();
				}
		 }

		model.addAttribute("categorys",categorys);
		return "article-add";
	}
	
	@ResponseBody
	@RequestMapping("/toAddInformation")
	public JSONObject toAddInformation(Information_toUser info) {
		System.out.println(info.toString());
		resourceDao.addInformation(info);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}

	@RequestMapping("/article-edit")
	public String toArticleEdit(int bu_id,Model model,HttpServletRequest request) {
		/**重复代码——加载info的类别信息**/
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("this_role");
		List categorys = resourceDao.getAllInformationCategory();
		/**为加载页面添加动态的目录信息并去除不符合权限的选项**/
		Iterator<InformationCategory> iterator = categorys.iterator();
		 while (iterator.hasNext())
		 {
			 InformationCategory category = iterator.next();
			 if(category.getCid()==1&&(role.equals("admin")||role.equals("管理员"))) {
					iterator.remove();
				}
		 }
		model.addAttribute("categorys",categorys);
		
		/**根据bu_id补充info信息**/
		Information_toUser info =  resourceDao.getInformationById(bu_id);
		model.addAttribute("info",info);
		return "article-edit";
	}
	
	@ResponseBody
	@RequestMapping("/toEditArticle")
	public JSONObject toEditArticle(Information_toUser info,HttpServletRequest request) {
		/**逻辑性考虑到俩个部分，一个是不该category（update）一个是改(delete then add)**/
		/*ps:不确定是否需要修改内容，修改者成为新的发布者*/
		int old_category_id = resourceDao.getInformationById(info.getBu_id()).getBu_category_id();
		int bu_id = info.getBu_id();
		int category_id = info.getBu_category_id();
		HttpSession session = request.getSession();
		info.setCreater_id((int) session.getAttribute("this_id"));
		//判断是否改变了类别
		//否
		System.out.println(info.toString());
		if(old_category_id==category_id)
		{
			resourceDao.editInformation(info);
			System.out.println(info.toString());
		}else if(old_category_id!=1&&category_id!=1){
			resourceDao.editInformation(info);
		}else {
			//操作改变类别会导致重新广播，相对于产生新的资讯
			resourceDao.deleteInformation(bu_id);
			resourceDao.addInformation(info);
		}		
		
		
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/toDeleteArticle")
	public JSONObject toDeleteArticle(int bu_id) {
		resourceDao.deleteInformation(bu_id);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	

	@RequestMapping("/article-unRead")
	public String toArticleUnRead(Model model,HttpServletRequest request) {
		String cname;
		/*根据id信息补充具体的文字信息比如admin_id、bu_category_id补充admin,bu_catergory*/
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("this_role");
		int admin_id = (int)session.getAttribute("this_id");
		/*数据库联立表查询返回信息*/
		List<Information_toUser> infos = resourceDao.getAdminUnreadInformation(admin_id);
		Iterator<Information_toUser> iterator = infos.iterator();
		 while (iterator.hasNext())
		 {
			 /*使用iterator是为了编辑ArrayList，foreach直接remove(obj)会出实现BUG*/
			 	Information_toUser info = iterator.next();
				cname = resourceDao.getInformationCategoryByCategoryId(info.getBu_category_id());
				Admin_User admin = adminDao.getAdminById(info.getCreater_id());
				info.setBu_category(cname);
				info.setCreater_name(admin.getAdmin_name());
		 }
		model.addAttribute("infos", infos);
		return "article-unRead";
	}
	
	@ResponseBody
	@RequestMapping("/toDeleteInformations")
	public JSONObject toDeleteInformations(@RequestParam(value="checked_ids") int[] checked_ids) {
		System.out.println("length: " + checked_ids.length + "  last: " + checked_ids[checked_ids.length-1]);
		for(int i=0 ; i<checked_ids.length; i++)
		{
			resourceDao.deleteInformation(checked_ids[i]);
		}
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
	}
	
	@RequestMapping("/toGetDateFindInformation")
	public String toGetDateFindInformation(Model model,String logMin,String logMax,String title) {
		/*取出所有数据,用sql语句直接比较datetime数据，比如bu_create_time>'2020-03-03'*/
		List<Information_toUser> infos = resourceDao.getDateFindInformation(logMax, logMin);
		/*截取id和title组成新的MAP信息*/
		Map<Integer,String>datas = new HashMap<Integer, String>();
		Iterator<Information_toUser> iterator = infos.iterator();
		 while (iterator.hasNext())
		 {
			 	Information_toUser info = iterator.next();
			 	datas.put(info.getBu_id(), info.getBu_title());
		 }
		KMP kmp = new KMP(title, datas);
		System.out.println("datas: " + datas.toString());
		List<Integer> bu_ids = kmp.Judgement();
		System.out.println("bu_ids: " + bu_ids.toString());
		
		/**根据bu_id返回筛选的结果集**/
		iterator = infos.iterator();
		while (iterator.hasNext())
		 {
			 	Information_toUser info = iterator.next();
			 	if(!bu_ids.contains(info.getBu_id())) {
			 		iterator.remove();
			 		System.out.print("移除了：" + info.getBu_title());
			 	}
		 }
		model.addAttribute("infos", infos);
		return "article-list";
	}
	
}
