package com.ers.services;

import java.util.List;

import com.ers.models.Reimbursements;
import com.ers.models.Status;
import com.ers.models.Type;
import com.ers.models.Users;
import com.ers.repository.ReimbursementsDAO;
import com.ers.repository.ReimbursementsDAOImp;

public class ReimbursementService {
	
	ReimbursementsDAO repository = null;
	
	public ReimbursementService() {
		super();
		this.repository = new ReimbursementsDAOImp();
	}
	
	public boolean insert(Users usr, Reimbursements reim) {
		return repository.insert(usr, reim);
	}
	
	public boolean approve(Users usr, Reimbursements reim) {
		return repository.approve(usr, reim);
	}
	
	public List<Reimbursements> findAll() {
		return repository.findAll();
	}
	
	public List<Reimbursements> findByType(Type type) {
		return repository.findByType(type);
	}
	
	public List<Reimbursements> findByStatus(Status status) {
		return repository.findByStatus(status);
	}
	
	public List<Reimbursements> findByUser(Users usr) {
		return repository.findByUser(usr);
	}
	
	public Reimbursements findById(Reimbursements r) {
		return repository.findById(r);
	}

}
