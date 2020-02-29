package po;

public class Information_toAdmin {
	int ba_id;
	int super_admin_id;
	String ba_content;
	String ba_title;
	public int getBa_id() {
		return ba_id;
	}
	public void setBa_id(int ba_id) {
		this.ba_id = ba_id;
	}
	public int getSuper_admin_id() {
		return super_admin_id;
	}
	public void setSuper_admin_id(int super_admin_id) {
		this.super_admin_id = super_admin_id;
	}
	public String getBa_content() {
		return ba_content;
	}
	public void setBa_content(String ba_content) {
		this.ba_content = ba_content;
	}
	public String getBa_title() {
		return ba_title;
	}
	public void setBa_title(String ba_title) {
		this.ba_title = ba_title;
	}
	@Override
	public String toString() {
		return "Information_toAdmin [ba_id=" + ba_id + ", super_admin_id=" + super_admin_id + ", ba_content="
				+ ba_content + ", ba_title=" + ba_title + "]";
	}
}
