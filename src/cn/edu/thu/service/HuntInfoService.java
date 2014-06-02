package cn.edu.thu.service;

import java.util.Set;

import cn.edu.thu.bean.HuntInfo;
import cn.edu.thu.util.SQLUtil;

/**
 * @author zhf E-mail:zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午6:53:25
 */
public class HuntInfoService {
	SQLUtil su = new SQLUtil();
	public Set<HuntInfo> getHuntInfo(String source,int pageNow, int pageSize){
		return su.getInfo(source,pageNow,pageSize);
	}
	
	public HuntInfo getContentById(int id){
		return su.getContent(id);
	}

	public int getTotalPage(String source,int pageSize){
		return su.getTotalPage(source,pageSize);
	}
}
