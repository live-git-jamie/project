<%@ page language="java"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Insert title here</title>
	</head>
	<body>
		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		<%
			// create the connection
			String dbName = "dreamsdb";
			String url = "jdbc:mysql://localhost:3306/";
			String user = "root";
			String pass = "my#1SQLroot";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				//Class.forName("com.mysql.jdbc.Driver");
				
				Connection connection = DriverManager.getConnection(url+dbName,user,pass);
				System.out.println("Connection Established!");
	
				// create the statement
				Statement statement = connection.createStatement();
				System.out.println("Statement created!");
				// Fetch the results by executing the query
				ResultSet resultSet = statement.executeQuery("select * from dream");
		%>
		<div class="container" style="height:500px;width:500px">
			<table class="table table-dark">
				<thead class="thead-light">
					<tr>
						<th scope="col">User</th>
						<th scope="col">Dream Title</th>
						<th scope="col">Date</th>
					</tr>
				</thead>
				<%
					// Iterate the resultSet
					while (resultSet.next()) {
				%>
				<tr>
					<td><%= resultSet.getString("dream_records_id") %></td>
					<td><%= resultSet.getString("dream_title") %></td>
					<td><%= resultSet.getString("dream_date") %></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<%		
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println(e);
			}
		%>
	</body>
</html>