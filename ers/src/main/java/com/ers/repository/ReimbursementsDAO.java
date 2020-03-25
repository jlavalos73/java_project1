package com.ers.repository;

import java.util.List;

import com.ers.models.Reimbursements;
import com.ers.models.Status;
import com.ers.models.Type;
import com.ers.models.Users;

public interface ReimbursementsDAO {
	
	public boolean insert(Users usr, Reimbursements reim);
	
	public boolean approve(Users usr, Reimbursements reim);
	
	public List<Reimbursements> findAll();
	
	public List<Reimbursements> findByType(Type type);
	
	public List<Reimbursements> findByStatus(Status status);
	
	public List<Reimbursements> findByUser(Users usr);
	
	public Reimbursements findById(Reimbursements r);
	
	
}
