package dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import po.Information_toUser;
import po.ResourceCategory;
import po.Resource_Info;

@Repository("resourceDao")
@Mapper
public interface ResourceDao {
	public List getAllCategory();
	public void addNode(ResourceCategory category);
	public void editNode(ResourceCategory categoryw);  		//封装为对象属性来进行操作 
	public void deleteNode(int nodeId);
	
	/*资源部分*/
	public List getAllResource();
	public void addResource(Resource_Info resource);
	public void editResource(Resource_Info resource); 
	public void deleteResource(int re_id);
	public List getResourceByCategoryId(int categoryId);
	
	/*资讯部分*/
	public List getAllInfomation();
	public String getInformationCategoryByCategoryId(int cid);
	public List getAllInformationCategory();
	public Information_toUser getInformationById(int bu_id);
	public void addInformation(Information_toUser info);
	public void editInformation(Information_toUser info); 
	public void changeInfomationState(Information_toUser info);
	public void deleteInformation(int bu_id);
	public List<Information_toUser> getAdminUnreadInformation(int admin_id);
	//多个参数用param标签传输，非pojo对象
	public void setReaded(@Param("bu_id")int bu_id, @Param("admin_id")int admin_id);
}
