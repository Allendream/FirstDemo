package service;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONArray;
import org.json.JSONObject;

import dao.DbUtil;


/**
 * Servlet implementation class Select
 */
@WebServlet("/Select")
public class Select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			String serialNumber=null,backWood=null,price=null;  
			String builder=request.getParameter("builder");
			 String model=request.getParameter("model"); 
			 String type=request.getParameter("type").trim(); 
			//out.print(type);
	     //String builder= (String)request.getSession().getAttribute("builder");
	     // String type = (String) request.getSession().getAttribute("type");
		//String model=((String) request.getSession().getAttribute("model"));

		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = null;
			try{
		Connection conn = DbUtil.getConnection();
			sql = "select * from `guitar` where type='"+ type + " ' and model='"+ model + " ' and builder='"+ builder + " '";
			pstat = conn.prepareStatement(sql);		
			rs = pstat.executeQuery();	
			out.print("<h1 align='center'>查询结果</h1>");
		out.println("<table width='100%' border='1' align='center' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF' bordercolordark='#819BBC' bordercolorlight='#FFFFFF'>");
		out.println("<tr><td heigh='28'><div align='center'>"+"吉他序号"+"</div></td><td><div align='center'>"
				+"价格"+"</div></td><td><div align='center'>"+"制造商"+"</div></td><td><div align='center'>"
				+"型号"+"</div></td><td><div align='center'>"+"类型"+"</div></td><td><div align='center'>"
					+"木料"+"</div></td></tr>");	
		//  out.println(rs.getString(type));
			if (rs.next())  {
				serialNumber = rs.getString("serialNumber").trim();
				price = rs.getString("price").trim();
				builder = rs.getString("builder").trim();
				model=rs.getString("model").trim();
				type=rs.getString("type").trim();
				backWood=rs.getString("backWood").trim();
			
				//out.print(serialNumber+price+builder+model+type+backWood);
		out.println("<tr><td heigh='28'><div align='center'>"+serialNumber+"</div></td><td><div align='center'>"
				+price+"</div></td><td><div align='center'>"+builder+"</div></td><td><div align='center'>"
				+model+"</div></td><td><div align='center'>"+type+"</div></td><td><div align='center'>"
					+backWood+"</div></td></tr>");			
			}
			
				rs.close();
			out.println("</table>");
				out.print("<button type='submit' class='btn btn-default' link='index.html'>返回</button>");
				out.close();
				pstat.close();
				conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

}
