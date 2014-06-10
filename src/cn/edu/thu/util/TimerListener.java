package cn.edu.thu.util;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author zhf
 * @email zhf.thu@gmail.com
 * @version 创建时间：2014年6月2日 上午9:27:26
 */
public class TimerListener implements ServletContextListener {
	private Timer timer;
	private EveryHourTimerTask task;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		timer.cancel();
		System.out.println("定时器已销毁");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		task = new EveryHourTimerTask();
		timer.schedule(task, 60 * 60 * 1000, 60 * 60 * 1000);//服务器启动后的一小时后启动，每隔一小时执行一次
	}

}
