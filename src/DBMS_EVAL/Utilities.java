package DBMS_EVAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Utilities {
	public static final String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@192.168.2.25:1521:ORCL";
	public static final String user = "BCS15";
	public static final String password = "BCS15";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(jdbc_driver);
		return DriverManager.getConnection(url, user, password);
	}
	
	public static String getCookieValue(Cookie ck[], String cName) {
		String ckVal = null;
		boolean found = false;
		
		for(int i = 0; i < ck.length && !found; i++) {
			if(ck[i].getName().equals(cName)) {
				ckVal = ck[i].getValue();
				found = true;
			}
		}
		return ckVal;
	}
	
	public static boolean executeUpdate(String query) {
		try {
			Connection conn = Utilities.getConnection();
			Statement st = conn.createStatement();
			boolean rs = st.executeUpdate(query) == 1 ? true : false;
			conn.close();
			return rs;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean checkEmailPassword(String email, String password) {
		Connection conn = null;
		try {
			conn = Utilities.getConnection();
			Statement st = conn.createStatement();

			System.out.println(email + " " + password);
//			ResultSet result = st.executeQuery("select * from users where email='" + email + "' and password='" + password + "'");
			ResultSet result = st.executeQuery("select * from users where email='a@a.com' and pw='1234'");
			
			int count = 0;
			
			while(result.next())
				count++;

			return count == 1? true : false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static boolean is_admin(HttpServletRequest request) {
		try {
			int num = 0;
			Connection conn = Utilities.getConnection();
			Statement st = conn.createStatement();
			String email = getCookieValue(request.getCookies(), "email");
			ResultSet result = st.executeQuery("select role from users where email='" + email + "'");
			while(result.next())
			{
				num = result.getInt("role");
			}
			conn.close();
			if(num == 0)
				return false;
			else
				return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean placeOrder(HttpServletRequest request, String pid, String price, int quant) {
		try {
			Connection conn = Utilities.getConnection();
			Statement st = conn.createStatement();
			String email = getCookieValue(request.getCookies(), "email");
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localDate = LocalDate.now();
			String date = dtf.format(localDate);
			String query = "insert into orders(client, prodid, quantity, price, odate, ordershipped) "
					+ "values('" + email + "', " + pid + ", " + quant + ", " + price + ", '" + date + "', " + "0" + ")";
			System.out.println(query);
			boolean result = executeUpdate(query);
			
			return result;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
