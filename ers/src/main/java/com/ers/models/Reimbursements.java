package com.ers.models;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reimbursements {
	
	private int reimId;
	private double amount;
	private LocalDateTime submitted;
	private LocalDateTime resolved;
	private String description;
	private Blob receipt;
	private Users author;
	private Users resolver;
	private Status rStatus;
	private Type rType;
	
	public Reimbursements() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reimbursements(int reimId, double amount, LocalDateTime submitted, LocalDateTime resolved,
			String description, Blob receipt, Users author, Users resolver, Status rStatus, Type rType) {
		super();
		this.reimId = reimId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.rStatus = rStatus;
		this.rType = rType;
	}

	public int getReimId() {
		return reimId;
	}

	public void setReimId(int reimId) {
		this.reimId = reimId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getSubmitted() {
		return submitted;
	}

	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}

	public LocalDateTime getResolved() {
		return resolved;
	}

	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public Users getResolver() {
		return resolver;
	}

	public void setResolver(Users resolver) {
		this.resolver = resolver;
	}

	public Status getrStatus() {
		return rStatus;
	}

	public void setrStatus(Status rStatus) {
		this.rStatus = rStatus;
	}

	public Type getrType() {
		return rType;
	}

	public void setrType(Type rType) {
		this.rType = rType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, rStatus, rType, reimId, resolved, resolver, submitted);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Reimbursements)) {
			return false;
		}
		Reimbursements other = (Reimbursements) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& rStatus == other.rStatus && rType == other.rType && reimId == other.reimId
				&& Objects.equals(resolved, other.resolved) && Objects.equals(resolver, other.resolver)
				&& Objects.equals(submitted, other.submitted);
	}

	@Override
	public String toString() {
		return "Reimbursements [reimId=" + reimId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", author=" + author + ", resolver=" + resolver
				+ ", rStatus=" + rStatus + ", rType=" + rType + "]";
	}
}
