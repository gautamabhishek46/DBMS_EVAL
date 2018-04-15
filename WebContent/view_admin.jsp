<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<% 
Class.forName("oracle.jdbc.driver.OracleDriver");

ResultSet products = (ResultSet) request.getAttribute("products");
ResultSet users = (ResultSet) request.getAttribute("users");
ResultSet orders = (ResultSet) request.getAttribute("orders");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

</head>
<body>
	<h1>Products</h1>
	<table style="width:100%">
	  <tr>
	    <th>Product</th>
	    <th>Cost</th> 
	    <th>Category</th>
	    <th>Description</th>
	    <th>Remove Product</th>
	  </tr>
	  <%
	  	while(products.next()) {
	  %>
		  <tr>
		    <td><% out.println( products.getString("prod")); %></td>
		    <td><% out.println( products.getInt("cost")); %></td>
		    <td><% out.println( products.getString("category")); %></td>
		    <td><% out.println( products.getString("descr")); %></td>
		    <td>
		    	<form method="post" action="view_admin">
		    		<input type="hidden" name="productid" value="<% out.println( products.getString("prodid")); %>" />
		    		<input type="hidden" name="type" value="remove" />
		    		<input type="submit" value="Remove"/>
		    	</form>
		    </td>
			  </tr>
	<% } %>
	</table>
	
	<h1>Users</h1>
	<table style="width:100%">
	  <tr>
	    <th>Uname</th>
	    <th>Email</th> 
	    <th>Password</th>
	    <th>Role</th>
	  </tr>
	  <%
	  	while(users.next()) {
	  %>
		  <tr>
		    <td><% out.println( users.getString("uname")); %></td>
		    <td><% out.println( users.getString("email")); %></td>
		    <td><% out.println( users.getString("pw")); %></td>
		    <td><% out.println( users.getString("role")); %></td>
		  </tr>
	<% } %>
	</table>
	
	<h1>Orders</h1>
	<table style="width:100%">
	  <tr>
	    <th>Client</th>
	    <th>Product ID</th> 
	    <th>Quantity</th>
	    <th>Price</th>
	    <th>Order Date</th>
	    <th>Shipping Status</th>
	  </tr>
	  <%
	  	while(orders.next()) {
	  %>
		  <tr>
		    <td><% out.println( orders.getString("client")); %></td>
		    <td><% out.println( orders.getString("prodid")); %></td>
		    <td><% out.println( orders.getString("quantity")); %></td>
		    <td><% out.println( orders.getString("price")); %></td>
		    <td><% out.println( orders.getString("odate")); %></td>
		    <td><% out.println( orders.getString("ordershipped")); %></td>
		  </tr>
	<% } %>
	</table>
</body>
</html>