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
	 * �洢��¼��Ϣ��keyֵ
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
	 * �ж��û����������Ƿ���ȷ
	 * 
	 * @method checkLogInfo
	 * 
	 * @param {String} �û���
	 * 
	 * @param {String} ����
	 * 
	 * @return {boolean} �Ƿ���ȷ�Ľ��
	 */
	public boolean checkLogInfo(String usr, String pwd) {
		boolean f = false;
		StudentVO vo = null;

		StudentDAO dao = new StudentDAO();
		vo = dao.findByUsr(usr);

		if (vo != null) {
			// ƥ�������Ƿ���ȷ
			System.out.println(vo.getPwd());
			f = vo.getPwd().equals(pwd) ? true : false;
		} else {
			System.out.println("�û��������ڣ�");
		}

		return f;
	}

	/*
	 * �ж��Ƿ��û��Ƿ��¼��
	 * 
	 * @method checkLogged
	 * 
	 * @param {HttpSession} ��ǰ�������session
	 * 
	 * @return {boolean} ����Ƿ��¼�Ľ��
	 */
	public boolean checkLogged(HttpSession session) {
		boolean f = false;

		// keyֵ����
		String key = loginKey;

		// ���session���Ƿ���
		if (session.getAttribute(key) != null) {
			f = true;
		}

		return f;
	}

	/*
	 * ��תҳ���Ƿ���Է��ڸ��ࣿServletLogin ServletReg
	 */

	/*
	 * ��תҳ��
	 * 
	 * @method turnTo
	 * 
	 * @param {HttpServletRequest} ��ǰ�����������
	 * 
	 * @param {HttpServletResponse} ��ǰ���������Ӧ
	 * 
	 * @param {String} ��Ҫ��ת�ĵ�ַ
	 */
	public void turnTo(HttpServletRequest request,
			HttpServletResponse response, String addr) throws ServletException,
			IOException {
		RequestDispatcher rd = request.getRequestDispatcher(addr);
		rd.forward(request, response);
	}

	/*
	 * ��session�б����û���¼����Ϣ
	 * 
	 * @function saveInSession
	 * 
	 * @param {String} �洢��keyֵ
	 * 
	 * @param {String} �洢��sessionֵ
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
	 * ��ӦPOST����
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");

		// �û���Ϣ
		String usrInfo = usr;

		// ��ȡsession
		HttpSession session = request.getSession();

		// �ж��Ƿ��Ѿ���¼��
		if (checkLogged(session)) {
			// ��תҳ��...
			turnTo(request, response, "logoff.html");

		} else if (checkLogInfo(usr, pwd)) {
			// session�����û���Ϣ
			saveInSession(request, loginKey, usrInfo);

			// ��תҳ��...
			// turnTo(request, response, "welcome.html");
			turnTo(request, response, "welcome.jsp");
		} else {
			System.out.println("�û������������");
			// ��תҳ��...
			turnTo(request, response, "login.html");
		}

	}

}
