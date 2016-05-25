package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
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
 * Servlet implementation class Selectguitar
 */
@WebServlet("/Selectguitar")
public class Selectguitar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Selectguitar() {
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
		response.setContentType("application/json");  
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
	    response.setHeader("Cache-Control", "no-cache");  
	    PrintWriter out = response.getWriter();	
		String builder=request.getParameter("builder");
		 String model=request.getParameter("model");
		 
		 String type=request.getParameter("type").trim(); 
		 String backWood,serialNumber,price;   
		// out.print(model);
		 //out.print(type);
		 HttpSession session = request.getSession();
		 session.setAttribute("builder", builder);
		session.setAttribute("model", model);
		session.setAttribute("type", type);
		
		
		out.print((String) request.getSession().getAttribute("builder"));
		out.print((String) request.getSession().getAttribute("model"));
		PreparedStatement pstat = null;
		ResultSet rs = null;
		String sql = null;
		JSONArray ja=new JSONArray();
		try{
			Connection conn = DbUtil.getConnection();
			sql = "select * from `guitar` where type='"+ type + " ' and model='"+ model + " ' and model='"+ model + " '";
			pstat = conn.prepareStatement(sql);		
			rs = pstat.executeQuery();	
		response.setContentType("text/x-json");		
			while (rs.next())  {
				serialNumber= rs.getString("serialNumber");
				JSONObject jo=new JSONObject();
				jo.put("serialNumber",rs.getString("serialNumber"));
				jo.put("price",rs.getString("price"));
				jo.put("builder",rs.getString("builder"));
				jo.put("model",rs.getString("model"));
				jo.put("type",rs.getString("type"));
				jo.put("backWood",rs.getString("backWood"));
				ja.put(jo);
			}
		out.print(ja.toString());	
				out.close();
				rs.close();
				pstat.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}		
}
