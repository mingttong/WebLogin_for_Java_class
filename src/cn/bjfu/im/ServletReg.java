package cn.bjfu.im;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
			// ��תҳ��
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
	    	rd.forward(request, response);
		} else {
			System.out.println("ע��ʧ�ܣ�");
			
			// ��תҳ��
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
	    	rd.forward(request, response);
		}
		
	}

}
