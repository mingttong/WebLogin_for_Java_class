package cn.bjfu.im;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletReg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletReg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	/*
	 * ��תҳ���Ƿ���Է��ڸ��ࣿServletLogin ServletReg
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		StudentVO vo = new StudentVO(usr, pwd);
		StudentDAO dao = new StudentDAO();
		
		boolean f = false;
		
		f = dao.add(vo);
		
		if (f) {
			System.out.println("ע��ɹ���");
			
			// ��session�д洢��¼��Ϣ
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_RES", usr);
			
			// ��ת��welcome.jsp����ʾ��ʾ
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	    	rd.forward(request, response);
		} else {
			System.out.println("ע��ʧ�ܣ�");
			
			// ��session�д洢ע����ʾ��Ϣ
			HttpSession session = request.getSession();
			session.setAttribute("REG_RES", "���û����Ѿ�ע���ˣ�");
			
			// ��ת��reg.jsp����ʾ��ʾ
			RequestDispatcher rd = request.getRequestDispatcher("reg.jsp");
	    	rd.forward(request, response);
		}
		
	}

}
