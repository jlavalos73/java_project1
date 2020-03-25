package com.ers.repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ers.models.Reimbursements;
import com.ers.models.Role;
import com.ers.models.Status;
import com.ers.models.Type;
import com.ers.models.Users;
import com.ers.util.ConnectionUtil;
import com.ers.util.LoggerUtil;

public class ReimbursementsDAOImp implements ReimbursementsDAO {

	@Override
	public boolean insert(Users usr, Reimbursements reim) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL insert_reim(?, ?, ?, ?, ?, ?)}";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setDouble(1, reim.getAmount());
			stmt.setTimestamp(2, Timestamp.valueOf(reim.getSubmitted()));
			stmt.setString(3, reim.getDescription());
			stmt.setInt(4, reim.getAuthor(usr));
			
			Status rStatus = reim.getrStatus();
			Type rType = reim.getrType();
			
			switch(rStatus) {
			case Pending:
				stmt.setInt(5, 1);
				break;
			case Denied:
				stmt.setInt(5, 2);
				break;
			case Approved:
				stmt.setInt(5, 3);
				break;
			default:
				stmt.setInt(5, 1);
				break;
			}
			
			switch(rType) {
			case Lodging:
				stmt.setInt(6, 1);
				break;
			case Travel:
				stmt.setInt(6, 2);
				break;
			case Food:
				stmt.setInt(6, 3);
				break;
			case Other:
				stmt.setInt(6, 4);
				break;
			default:
				stmt.setInt(6, 1);
				break;
			}
			
			return stmt.execute();
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.insert()", e);
		}
		return false;
	}

	@Override
	public boolean approve(Users usr, Reimbursements reim) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "{CALL approve_reim(?, ?, ?, ?)}";
			
			CallableStatement stmt = conn.prepareCall(sql);
			
			stmt.setInt(1, reim.getReimId());
			stmt.setTimestamp(2, Timestamp.valueOf(reim.getResolved()));
			stmt.setInt(3, reim.getResolver(usr));
			
			Status rStatus = reim.getrStatus();
			
			switch(rStatus) {
			case Denied:
				stmt.setInt(4, 2);
				break;
			case Approved:
				stmt.setInt(4, 3);
				break;
			default:
				stmt.setInt(4, 3);
				break;
			}
			
			return stmt.execute();
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.approve()", e);
		}
		return false;
	}

	@Override
	public List<Reimbursements> findAll() {
		List<Reimbursements> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int rId = rs.getInt("r_id");
				double amount = rs.getDouble("r_amount");
				LocalDateTime sub = rs.getTimestamp("r_submitted").toLocalDateTime();
				LocalDateTime res;
				if(rs.getTimestamp("r_reesolved") != null) {
					res = rs.getTimestamp("r_resolved").toLocalDateTime();
				} else {
					res = null;
				}
				String desc = rs.getString("r_description");
				int author = rs.getInt("r_author");
				int resolver = rs.getInt("r_resolver");
				int status = rs.getInt("r_status_id");
				int type = rs.getInt("r_type_id");
				Status rStatus;
				Type rType;
				
				
				switch(status) {
					case 1:
						rStatus = Status.Pending;
						break;
					case 2:
						rStatus = Status.Denied;
						break;
					case 3:
						rStatus = Status.Approved;
						break;
					default:
						rStatus = Status.Pending;
						break;
					}
				
				switch(type) {
					case 1:
						rType = Type.Lodging;
						break;
					case 2:
						rType = Type.Travel;
						break;
					case 3:
						rType = Type.Food;
						break;
					case 4:
						rType = Type.Other;
						break;
					default:
						rType = Type.Other;
						break;
					}
				
				Reimbursements r = new Reimbursements(rId, amount, sub, res, desc, author, resolver, rStatus, rType);
				list.add(r);
				}
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.findAll()", e);
		}
		return list;
	}



	@Override
	public List<Reimbursements> findByType(Type type) {
		List<Reimbursements> list = new ArrayList<>();
		int rType;
		switch(type) {
		case Lodging:
			rType = 1;
			break;
		case Travel:
			rType = 2;
			break;
		case Food:
			rType = 3;
			break;
		case Other:
			rType = 4;
			break;
		default:
			rType = 1;
			break;
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursements WHERE r_type_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, rType);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int rId = rs.getInt("r_id");
				double amount = rs.getDouble("r_amount");
				LocalDateTime sub = rs.getTimestamp("r_submitted").toLocalDateTime();
				LocalDateTime res;
				if(rs.getTimestamp("r_reesolved") != null) {
					res = rs.getTimestamp("r_resolved").toLocalDateTime();
				} else {
					res = null;
				}
				String desc = rs.getString("r_description");
				int author = rs.getInt("r_author");
				int resolver = rs.getInt("r_resolver");
				int status = rs.getInt("r_status_id");
				Status rStatus;
				
				switch(status) {
				case 1:
					rStatus = Status.Pending;
					break;
				case 2:
					rStatus = Status.Denied;
					break;
				case 3:
					rStatus = Status.Approved;
					break;
				default:
					rStatus = Status.Pending;
					break;
				}
				
				Reimbursements r = new Reimbursements(rId, amount, sub, res, desc, author, resolver, rStatus, type);
				list.add(r);
			}
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.findByType()", e);
		}
		return list;
	}
	
	@Override
	public List<Reimbursements> findByStatus(Status status) {
		List<Reimbursements> list = new ArrayList<>();
		int rStatus;
		switch(status) {
		case Pending:
			rStatus = 1;
			break;
		case Denied:
			rStatus = 2;
			break;
		case Approved:
			rStatus = 3;
			break;
		default:
			rStatus = 1;
			break;
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE r_type_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, rStatus);
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int rId = rs.getInt("r_id");
				double amount = rs.getDouble("r_amount");
				LocalDateTime sub = rs.getTimestamp("r_submitted").toLocalDateTime();
				LocalDateTime res;
				if(rs.getTimestamp("r_reesolved") != null) {
					res = rs.getTimestamp("r_resolved").toLocalDateTime();
				} else {
					res = null;
				}
				String desc = rs.getString("r_description");
				int author = rs.getInt("r_author");
				int resolver = rs.getInt("r_resolver");
				int type = rs.getInt("r_type_id");
				Type rType;
				
				switch(type) {
				case 1:
					rType = Type.Lodging;
					break;
				case 2:
					rType = Type.Travel;
					break;
				case 3:
					rType = Type.Food;
					break;
				case 4:
					rType = Type.Other;
					break;
				default:
					rType = Type.Other;
					break;
				}
				
				Reimbursements r = new Reimbursements(rId, amount, sub, res, desc, author, resolver, status, rType);
				list.add(r);
			}
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.findByStatus()", e);
		}
		return list;
	}

	@Override
	public List<Reimbursements> findByUser(Users usr) {
		List<Reimbursements> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursements WHERE r_author = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, usr.getUserId());
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int rId = rs.getInt("r_id");
				double amount = rs.getDouble("r_amount");
				LocalDateTime sub = rs.getTimestamp("r_submitted").toLocalDateTime();
				LocalDateTime res;
				if(rs.getTimestamp("r_reesolved") != null) {
					res = rs.getTimestamp("r_resolved").toLocalDateTime();
				} else {
					res = null;
				}
				String desc = rs.getString("r_description");
				int author = rs.getInt("r_author");
				int resolver = rs.getInt("r_resolver");
				int status = rs.getInt("r_status_id");
				int type = rs.getInt("r_type_id");
				Status rStatus;
				Type rType;
				
				
				switch(status) {
					case 1:
						rStatus = Status.Pending;
						break;
					case 2:
						rStatus = Status.Denied;
						break;
					case 3:
						rStatus = Status.Approved;
						break;
					default:
						rStatus = Status.Pending;
						break;
					}
				
				switch(type) {
					case 1:
						rType = Type.Lodging;
						break;
					case 2:
						rType = Type.Travel;
						break;
					case 3:
						rType = Type.Food;
						break;
					case 4:
						rType = Type.Other;
						break;
					default:
						rType = Type.Other;
						break;
					}
				
				Reimbursements r = new Reimbursements(rId, amount, sub, res, desc, author, resolver, rStatus, rType);
				list.add(r);
			}
			
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.findByUser()", e);
		}
		return list;
	}

	@Override
	public Reimbursements findById(Reimbursements r) {
		Reimbursements reim = new Reimbursements();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM reimbursement WHERE r_id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, r.getReimId());
			
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {
				return null;
			} else {
				int rId = rs.getInt("r_id");
				double amount = rs.getDouble("r_amount");
				LocalDateTime sub = rs.getTimestamp("r_submitted").toLocalDateTime();
				LocalDateTime res;
				if(rs.getTimestamp("r_reesolved") != null) {
					res = rs.getTimestamp("r_resolved").toLocalDateTime();
				} else {
					res = null;
				}
				String desc = rs.getString("r_description");
				int author = rs.getInt("r_author");
				int resolver = rs.getInt("r_resolver");
				int status = rs.getInt("r_status_id");
				int type = rs.getInt("r_type_id");
				Status rStatus;
				Type rType;
				
				
				switch(status) {
					case 1:
						rStatus = Status.Pending;
						break;
					case 2:
						rStatus = Status.Denied;
						break;
					case 3:
						rStatus = Status.Approved;
						break;
					default:
						rStatus = Status.Pending;
						break;
					}
				
				switch(type) {
					case 1:
						rType = Type.Lodging;
						break;
					case 2:
						rType = Type.Travel;
						break;
					case 3:
						rType = Type.Food;
						break;
					case 4:
						rType = Type.Other;
						break;
					default:
						rType = Type.Other;
						break;
					}
				
				reim = new Reimbursements(rId, amount, sub, res, desc, author, resolver, rStatus, rType);
			}
		} catch(SQLException e) {
			LoggerUtil.log.info("SQLException in ReimbursementsDAOImp.findById()", e);
		}
		return reim;
	}

}
