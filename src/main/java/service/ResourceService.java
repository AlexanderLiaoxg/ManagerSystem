package service;

import java.util.List;

import po.ResourceCategory;

public interface ResourceService {
	public List getAllCategory();
	public void deleteNode(int nodeId);
	public void addNode(ResourceCategory category);
	public void editNode(ResourceCategory category);
}
