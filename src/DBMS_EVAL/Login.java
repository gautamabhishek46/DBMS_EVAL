package DBMS_EVAL;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = Utilities.getCookieValue(request.getCookies(), "email");
		if( email != null) {
//			Cookie ck=new Cookie("email","");
//			ck.setMaxAge(0);
//			response.addCookie(ck);
			if(!Utilities.is_admin(request))
				response.sendRedirect("viewProducts");
			else
				response.sendRedirect("view_admin");
		}
		else
			request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uemail = request.getParameter("email");
		String upassword = request.getParameter("password");
		if(Utilities.checkEmailPassword(uemail, upassword)) {
			HttpSession session = request.getSession();
			session.setAttribute("email", uemail);	
			Cookie ck = new Cookie("email",uemail);
			response.addCookie(ck);
			response.getWriter().append("Correct");
		}
		else
			response.getWriter().append("Not Correct");
		
		response.sendRedirect("Login");
			
	}
}
