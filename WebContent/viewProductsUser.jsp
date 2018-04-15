<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.sql.*" %>
<% 
Class.forName(DBMS_EVAL.Utilities.jdbc_driver);

ResultSet product = (ResultSet) request.getAttribute("product");
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
	<table style="width:100%">
	  <tr>
	    <th>Product</th>
	    <th>Cost</th> 
	    <th>Catogory</th>
	    <th>Discription</th>
	    <th>Quantity</th>
	    <th>BUY</th>
	  </tr>
	  <%
	  	while(product.next()) {
	  %>
		  <tr>
		    <td><% out.println( product.getString("prod")); %></td>
		    <td><% out.println( product.getInt("cost")); %></td>
		    <td><% out.println( product.getString("category")); %></td>
		    <td><% out.println( product.getString("descr")); %></td>
		    <form method="post">
			    <td>
		    		<input type="hidden" name="productid" value="<% out.println( product.getString("prodid")); %>" />
		    		<input type="hidden" name="price" value="<% out.println( product.getString("cost")); %>" />
		    		<input type="number" name="quantity"/>
			    </td>
			    
			    <td><input type="submit" /></td>
		    
		    </form>
		  </tr>
	<% } %>
	</table>
</body>
</html>