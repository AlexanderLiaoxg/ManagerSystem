package po;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Information_toUser {
   
	private int bu_id;
	private int creater_id;
	private String creater_name;
	private int bu_category_id;
	private String bu_category;
	private String bu_content;
	private String bu_title;
	private Date bu_create_time;
	private int bu_read_times;
	private String bu_state;
	/**格式化时间**/
	SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public int getBu_id() {
		return bu_id;
	}
	public void setBu_id(int bu_id) {
		this.bu_id = bu_id;
	}
	public int getCreater_id() {
		return creater_id;
	}
	public void setCreater_id(int creater_id) {
		this.creater_id = creater_id;
	}
	public String getCreater_name() {
		return creater_name;
	}
	public void setCreater_name(String creater_name) {
		this.creater_name = creater_name;
	}
	public int getBu_category_id() {
		return bu_category_id;
	}
	public void setBu_category_id(int bu_category_id) {
		this.bu_category_id = bu_category_id;
	}
	public String getBu_category() {
		return bu_category;
	}
	public void setBu_category(String bu_category) {
		this.bu_category = bu_category;
	}
	public String getBu_content() {
		return bu_content;
	}
	public void setBu_content(String bu_content) {
		this.bu_content = bu_content;
	}
	public String getBu_title() {
		return bu_title;
	}
	public void setBu_title(String bu_title) {
		this.bu_title = bu_title;
	}
	public String getBu_create_time() {
		return format.format(bu_create_time);
	}
	public void setBu_create_time(Date bu_create_time) {
		this.bu_create_time = bu_create_time;
	}
	public int getBu_read_times() {
		return bu_read_times;
	}
	public void setBu_read_times(int bu_read_times) {
		this.bu_read_times = bu_read_times;
	}
	public String getBu_state() {
		return bu_state;
	}
	public void setBu_state(String bu_state) {
		this.bu_state = bu_state;
	}
	@Override
	public String toString() {
		return "Information_toUser [bu_id=" + bu_id + ", creater_id=" + creater_id + ", creater_name=" + creater_name
				+ ", bu_category_id=" + bu_category_id + ", bu_category=" + bu_category + ", bu_content=" + bu_content
				+ ", bu_title=" + bu_title + ", bu_create_time=" + bu_create_time + ", bu_read_times=" + bu_read_times
				+ ", bu_state=" + bu_state + "]";
	}
}
