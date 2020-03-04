package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ResourceDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import po.Admin_User;
import po.Information_toUser;
import po.ResourceCategory;
import po.Resource_Info;
import service.ResourceService;
import util.KMP;


@Controller
public class ResourceController {
	@Autowired
	private  ResourceDao resourceDao;
	@Autowired
	private  ResourceService resourceService;
	
	@ResponseBody
	@RequestMapping("toDeleteNode")
	public JSONObject  toDeleteNode(int[] nodes) {
		
		for(int i=0;i<nodes.length;i++)
		{
			resourceDao.deleteNode(nodes[i]);
			System.out.println("删除了id为" + nodes[i] + "的节点");
		}
		
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}

	@ResponseBody
	@RequestMapping("toEditNode")
	public JSONObject  toEditNode(ResourceCategory category) {
		
		System.out.println("修改后的节点信息为： " + category.getNodeId() + "nodeName " + category.getNodeName());
		
		resourceDao.editNode(category);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("toAddNode")
	public JSONObject  toAddNode(ResourceCategory category) {
		
		System.out.println("新增的节点信息为： " + category.toString());
		resourceDao.addNode(category);
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	
	
	/**资源管理**/
	@RequestMapping("toGetResourceByCategoryId")
	public String  toGetResourceByCategoryId(int categoryId,Model model) {
		List<ResourceCategory> list = resourceDao.getAllCategory();
		JSONArray zNodes = JSONArray.fromObject(list);
		System.out.println(" product里面的分类信息：  " + zNodes);
		model.addAttribute("zNodes", zNodes);
		
		/*资源信息*/
		List resources = resourceDao.getResourceByCategoryId(categoryId);
		model.addAttribute("resources", resources);
		System.out.println(" product里面的资源信息：  " + resources);
		return "product-list";
		
	}
	
	@ResponseBody
	@RequestMapping("toAddResource")
	public JSONObject  toAddResource(Resource_Info resource) {
		
		System.out.println("新增的资源信息为： " + resource.toString());
		resourceDao.addResource(resource);

		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("toDeleteResource")
	public JSONObject  toDeleteResource(int re_id) {
		
		System.out.println("删除的资源id为： " + re_id);
		resourceDao.deleteResource(re_id);

		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping("toEditResource")
	public JSONObject  toEditResource(Resource_Info resource) {
		
		System.out.println("修改后的资源信息为： " + resource.toString());
		resourceDao.editResource(resource);
		
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	

	@ResponseBody
	@RequestMapping("toDeletResources")
	public JSONObject  toDeletResources(@RequestParam(value="checked_ids") int[] checked_ids) {
		
		for(int i=0 ; i<checked_ids.length; i++)
		{
			resourceDao.deleteResource(checked_ids[i]);
		}
		
		JSONObject result = new JSONObject();  
        result.put("success", true);  
		return result;
		
	}
	
	@RequestMapping("toGetDateFindResource")
	public String  toGetDateFindResource(Model model,String logMin,String logMax,String resource_name) {
		/*加载左侧ztree的信息*/
		List<ResourceCategory> list = resourceDao.getAllCategory();
		JSONArray zNodes = JSONArray.fromObject(list);
		model.addAttribute("zNodes", zNodes);
		
		/*取出所有数据,用sql语句直接比较datetime数据，比如bu_create_time>'2020-03-03'*/
		List<Resource_Info> resources = resourceDao.getDateFindResources(logMax, logMin);
		/*截取id和title组成新的MAP信息*/
		Map<Integer,String> datas = new HashMap<Integer, String>();
		Iterator<Resource_Info> iterator = resources.iterator();
		 while (iterator.hasNext())
		 {
			 Resource_Info resource = iterator.next();
			 datas.put(resource.getRe_id(), resource.getRe_name());
		 }
		KMP kmp = new KMP(resource_name, datas);
		System.out.println("datas: " + datas.toString());
		List<Integer> re_ids = kmp.Judgement();
		
		/**根据bu_id返回筛选的结果集**/
		iterator = resources.iterator();
		while (iterator.hasNext())
		 {
			 	Resource_Info resource = iterator.next();
			 	if(!re_ids.contains(resource.getRe_id())) {
			 		iterator.remove();
			 		System.out.print("移除了：" + resource.getRe_name());
			 	}
		 }
		model.addAttribute("resources", resources);
		return "product-list";
		
	}
}
