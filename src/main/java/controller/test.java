package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

public class test {

	public void test() {
		//1初始化shiro的安全管理器
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		
		//2设置用户的权限信息到安全管理器
		Realm realm = new IniRealm("classpath:shiro.ini");
		securityManager.setRealm(realm);
		
		//3使用SecurityUtils将securityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		
		//4创建一个Subject实例
		Subject subject = SecurityUtils.getSubject();
		
		//5创建token令牌，记录用户认证的身份和凭证即账户和密码
		UsernamePasswordToken token = new UsernamePasswordToken("lxg","123");
		System.out.println("用户认证状态1：" + subject.isAuthenticated());
		
		//6主体要进行登陆，登陆的时候进行验证检查
		subject.login(token);
		//用户认证状态
		System.out.println("用户认证状态2：" + subject.isAuthenticated());
		
		//7.检查角色的授权状态
		System.out.println("是否拥有admin角色 " + subject.hasRole("admin"));
		System.out.println("是否拥有admin角色 " + subject.hasRole("public"));
		
		//8.检查权限的授权状态
		System.out.println("product:view" + subject.isPermitted("product:view"));
		System.out.println("product:view" + subject.isPermitted("product:view","product:update"));
	
		//退出
		subject.logout();
		System.out.println("用户认证状态" + subject.isAuthenticated());
	}
}
