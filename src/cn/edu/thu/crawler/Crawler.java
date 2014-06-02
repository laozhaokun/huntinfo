package cn.edu.thu.crawler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

	public static void print(Set<HuntInfo> set){
		for (HuntInfo hi : set) {
			System.out.println(hi.getTitle());
			System.out.println(hi.getContent_url());
			System.out.println(hi.getSource());
			System.out.println("-----------------------------------------");
		}
		System.out.println(set.size());
	}
	public static void crawleAndInsertToDB() {
		Set<HuntInfo> set_social,set_campus,set_hunting;
		try {
//			set_social = getShuimu("Career_Upgrade","shuimu_social");
			set_campus = getShuimu("Career_Campus","shuimu_campus");
			set_hunting = getShuimu("ExecutiveSearch","shuimu_hunting");
//			print(set_social);
//			print(set_campus);
//			print(set_hunting);
			SQLUtil su = new SQLUtil();
//			su.insert(set_social);
			su.insert(set_campus);
			su.insert(set_hunting);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 获取信息标题、链接
	public static Set<HuntInfo> getShuimu(String source, String sourceTag)
			throws IOException {
		Set<HuntInfo> set = new HashSet<HuntInfo>();
		String url = "http://www.newsmth.net/nForum/board/" + source
				+ "?ajax&p=";
		for (int j = 2; j <= 10; j++) {
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
						|| title.contains("环保") || title.contains("编剧")) {
					continue;
				} else {
					String content = getContent(content_url);
					HuntInfo hi = new HuntInfo();
					hi.setTitle(title);
					hi.setContent_url(content_url);
					hi.setContent(content);
					hi.setSource(sourceTag);
					set.add(hi);
				}
			}
		}
		return set;
	}

	// 获取文章具体内容
	public static String getContent(String url) throws IOException {
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
		return trans(temp);
	}

	public static String trans(String str) {
		String arg1 = Character.toString('\"');
		String arg2 = "\\\\" + '"';
		String ret = str.replaceAll(arg1, arg2);
		return ret;
	}
}
