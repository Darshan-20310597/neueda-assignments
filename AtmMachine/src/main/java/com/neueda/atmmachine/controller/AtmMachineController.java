package com.neueda.atmmachine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.atmmachine.exceptions.AccountsException;
import com.neueda.atmmachine.model.Account;
import com.neueda.atmmachine.model.AccountResponse;
import com.neueda.atmmachine.model.ExceptionResponse;
import com.neueda.atmmachine.model.LoginVo;
import com.neueda.atmmachine.model.WithdrawVo;
import com.neueda.atmmachine.service.AtmMachineService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(value = "/api")
public class AtmMachineController {
	
	
	final static Logger LOGGER = LoggerFactory.getLogger(AtmMachineController.class);

    @Autowired
    private  AtmMachineService atmMachineService;
    
    
    @GetMapping("/accountdetails")
    public ResponseEntity<?> getAccountDeatils(@RequestBody LoginVo loginvo) throws AccountsException {
        LOGGER.info("getAccountDeatils initiated: " + loginvo.getAccountNumber());
        Account response = atmMachineService.getDetails(loginvo.getAccountNumber(), loginvo.getPin());
        LOGGER.info("getAccountDeatils completed: " + response.toString());
        AccountResponse returnedvalues = new AccountResponse();
        returnedvalues.setAccountNumber(response.getAccountNumber());
        returnedvalues.setBalance(response.getBalance());
        return new ResponseEntity<>(returnedvalues, HttpStatus.OK);
    }
    
    @GetMapping("/withdraw")
    public ResponseEntity<?> withdrawAmount(@RequestBody WithdrawVo withdrawVo) throws Exception {
        LOGGER.info("getAccountDeatils initiated: " + withdrawVo.getAccountNumber());
        AccountResponse response = atmMachineService.withdrawAmount(withdrawVo);
        //response.getBalance();
        if (response==null) {
        	LOGGER.info("Balance it too low for withdrawal or Entered amount is Greater than Balance.");
    		ExceptionResponse error = new ExceptionResponse();
    		error.setErrorMessage("Balance it too low for withdrawal or Entered amount is Greater than Balance.");
    		error.callerURL("/api/withdraw");
        	return new ResponseEntity<>(error, HttpStatus.OK);
        }
        LOGGER.info("getAccountDeatils completed: " + response.toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
   
    
    
    

}
