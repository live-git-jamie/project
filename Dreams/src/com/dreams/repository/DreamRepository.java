package com.dreams.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dreams.model.Dream;

public class DreamRepository {
	private final String dbName = "dreamsdb";
	
	private String url = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "my#1SQLroot";
	
	private Connection connection = null;
	
	private void openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url + dbName,
				user, pass);
		System.out.println("Connection Established");
	}
	private void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				System.out.println("Closed successfully.");
			} catch(SQLException e) {
				System.out.println("Closing connection failed.");
				System.out.println(e);
			}
		}
	}
	
	public boolean saveDream(Dream dream) {
		try {
			openConnection();
			// Create statement
			Statement statement = connection.createStatement();
			System.out.println("Statement created!");
			// Execute query
			String insertQuery = "insert into dream_entry values (null,'"
					+dream.getDreamTitle()+"','"+dream.getDreamDate()+"',"
					+dream.getDreamDuration()+",'"+dream.getDreamDescr()
					+"',"+dream.getDreamRecordsId()+")";
			System.out.println("insertQuery: " + insertQuery);
			statement.executeUpdate(insertQuery);
			
			closeConnection();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean deleteDream(String date) {
		try {
			openConnection();
			// Create statement
			Statement statement = connection.createStatement();
			System.out.println("Statement created!");
			
			// Create and execute query.
			String deleteQuery = "delete from dream_entry where dream_date='"
					+date+"'";
			System.out.println("deleteQuery: " + deleteQuery);
			statement.executeUpdate(deleteQuery);   
			
			closeConnection();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public List<Dream> searchDatabase(String stringToSearch) {
		System.out.println("@searchDatabase::stringToSearch="+stringToSearch);
		boolean addAll = stringToSearch.isEmpty();
		List<String> keywords = null;
		if(!addAll) {
			keywords = new ArrayList<>(Arrays.asList(stringToSearch.split("\\s+")));
		}
		
		// Keep track of ids and their priorities
		List<Integer> ids = new ArrayList<>();
		List<Integer> priorities = new ArrayList<>();
		// Get all dream descriptions from database
		// NOTE: as of right now, it will return all
		//			dream entries regardless of dream_records_id
		//		 This will change once I figure out how
		//			to remember which user is currently
		//			logged in
		try {
			openConnection();
			// Create statement and get the resultSets
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(
					"select dream_id, dream_descr "
					+ "from dream_entry");
			// Iterate
			String dream_descr = "";
			int dream_id = -1;
			while(resultSet.next()) {
				dream_descr = resultSet.getString("dream_descr");
				dream_id = Integer.parseInt(resultSet.getString("dream_id")); 

				if(addAll) {
					ids.add(dream_id);
					priorities.add(-1);
				} else {
					int priority = 0;
					for(String key : keywords) {
						if(dream_descr.contains(key)) priority++;
					}
					if(priority > 0) {
						ids.add(dream_id);
						priorities.add(priority);
					}
				}
			}
			
			closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return toDream(idsPrioritized(ids,priorities));
	}
	/* Helper method to sort ids based on their priorities */
	public List<Integer> idsPrioritized(List<Integer> ids, List<Integer> priorities) {
		List<Integer> final_ids = new ArrayList<>();
		// If empty...
		if(ids.isEmpty() || priorities.isEmpty()) return final_ids;
		else if(priorities.get(0) == -1) return ids;
		// Else...
		while(!priorities.isEmpty()) {
			int max_i = 0;
			int max = priorities.get(0);
			for(int i = 1; i < priorities.size(); i++) {
				if(priorities.get(i) > max) {
					max_i = i;
					max = priorities.get(i);
				}
			}
			// Remove
			priorities.remove(max_i);
			// Insert corresponding id into final_ids
			final_ids.add(ids.get(max_i));
		}
		System.out.println("@idsPrioritized::");
		final_ids.forEach((Integer id)->System.out.println(id));
		return final_ids;
	}
	public List<Dream> toDream(List<Integer> ids) {
		List<Dream> dreams = new ArrayList<>();
		// If empty...
		if(ids.isEmpty()) return dreams;
		// Else...
		try {
			openConnection();
			// Create statement
			PreparedStatement statement = connection.prepareStatement(
					"select * from dream_entry " + 
					"where dream_id = ?");
			ResultSet resultSet = null;
			for(Integer id : ids) {
				statement.setInt(1, id);
				resultSet = statement.executeQuery();
				resultSet.next();
				// Add
				dreams.add(new Dream(resultSet.getInt("dream_id"),
						resultSet.getInt("dream_records_id"),
						resultSet.getDate("dream_date").toString(),
						resultSet.getString("dream_title"),
						resultSet.getString("dream_descr"),
						resultSet.getFloat("dream_duration")));
			}
			
			closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
		
		return dreams;
	}
}
