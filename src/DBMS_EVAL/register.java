package DBMS_EVAL;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uemail = request.getParameter("email");
		String upassword = request.getParameter("password");
		String upassword2 = request.getParameter("password2");
		String username = request.getParameter("username");
		String role = request.getParameter("role");
		
		if(!upassword.equals(upassword2))
			response.sendRedirect("register");
		String query = "insert into users (email, password, username, role) "
				+ "values('" + uemail + "', '" + upassword + "', '" + username + "', " + role + ")";
		System.out.println(query);
		boolean isRegistered = Utilities.executeUpdate(query);
		
		if(isRegistered)
			response.getWriter().append("You are registered");
		else
			response.getWriter().append("You are not registered");
	}

}
