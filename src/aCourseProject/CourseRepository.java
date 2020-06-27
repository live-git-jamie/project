package aCourseProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourseRepository {
	private CourseCRUD coursesCRUD;
	private static Connection connect;
	
	private String URL = "jdbc:mysql://localhost:3306/";
	private String USER = "root";
	private String PASS = "my#1SQLroot";
	
	public CourseRepository(CourseCRUD c) {
		this.coursesCRUD = c;
		
//		String dbName = "ezshopdb";
//		
//		/*== Connect to database ==*/
//		//String url = "jdbc:mysql://localhost:3306/ezshopdb?user=root&password=my#1SQLroot";
//		connect = null;
//		try {
//			connect = DriverManager.getConnection(URL+dbName,
//					USER, PASS);
//			if(connect != null) {
//				System.out.println("Connection success.");
//			}
//			
//
////			deleteCourse(2);
//		} catch (SQLException e) {
//			System.out.println("Connection failed.");
//			e.printStackTrace();
//		}
		
		/*========================TESTING START=======================*/
		String dbName = "ezshopdb";
		try {
			// Establish connection
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(URL + dbName,
					USER,PASS);
			System.out.println("Connection Established!");

			// create the statement
			Statement statement = connection.createStatement();
			System.out.println("Statement created!");
			// Fetch the results by executing the query
			ResultSet resultSet = statement.executeQuery("select * from product");
			
			Product product = new Product();
			// Iterate the resultSet
			while (resultSet.next()) {
				product.setProductID(resultSet.getString(("product_id")));
				product.setProductName(resultSet.getString("product_name"));
				product.setPrice(resultSet.getFloat("price"));
				product.setQuantity(resultSet.getInt("quantity"));
				
				System.out.println("<h4>" + product.getProductID() + "</h4>");
				System.out.println("<h4>" + product.getProductName() + "</h4>");
				System.out.println("<h4>" + product.getPrice() + "</h4>");
				System.out.println("<h4>" + product.getQuantity() + "</h4>");
				System.out.println();
			}
			
			
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		/*========================TESTING END=======================*/
		
		closeConnection();
	}
	
	public void closeConnection() {
		if(connect != null) {
			try {
				connect.close();
				System.out.println("Closed successfully.");
			} catch (SQLException e) {
				System.out.println("Closing connection failed.");
			}
		}
	}
	
	/* FILE I/O */
	@Deprecated
	public void writeToFile(String fileName) {
		try {
			// Set things up
			FileOutputStream fileOutStream = new FileOutputStream(fileName);
			ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutStream);
			Object[] courses = coursesCRUD.getCourses();
			// Write out
			for(Object course: courses) {
				objOutStream.writeObject(course);
			}
			
			objOutStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: <"+fileName+"> not found");
		} catch (IOException e) {
			System.out.println("ERROR accessing <"+fileName+">");
		}
		
	}
	
	@Deprecated
	public List<Course> readFile(String fileName) {
		List<Course> courses = new ArrayList<>();
		FileInputStream fileInStream = null;
		ObjectInputStream objInStream = null;
		try {
			// Set things up
			fileInStream = new FileInputStream(fileName);
			objInStream = new ObjectInputStream(fileInStream);
			// Read in
			try {
				while(true) {
					courses.add((Course)objInStream.readObject());
				}
			} catch (ClassNotFoundException e) {
				System.out.println("@readFILE:Class not Found");
			} catch (IOException e) {
				objInStream.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: <"+fileName+"> not found");
		} catch (IOException e) {
			System.out.println("ERROR accessing <"+fileName+">");
		}
	
		return courses;
	}

	/* DATABASE - JDBC */
	public static void insertCourses(HashMap<Integer,Course> courses) {
//		AlterDatabase alterDatabase = (course) -> "insert into course"+
//				" values(null,"+course.getCourseID()+","+course.getCourseDescr()
//				+","+course.getStartTime()+","+course.getEndTime()
//				+","+course.getNumStudents()+","+course.getDays().toString();

		//List<Course> c = (ArrayList<Course>)courses.values();
//		courses.forEach(Integer key -> {
//			System.out.println("");
//		});
	}
	
	
	public static void deleteCourse(int courseID) {
		System.out.println("@deleteCourse::courseID="+courseID);
		String deleteQuery = "DELETE FROM course WHERE" +
					" course_id = "+courseID;
		try {
			PreparedStatement delete = connect.prepareStatement(deleteQuery);
			delete.executeUpdate();
		} catch (SQLException e) {
			System.out.println("!Couldn't delete.");
		}
	}
}

@FunctionalInterface
interface AlterDatabase {
	String alter(Course c);
}