package com.neueda.atmmachine.service;

import org.springframework.stereotype.Service;

import com.neueda.atmmachine.model.Account;
import com.neueda.atmmachine.model.AccountResponse;
import com.neueda.atmmachine.model.WithdrawVo;


@Service
public interface AtmMachineService {
	
	public Account getDetails(long accountNumber, int pin);

	public AccountResponse withdrawAmount(WithdrawVo withdrawVo);
}
