package cn.bjfu.im;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

	/*
	 * 存储登录信息的key值
	 * 
	 * @property loginKey
	 * 
	 * @type String
	 */
	private String loginKey = "LOGIN_RES";

	public String getLoginKey() {
		return loginKey;
	}

	public void setLoginKey(String loginKey) {
		this.loginKey = loginKey;
	}

	private static final long serialVersionUID = 1L;

	public ServletLogin() {
		super();
	}

	/*
	 * 判断用户名和密码是否正确
	 * 
	 * @method checkLogInfo
	 * 
	 * @param {String} 用户名
	 * 
	 * @param {String} 密码
	 * 
	 * @return {boolean} 是否正确的结果
	 */
	public boolean checkLogInfo(String usr, String pwd) {
		boolean f = false;
		StudentVO vo = null;

		StudentDAO dao = new StudentDAO();
		vo = dao.findByUsr(usr);

		if (vo != null) {
			// 匹配密码是否正确
			System.out.println(vo.getPwd());
			f = vo.getPwd().equals(pwd) ? true : false;
		} else {
			System.out.println("用户名不存在！");
		}

		return f;
	}

	/*
	 * 判断是否用户是否登录过
	 * 
	 * @method checkLogged
	 * 
	 * @param {HttpSession} 当前浏览器的session
	 * 
	 * @return {boolean} 检查是否登录的结果
	 */
	public boolean checkLogged(HttpSession session) {
		boolean f = false;

		// key值处理
		String key = loginKey;

		// 检查session中是否有
		if (session.getAttribute(key) != null) {
			f = true;
		}

		return f;
	}

	/*
	 * 跳转页面是否可以放在父类？ServletLogin ServletReg
	 */

	/*
	 * 跳转页面
	 * 
	 * @method turnTo
	 * 
	 * @param {HttpServletRequest} 当前浏览器的请求
	 * 
	 * @param {HttpServletResponse} 当前浏览器的响应
	 * 
	 * @param {String} 需要跳转的地址
	 */
	public void turnTo(HttpServletRequest request,
			HttpServletResponse response, String addr) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(addr);
		rd.forward(request, response);
	}

	/*
	 * 在session中保存用户登录的信息
	 * 
	 * @function saveInSession
	 * 
	 * @param {String} 存储的key值
	 * 
	 * @param {String} 存储的session值
	 */
	public static void saveInSession(HttpServletRequest request, String key,
			String valueVO) {

		HttpSession session = request.getSession();
		session.setAttribute(key, valueVO);

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/*
	 * 响应POST请求
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");

		// 用户信息
		String usrInfo = usr;

		// 获取session
		HttpSession session = request.getSession();

		// 判断是否已经登录过
		if (checkLogged(session)) {
			// 跳转页面...
			turnTo(request, response, "logoff.html");

		} else if (checkLogInfo(usr, pwd)) {
			// session缓存用户信息
			saveInSession(request, loginKey, usrInfo);

			// 跳转页面...
			// turnTo(request, response, "welcome.html");
			turnTo(request, response, "welcome.jsp");
		} else {
			System.out.println("用户名或密码错误！");
			// 跳转页面...
			turnTo(request, response, "login.html");
		}

	}

}
