package cn.bjfu.im;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletQueryUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletQueryUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 每页显示的记录数目
		int numPerPage = 10;
		
		// (1) 获取页面传递参数
		String keyword = request.getParameter("keyword");
		String sPage = request.getParameter("page");
		
		int iPage = 1;
		System.out.println("==================" + keyword);
		try{
			iPage = Integer.parseInt(sPage); // 转换为整数
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		// (2) 获取符合条件总记录数
		UserDAO dao = new UserDAO();
		
		// 获取记录总数
		int totalRecNum = dao.countByKeyword(keyword);
		
		// (3) 计算总页数，校验当前页码
		int totalPgNum = totalRecNum / numPerPage;
		
		if (totalRecNum % numPerPage > 0) {
			totalPgNum++;
		}
		
		if (iPage < 1) {
			iPage = 1;
		}
		
		if (iPage > totalPgNum) {
			iPage = totalPgNum;
		}
		
		// (4) 根据页码查询符合条件的记录
		List<QueryUserVO> res = dao.queryByKeyword(keyword, iPage); // 获取查询结果
		
		// (5) 页面跳转以及传值（确保执行页面跳转后doPost执行完毕）
		request.setAttribute("curPgNum", iPage);
		request.setAttribute("totalPgNum", totalPgNum);
		request.setAttribute("QUERY_RES", res);
		request.setAttribute("keyword", keyword);
		
		// 跳转页面
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

}
