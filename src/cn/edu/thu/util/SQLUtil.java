package cn.edu.thu.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.edu.thu.bean.HuntInfo;

/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午8:34:54
 */
public class SQLUtil {
	DBUtil db = new DBUtil();

	public void insert(Set<HuntInfo> set) {
		db.openConnection();
		for (HuntInfo hi : set) {
			String temp = "select content_url from huntinfo";
			ResultSet rs = db.query(temp);
			List<String> list = new ArrayList<String>();
			try {
				while (rs.next()) {
					list.add(rs.getString("content_url"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (!list.contains(hi.getContent_url())) {
				String sql = "insert into huntinfo(title,content_url,content,source) "
						+ "values('"
						+ hi.getTitle()
						+ "','"
						+ hi.getContent_url()
						+ "','"
						+ hi.getContent()
						+ "','"
						+ hi.getSource() + "')";
				db.update(sql);
			}
			System.out.println(hi.getTitle() + ", " + hi.getContent_url());
		}
		db.closeConnection();
	}

	public int getTotalPage(String source,int pageSize) {
		int rowCount = 0;
		int totalPage = 0;
		db.openConnection();
		String query = "select count(*) from huntinfo where source = '" + source + "'";
		ResultSet rs = db.query(query);
		try {
			while (rs.next()) {
				rowCount = rs.getInt(1);
				if (rowCount % pageSize == 0)
					totalPage = rowCount / pageSize;
				else
					totalPage = rowCount / pageSize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return totalPage;
	}

	public Set<HuntInfo> getInfo(String source,int pageNow, int pageSize) {
		Set<HuntInfo> set = new HashSet<HuntInfo>();
		db.openConnection();
		String query = "select id,title,content_url from huntinfo where source = '"
				+ source + "' order by id desc limit "
				+ (pageNow * pageSize - pageSize) + "," + pageSize;
		ResultSet rs = db.query(query);
		try {
			while (rs.next()) {
				HuntInfo hi = new HuntInfo();
				hi.setId(rs.getInt("id"));
				hi.setTitle(rs.getString("title"));
				hi.setContent_url(rs.getString("content_url"));
				set.add(hi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return set;
	}

	public HuntInfo getContent(int id) {
		db.openConnection();
		HuntInfo hi = new HuntInfo();
		String query = "select title,content_url,content from huntinfo where id= "
				+ id;
		ResultSet rs = db.query(query);
		try {
			while (rs.next()) {
				hi.setTitle(rs.getString("title"));
				hi.setContent_url(rs.getString("content_url"));
				hi.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return hi;
	}

}
