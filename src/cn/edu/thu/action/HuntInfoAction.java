package cn.edu.thu.action;

import java.util.Set;

import cn.edu.thu.bean.HuntInfo;
import cn.edu.thu.service.HuntInfoService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author zhf E-mail:zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午6:52:55
 */
public class HuntInfoAction extends ActionSupport{
	private Set<HuntInfo> shi;
	private int id;
	private HuntInfo hi;
	private int pageNow = 1 ; //初始化为1,默认从第一页开始显示
	private int totalPage;
	private String source;
	HuntInfoService his = new HuntInfoService();
	public String getHuntInfoSet(){
		shi = his.getHuntInfo(source,pageNow,10);
		totalPage = his.getTotalPage(source,10);
		return SUCCESS;
	}
	
	public String getHuntContent(){
		hi = his.getContentById(id);
		return SUCCESS;
	}
	public Set<HuntInfo> getShi() {
		return shi;
	}
	public void setShi(Set<HuntInfo> shi) {
		this.shi = shi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HuntInfo getHi() {
		return hi;
	}

	public void setHi(HuntInfo hi) {
		this.hi = hi;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
