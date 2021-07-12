package com.neueda.atmmachine.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WithdrawVo {
	
	private long accountNumber;
	private int pin;
	private double amount;
	
	
	public WithdrawVo(long accountNumber, int pin, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.amount = amount;
	}


	public WithdrawVo() {
		super();
	}


	@Override
	public String toString() {
		return "WithdrawVo [accountNumber=" + accountNumber + ", pin=" + pin + ", amount=" + amount + "]";
	}
	
	
	

}
