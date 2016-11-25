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
		
		// ÿҳ��ʾ�ļ�¼��Ŀ
		int numPerPage = 10;
		
		// (1) ��ȡҳ�洫�ݲ���
		String keyword = request.getParameter("keyword");
		String sPage = request.getParameter("page");
		
		int iPage = 1;
		System.out.println("==================" + keyword);
		try{
			iPage = Integer.parseInt(sPage); // ת��Ϊ����
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		// (2) ��ȡ���������ܼ�¼��
		UserDAO dao = new UserDAO();
		
		// ��ȡ��¼����
		int totalRecNum = dao.countByKeyword(keyword);
		
		// (3) ������ҳ����У�鵱ǰҳ��
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
		
		// (4) ����ҳ���ѯ���������ļ�¼
		List<QueryUserVO> res = dao.queryByKeyword(keyword, iPage); // ��ȡ��ѯ���
		
		// (5) ҳ����ת�Լ���ֵ��ȷ��ִ��ҳ����ת��doPostִ����ϣ�
		request.setAttribute("curPgNum", iPage);
		request.setAttribute("totalPgNum", totalPgNum);
		request.setAttribute("QUERY_RES", res);
		request.setAttribute("keyword", keyword);
		
		// ��תҳ��
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

}
