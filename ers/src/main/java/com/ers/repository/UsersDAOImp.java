package com.ers.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Role;
import com.ers.models.Users;
import com.ers.util.ConnectionUtil;
import com.ers.util.LoggerUtil;

public class UsersDAOImp implements UsersDAO{

	@Override
	public boolean insert(Users usr) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "{CALL insert_user(?, ?, ?, ?, ?, ?)}";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setString(1, usr.getUsername());
			stmt.setString(2, usr.getPassword());
			stmt.setString(3, usr.getfName());
			stmt.setString(4, usr.getlName());
			stmt.setString(5, usr.getEmail());
			Role role = usr.getType();
			switch(role) {
			case Employee:
				stmt.setInt(6, 1);
				break;
			case Manager:
				stmt.setInt(6, 2);
				break;
			default:
				stmt.setInt(6, 1);
				break;
			}
			
			return stmt.execute();
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.insert()", e);
		}
		return false;
	}

	@Override
	public List<Users> findAll() {
		List<Users> list = new ArrayList<>();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "SELECT * FROM users";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int userId = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password"); //TODO ensure password is hashed
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String email = rs.getString("email");
				int usrRole = rs.getInt("user_role");
				Role role;
				switch(usrRole) {
				case 1:
					role = Role.Employee;
					break;
				case 2:
					role = Role.Manager;
					break;
				default:
					role = Role.Employee;
					break;
				}
				
				Users usr = new Users(userId, username, password, fName, lName, email, role);
				
				list.add(usr);
			}
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.findAll()", e);
		}
		return list;
	}
	

	@Override
	public Users findById(int id) {
		Users usr = new Users();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1,  id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int usrId = rs.getInt("user_id");
				String usrName = rs.getString("username");
				String pass = rs.getString("password");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String usrEmail = rs.getString("email");
				int usrRole = rs.getInt("user_role");
				Role role;
				switch(usrRole) {
				case 1:
					role = Role.Employee;
					break;
				case 2:
					role = Role.Manager;
					break;
				default:
					role = Role.Employee;
					break;
				}
				
				usr = new Users (usrId, usrName, pass, fName, lName, usrEmail, role);
			}
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.findById()", e);
		}
		return usr;
	}

	@Override
	public Users findByUsername(String username) {
		Users usr = new Users();
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE username = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int usrId = rs.getInt("user_id");
				String usrName = rs.getString("username");
				String pass = rs.getString("password");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String usrEmail = rs.getString("email");
				int usrRole = rs.getInt("user_role");
				Role role;
				switch(usrRole) {
				case 1:
					role = Role.Employee;
					break;
				case 2:
					role = Role.Manager;
					break;
				default:
					role = Role.Employee;
					break;
				}
				
				usr = new Users (usrId, usrName, pass, fName, lName, usrEmail, role);
			}
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.findById()", e);
		}
		return usr;
	}

	@Override
	public boolean update(Users usr) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "CALL update_user (?, ?, ?, ?, ?, ?)}";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, usr.getUserId());
			stmt.setString(2, usr.getUsername());
			stmt.setString(3, usr.getPassword());
			stmt.setString(4, usr.getfName());
			stmt.setString(5, usr.getlName());
			stmt.setString(6, usr.getEmail());
			
			return stmt.execute();
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.update()", e);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "{CALL delete_user (?)}";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, id);
			
			return stmt.execute();
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in UsersDAOImp.delete()", e);
		}
		return false;
	}

}
