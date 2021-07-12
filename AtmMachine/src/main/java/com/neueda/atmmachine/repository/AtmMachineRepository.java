package com.neueda.atmmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neueda.atmmachine.model.Account;



public interface AtmMachineRepository extends JpaRepository<Account, Long> {
	
	Account findByAccountNumber(long accountNumber);
	

}
