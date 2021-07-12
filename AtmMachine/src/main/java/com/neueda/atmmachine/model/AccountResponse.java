package com.neueda.atmmachine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AccountResponse {

	private long accountNumber;
	private double balance;
	
	public AccountResponse(int accountNumber, double balance) {
		
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	public AccountResponse() {
		
	}
	@Override
	public String toString() {
		return "AccountResponse [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}	
	
	
	
}
