package cn.bjfu.im;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogoff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogoff() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// 删除当前浏览器对应的session
		HttpSession session = request.getSession();
		session.invalidate();
				
		// 跳转页面
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
    	rd.forward(request, response);
		
	}

}
