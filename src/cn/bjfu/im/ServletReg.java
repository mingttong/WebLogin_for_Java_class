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
	 * 跳转页面是否可以放在父类？ServletLogin ServletReg
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usr = request.getParameter("usr");
		String pwd = request.getParameter("pwd");
		
		StudentVO vo = new StudentVO(usr, pwd);
		StudentDAO dao = new StudentDAO();
		
		boolean f = false;
		
		f = dao.add(vo);
		
		if (f) {
			System.out.println("注册成功！");
			
			// 在session中存储登录信息
			HttpSession session = request.getSession();
			session.setAttribute("LOGIN_RES", usr);
			
			// 跳转至welcome.jsp并显示提示
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
	    	rd.forward(request, response);
		} else {
			System.out.println("注册失败！");
			
			// 在session中存储注册提示信息
			HttpSession session = request.getSession();
			session.setAttribute("REG_RES", "该用户名已经注册了！");
			
			// 跳转至reg.jsp并显示提示
			RequestDispatcher rd = request.getRequestDispatcher("reg.jsp");
	    	rd.forward(request, response);
		}
		
	}

}
