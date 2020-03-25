package com.ers.repository;

import java.util.List;

import com.ers.models.Users;

public interface UsersDAO {
	
	public boolean insert(Users usr);
	
	public List<Users> findAll();
	
	public Users findById(int id);
	
	public Users findByUsername(String username);
	
	public boolean update(Users usr);
	
	public boolean delete(int id);
}
