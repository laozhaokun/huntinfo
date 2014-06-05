package cn.edu.thu.bean;


/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午8:34:54
 */
public class HuntInfo {
	private int id;
	private String title;
	private String content;
	private String content_url;
	private String source;
	private String pub_date;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_url() {
		return content_url;
	}
	public void setContent_url(String content_url) {
		this.content_url = content_url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pubDate) {
		this.pub_date = pubDate;
	}

}
