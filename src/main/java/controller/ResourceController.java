package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ResourceDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import po.Admin_User;
import po.ResourceCategory;
import po.Resource_Info;
import service.ResourceService;


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
	
}
