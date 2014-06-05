package cn.edu.thu.crawler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.thu.bean.HuntInfo;
import cn.edu.thu.util.SQLUtil;

/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年5月31日 下午7:11:26
 */
public class Crawler {
	public static void main(String[] args) throws IOException {
//		Date d1 = new Date("Thu Jun  5 10:50:04 2014");
//		SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String dateStr = s.format(d1); //转为字符串
//		System.out.println(dateStr);
//		Map<String,String> str = getContent("http://www.newsmth.net/nForum/article/Career_Campus/180218?ajax&p=1");
		//发信站: 水木社区 (Mon Apr 14 10:46:05 2014), 站内 
//		System.out.println(str.indexOf("发信站: 水木社区 (")  + ","+ str.indexOf("), 站内 "));
//		String temp = str.substring(str.indexOf("发信站: 水木社区 (") , str.indexOf("), 站内"));
//		System.out.println(temp);
//		System.out.println(temp.substring("发信站: 水木社区 (".length()));
//		for(Map.Entry<String, String> m : str.entrySet()){
//			System.out.println(m.getKey());
//			System.out.println("---------------------------------------");
//			System.out.println(m.getValue());
//		}
		crawleAndInsertToDB();
//		Set<HuntInfo> set = getShuimu();
//		System.out.println(set.size());
		// SQLUtil su = new SQLUtil();
		// su.insert(set);
//		for (HuntInfo hi : set) {
//			System.out.println(hi.getTitle());
//			System.out.println(hi.getContent_url());
//			System.out.println(hi.getSource());
//			System.out
//					.println("--------------------------------------------------");
//		}
		// String str =
		// getContent("http://www.newsmth.net/nForum/article/Career_Upgrade/255410?ajax");
		// String str = "<a href=\"http://www.newsmth.net\" rel=\"nofollow\">";
		// System.out.println(trans(str));
	}

	public static void print(List<HuntInfo> set){
		for (HuntInfo hi : set) {
			System.out.println(hi.getTitle());
			System.out.println(hi.getContent_url());
			System.out.println(hi.getSource());
			System.out.println("-----------------------------------------");
		}
		System.out.println(set.size());
	}
	public static void crawleAndInsertToDB() {
		List<HuntInfo> set_social,set_campus,set_hunting;
		try {
			set_social = getShuimu("Career_Upgrade","shuimu_social");
			set_campus = getShuimu("Career_Campus","shuimu_campus");
			set_hunting = getShuimu("ExecutiveSearch","shuimu_hunting");
			print(set_social);
			print(set_campus);
			print(set_hunting);
			SQLUtil su = new SQLUtil();
			su.insert(set_social);
			su.insert(set_campus);
			su.insert(set_hunting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取信息标题、链接
	public static List<HuntInfo> getShuimu(String source, String sourceTag)
			throws IOException {
		List<HuntInfo> list = new ArrayList<HuntInfo>();
		String url = "http://www.newsmth.net/nForum/board/" + source
				+ "?ajax&p=";
		for (int j = 1; j <= 5; j++) {
			Document doc = Jsoup
					.connect(url + j)
					.timeout(50000)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 "
									+ "(KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36")
					.get();
			Elements elements = doc.getElementsByClass("title_9");
			for (Element ele : elements) {
				String title = ele.text();
				String content_url = ele.select("a[href]").attr("abs:href");
				// 过滤一些广告之类的数据
				if (title.contains("黑名单") || title.contains("更新")
						|| title.contains("警告") || title.contains("主板")
						|| title.contains("发布") || title.contains("发帖")
						|| title.contains("转载") || title.contains("求职")
						|| title.contains("主题") || title.contains("取消")
						|| title.contains("？") || title.contains("抱怨")
						|| title.contains("注意") || title.contains("阅读")
						|| title.contains("舞蹈") || title.contains("销售")
						|| title.contains("规定") || title.contains("赌")
						|| title.contains("审核") || title.contains("治理")
						|| title.contains("秘书") || title.contains("咨询")
						|| title.contains("环保") || title.contains("编剧")
						|| title.contains("求助") || title.contains("关于")
						|| title.contains("环保") || title.contains("编剧")
						|| title.contains("积分") || title.contains("版面")
						|| title.contains("变更") || title.contains("通知")) {
					continue;
				} else {
					Map<String,String> map = getContent(content_url);
					HuntInfo hi = new HuntInfo();
					hi.setTitle(title);
					hi.setContent_url(content_url);
					hi.setSource(sourceTag);
					for(Map.Entry<String, String> m : map.entrySet()){
						hi.setContent(m.getKey());
						hi.setPub_date(m.getValue());
					}
					list.add(hi);
				}
			}
		}
		return list;
	}

	// 获取文章具体内容
	public static Map<String,String> getContent(String url) throws IOException {
		Map<String,String> map = new HashMap<String,String>();
		Document doc = Jsoup
				.connect(url)
				.timeout(50000)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 "
								+ "(KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36")
				.get();
		Elements elements = doc.getElementsByClass("a-content");
		String temp = "";
		for (Element ele : elements) {
			temp += ele.html();
		}
		String str = temp.substring(temp.indexOf("发信站: 水木社区 (") , temp.indexOf("), 站内"));
		String value = str.substring("发信站: 水木社区 (".length());
		//Mon Apr 14 10:46:05 2014
		//Thu Jun  5 10:50:04 2014
		Date d1 = new Date(value.replace("&nbsp;&nbsp;", " "));
		SimpleDateFormat s= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put(trans(temp),s.format(d1));
		return map;
	}

	public static String trans(String str) {
		String arg1 = Character.toString('\"');
		String arg2 = "\\\\" + '"';
		String ret = str.replaceAll(arg1, arg2);
		return ret;
	}
}
