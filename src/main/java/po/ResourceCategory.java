package po;

public class ResourceCategory {
	private int nodeId;
	private int nodePid;
	private String nodeName;
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public int getNodePid() {
		return nodePid;
	}
	public void setNodePid(int nodePid) {
		this.nodePid = nodePid;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String name) {
		this.nodeName = name;
	}
	@Override
	public String toString() {
		return "ResourceCategory [nodeId=" + nodeId + ", nodePid=" + nodePid + ", nodeName=" + nodeName + "]";
	}

}
