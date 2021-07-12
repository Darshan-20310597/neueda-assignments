package com.neueda.atmmachine.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginVo {
	
	private long accountNumber;
	private int pin;
	
	
	
	
	public LoginVo(long accountNumber, int pin) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
	}




	@Override
	public String toString() {
		return "LoginVo [accountNumber=" + accountNumber + ", pin=" + pin + "]";
	}




	public LoginVo() {
		super();
	}
	
	
	
	

}
