package po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Resource_Info {
	private int re_id;
	private String re_name;
	private String re_src;
	private String re_img_path;
	private int re_category_id;
	private String re_type;
	private int uper_account;
	private String uper_name;
	private Date up_time;
	private String re_state;
	private String re_description;
	private int re_value;
	private int re_download_times;
	SimpleDateFormat format  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public int getRe_id() {
		return re_id;
	}
	public void setRe_id(int re_id) {
		this.re_id = re_id;
	}
	public String getRe_name() {
		return re_name;
	}
	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}
	public String getRe_src() {
		return re_src;
	}
	public void setRe_src(String re_src) {
		this.re_src = re_src;
	}
	public String getRe_img_path() {
		return re_img_path;
	}
	public void setRe_img_path(String re_img_path) {
		this.re_img_path = re_img_path;
	}
	public int getRe_category_id() {
		return re_category_id;
	}
	public void setRe_category_id(int re_category_id) {
		this.re_category_id = re_category_id;
	}
	public String getRe_type() {
		return re_type;
	}
	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}
	public int getUper_account() {
		return uper_account;
	}
	public void setUper_account(int uper_account) {
		this.uper_account = uper_account;
	}
	public String getUper_name() {
		return uper_name;
	}
	public void setUper_name(String uper_name) {
		this.uper_name = uper_name;
	}
	public String getUp_time() {
		return format.format(up_time);
	}
	public void setUp_time(Date up_time) {
		this.up_time = up_time;
	}
	public String getRe_state() {
		return re_state;
	}
	public void setRe_state(String re_state) {
		this.re_state = re_state;
	}
	public String getRe_description() {
		return re_description;
	}
	public void setRe_description(String re_description) {
		this.re_description = re_description;
	}
	public int getRe_value() {
		return re_value;
	}
	public void setRe_value(int re_value) {
		this.re_value = re_value;
	}
	public int getRe_download_times() {
		return re_download_times;
	}
	public void setRe_download_times(int re_download_times) {
		this.re_download_times = re_download_times;
	}
	@Override
	public String toString() {
		return "Resource_Info [re_id=" + re_id + ", re_name=" + re_name + ", re_src=" + re_src + ", re_img_path="
				+ re_img_path + ", re_category_id=" + re_category_id + ", re_type=" + re_type + ", uper_account="
				+ uper_account + ", uper_name=" + uper_name + ", up_time=" + up_time + ", re_state=" + re_state
				+ ", re_description=" + re_description + ", re_value=" + re_value + ", re_download_times="
				+ re_download_times + "]";
	}	
	
}
