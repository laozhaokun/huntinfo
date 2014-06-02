package cn.edu.thu.util;

import java.util.Calendar;
import java.util.Set;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import cn.edu.thu.bean.HuntInfo;
import cn.edu.thu.crawler.Crawler;

/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年6月2日 上午9:29:30
 */
public class EveryHourTimerTask extends TimerTask {

	@Override
	public void run() {
		Crawler.crawleAndInsertToDB();
	}

}
