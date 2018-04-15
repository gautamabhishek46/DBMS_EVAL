package DBMS_EVAL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Product
 */
@WebServlet("/view_admin")
public class view_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public view_admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubStatement st = null;
		Statement st = null;
		ResultSet products, users, orders;
		try {
			Connection con = Utilities.getConnection();
			st = con.createStatement();
			products = st.executeQuery("SELECT * FROM shopping");
			st = con.createStatement();
			users = st.executeQuery("SELECT * FROM users");
			st = con.createStatement();
			orders = st.executeQuery("SELECT * FROM orders");
			request.setAttribute("products", products);
			request.setAttribute("users", users);
			request.setAttribute("orders", orders);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("view_admin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In doPost");
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		Statement st = null;
		if(type.equals("remove")) {
			String prodid = request.getParameter("productid");
			try {
				Connection con = Utilities.getConnection();
				st = con.createStatement();
				int res = st.executeUpdate("delete from shopping where id=" + prodid);
				System.out.println(res);
				response.sendRedirect("Login");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			System.out.println("not remove");
	}

}