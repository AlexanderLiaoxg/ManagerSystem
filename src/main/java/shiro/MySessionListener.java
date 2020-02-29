package shiro;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import controller.UserController;
import service.UserService;

public class MySessionListener implements SessionListener {

	/**
	 * 统计在线人数
	 * juc包下线程安全自增
	 */
	private final AtomicInteger sessionCount = new AtomicInteger(0);
	
	public void onStart(Session session) {
		// TODO Auto-generated method stub
		//会话创建在线人数加一
		sessionCount.incrementAndGet();
		 System.out.println("会话创建：" + session.getId()); 
	}

	public void onStop(Session session) {
		// TODO Auto-generated method stub
		//减一
		sessionCount.decrementAndGet();
		System.out.println("退出会话：" + session.getId());  
	}

	public void onExpiration(Session session) {
		// TODO Auto-generated method stub
		//减一
		System.out.println("会话过期1：" + session.getId()); 
		sessionCount.decrementAndGet();
        HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
        try {
			response.sendRedirect("toLogin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("遇到了错误");
			e.printStackTrace();
		}
	}
	/**
	 * 获取在线人数使用
	 */
	public AtomicInteger getSessionCount() {
		return sessionCount;
	}
}
