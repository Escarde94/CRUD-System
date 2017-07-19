package crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import crud.model.task;
import crud.util.crudDbUtil;

public class tasksDao {
	private Connection connection;
	
	public tasksDao() {
		connection = crudDbUtil.getConnect(); //establish connection
	}
	
	public void addtask(task t) {
		try {
			//Create SQL Statement
			PreparedStatement ps = connection.prepareStatement("insert into tasks(name,description,dateCreated,dateUpdated) values (?,?,?,?)");
			
			//Set values
			ps.setString(1, t.getName());
			ps.setString(2, t.getDescription());
			ps.setDate(3, new java.sql.Date(t.getDateCreated().getTime()));
			ps.setDate(4, new java.sql.Date(t.getDateUpdated().getTime()));
			
			//Exceute SQL Statement
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletetask(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement("delete from tasks where id=?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatetask(task t) {
		try {
			PreparedStatement ps = connection.prepareStatement("update tasks set name=?, description=?, dateCreated=?, dateUpdated=? where id=?");
			
			ps.setString(1, t.getName());
			ps.setString(2, t.getDescription());
			ps.setDate(3, new java.sql.Date(t.getDateCreated().getTime()));
			ps.setDate(4, new java.sql.Date(t.getDateUpdated().getTime()));
			ps.setInt(5, t.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<task> getAllTasks(){
		List<task> tasks = new ArrayList<task>(); //create a table
		
		try {
			if(connection !=null) {
				
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from tasks");
			
			while(rs.next()) {
				task t = new task();		//create new row
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setDesc(rs.getString("description"));
				t.setDateCreated(rs.getDate("dateCreated"));
				t.setDateUpdated(rs.getDate("dateUpdated"));
				
				tasks.add(t);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;  //return the table
	}
	
	public task getTaskById(int id) {
		task t = new task();
		
		try {
			PreparedStatement ps = connection.prepareStatement("select * from tasks where id=?");
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				t.setId(rs.getInt("id"));
				t.setName(rs.getString("name"));
				t.setDesc(rs.getString("description"));
				t.setDateCreated(rs.getDate("dateCreated"));
				t.setDateUpdated(rs.getDate("dateUpdated"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
