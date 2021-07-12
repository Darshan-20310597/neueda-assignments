package com.neueda.atmmachine.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;





@Getter
@Setter


@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long accountNumber;
	private int pin;
	private double openingBalance;
	private double overDraft;
	private double balance;
	


	public Account() {
		super();

	}


	@Override
	public String toString() {
		return "Account [id=" + id + ", accountNumber=" + accountNumber + ", pin=" + pin + ", openingBalance="
				+ openingBalance + ", overDraft=" + overDraft + ", balance=" + balance + "]";
	}


	public Account(long id, long accountNumber, int pin, double openingBalance, double overDraft, double balance) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.openingBalance = openingBalance;
		this.overDraft = overDraft;
		this.balance = balance;
	}


	public Account(long accountNumber, int pin, double openingBalance, double overDraft, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.openingBalance = openingBalance;
		this.overDraft = overDraft;
		this.balance = balance;
	}


}
