package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.AdminDao;
import dao.ResourceDao;
import po.ResourceCategory;

@Service("resourceService")

public class ResourceServiceImpl implements ResourceService{
	@Autowired
	private ResourceDao resourceDao;
	public List getAllCategory() {
		// TODO Auto-generated method stub
		return resourceDao.getAllCategory();
	}
	public void deleteNode(int nodeId) {
		// TODO Auto-generated method stub
		resourceDao.deleteNode(nodeId);
		
	}
	public void addNode(ResourceCategory category) {
		// TODO Auto-generated method stub
		resourceDao.addNode(category);
	}
	public void editNode(ResourceCategory category) {
		// TODO Auto-generated method stub
		resourceDao.editNode(category);
	}

}
