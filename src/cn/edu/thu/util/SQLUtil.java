package cn.edu.thu.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.thu.bean.HuntInfo;

/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午8:34:54
 */
public class SQLUtil {
	DBUtil db = new DBUtil();

	public static void main(String[] args) {
		DBUtil db = new DBUtil();
		db.openConnection();
		String sql = "insert into huntinfo(title,content_url,content,source,pub_date) "
				+ "values('hello','http:test','just test','shui','2014-06-05 10:50:04')";
		db.update(sql);
		db.closeConnection();
	}
	public void insert(List<HuntInfo> set) {
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
				String sql = "insert into huntinfo(title,content_url,content,source,pub_date) "
						+ "values('"
						+ hi.getTitle()
						+ "','"
						+ hi.getContent_url()
						+ "','"
						+ hi.getContent()
						+ "','"
						+ hi.getSource() + "','"
						+ hi.getPub_date()	+ "')";
				db.update(sql);
			}
//			System.out.println(hi.getTitle() + ", " + hi.getContent_url());
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

	public List<HuntInfo> getInfo(String source,int pageNow, int pageSize) {
		List<HuntInfo> list = new ArrayList<HuntInfo>();
		db.openConnection();
		String query = "select id,title,content_url,pub_date from huntinfo where source = '"
				+ source + "' order by pub_date desc limit "
				+ (pageNow * pageSize - pageSize) + "," + pageSize;
		ResultSet rs = db.query(query);
		try {
			while (rs.next()) {
				HuntInfo hi = new HuntInfo();
				hi.setId(rs.getInt("id"));
				hi.setTitle(rs.getString("title"));
				hi.setContent_url(rs.getString("content_url"));
				hi.setPub_date(rs.getString("pub_date"));
				list.add(hi);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return list;
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
