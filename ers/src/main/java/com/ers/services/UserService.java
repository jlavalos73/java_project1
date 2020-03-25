package com.ers.services;

import java.util.List;

import com.ers.models.Users;
import com.ers.repository.UsersDAO;
import com.ers.repository.UsersDAOImp;

public class UserService {
	UsersDAO repository = null;
	
	public UserService() {
		super();
		this.repository = new UsersDAOImp();
	}
	
	public boolean insert(Users usr) {
		return repository.insert(usr);
	}
	
	public List<Users> findAll() {
		return repository.findAll();
	}
	
	public Users findById(int id) {
		return repository.findById(id);
	}
	
	public Users findByUsername(String username) {
		return repository.findByUsername(username);
	}
	
	public boolean update(Users usr) {
		return repository.update(usr);
	}
	
	public boolean delete(int id) {
		return repository.delete(id);
	}
}
