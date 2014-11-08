package com.mseeworld.qzh.servlet;

import com.mseeworld.qzh.util.FileOperation;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class JsonServlet
 */
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JsonServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			if (request.getParameter("filepath") != null
					&& request.getParameter("filepath") != "") {
				String filepath = request.getParameter("filepath"); 
				String jsonStr = FileOperation.readFile(request.getRealPath("/")+filepath);
				
				response.setCharacterEncoding("UTF-8"); 
				response.setContentType("text/html;charset=UTF-8"); 
//				PrintWriter out = response.getWriter();				
//				out.println(jsonStr);
				
				OutputStream out = response.getOutputStream();  
				out.write(jsonStr.getBytes("UTF-8"));
				
			}
		} catch (Exception e) {
			response.setStatus(500); 
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
