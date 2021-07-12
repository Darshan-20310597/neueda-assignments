package com.neueda.atmmachine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.neueda.atmmachine.exceptions.AccountsException;
import com.neueda.atmmachine.exceptions.BadRequestException;
import com.neueda.atmmachine.model.Account;
import com.neueda.atmmachine.model.AccountResponse;
import com.neueda.atmmachine.model.WithdrawVo;
import com.neueda.atmmachine.repository.AtmMachineRepository;

@Component
@Service
public class AtmMachineServiceImpl implements AtmMachineService {
	
	
	final static Logger LOGGER = LoggerFactory.getLogger(AtmMachineServiceImpl.class);
	
	@Autowired
	private AtmMachineRepository atmMachineRepository;

	@Override
	public Account getDetails(long accountNumber, int pin) {
		double checkint = pin;
		double checkaccountNumber = accountNumber;
		//Account acc = new Account();
		Account accResponse = new Account();
		if (Double.isNaN(checkaccountNumber) || Double.isNaN(checkint)) {
			LOGGER.error("Invalid Account Number or Pin");
            throw new BadRequestException("Invalid Account Number or Pin");
		}
		else{
			if(atmMachineRepository.findByAccountNumber(accountNumber) == null) {
				LOGGER.error("Account Number or Pin Doesnt Exisit In DB");
	            throw new BadRequestException("Invalid Account Number or Pin");
			}
			
			else {
				accResponse = atmMachineRepository.findByAccountNumber(accountNumber);
				//LOGGER.error("Account Number or Pin Doesnt Exisit In DB" + accResponse.toString());
				if(accResponse.getPin() == pin) {
					accResponse.setAccountNumber(accountNumber);
					accResponse.setBalance(accResponse.getBalance());
				}
				else {
					LOGGER.error("Invalid Account Number or Pin");
		            throw new BadRequestException("Invalid Account Number or Pin");
				}
			}	
		}
		//LOGGER.error("Account Number or Pin Doesnt Exisit In DB" + accResponse.toString());
		return accResponse;
	}

	@Override
	public AccountResponse withdrawAmount(WithdrawVo withdrawVo) {
		double balance = 0;
		Account acc = getDetails(withdrawVo.getAccountNumber(), withdrawVo.getPin());
		balance = acc.getBalance();
		LOGGER.error("Entered Balance is too low" + acc.getPin());
		if(balance < 0) {
			LOGGER.error("Entered Balance is too low" + acc.getId());
			acc.setBalance(0);
			return null;	
		}
		else if (withdrawVo.getAmount() > balance) {
			LOGGER.error("Entered Amount is greater than Balance");
			return null;
		}
		
		balance = balance - withdrawVo.getAmount();
		acc.setBalance(balance);
		atmMachineRepository.save(acc);
		AccountResponse accresponse = new AccountResponse();
		accresponse.setAccountNumber(acc.getAccountNumber());
		accresponse.setBalance(acc.getBalance());
		return accresponse;
	}

	
}
