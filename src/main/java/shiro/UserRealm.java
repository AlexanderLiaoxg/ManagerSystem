package shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import dao.AdminDao;
import dao.UserDao;
import po.Admin_User;
import service.AdminService;
import service.UserService;

public class UserRealm  extends AuthorizingRealm{
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminService adminService;

	 /**
     * 授权**/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub

	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();       
	        Admin_User admin = (Admin_User)principals.getPrimaryPrincipal();
			System.out.println("执行授权认证" + admin.getAdmin_id());
	        String role = adminService.selectRoleById(admin.getAdmin_id());
	        System.out.println(" power : " + role);
	        info.addRole(role);
		return info;
	}

	/**登陆**/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
			//token是传输来的用户输入的数据
		 	UsernamePasswordToken token = (UsernamePasswordToken) arg0;
			String password = new String(token.getPassword());
		 	System.out.println("Realm中：" + token.getUsername() + password);
		 	//user是数据库存在的数据
		 	Admin_User admin = adminService.selectUserById(Integer.valueOf(token.getUsername()));
		       //交由shiro比对信息
		 	if(admin==null) {
		 		//用户不存在
		 		System.out.println("数据库传回数据为空");
		 		return null;//shiro底层会抛出UnKnownAccountException
		 	}else {
			 	System.out.println("数据库中：" + admin.getAdmin_id() + admin.getAdmin_password());
		 	}
		 	/*return的第一个参数就是传到授权验证的principals*/
	        return new SimpleAuthenticationInfo(admin,admin.getAdmin_password(),getName());
	}

}
