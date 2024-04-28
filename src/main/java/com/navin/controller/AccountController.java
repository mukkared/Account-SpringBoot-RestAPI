package com.navin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navin.entity.Account;
import com.navin.service.AccountService;

@RestController
@RequestMapping("/Account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PutMapping("/{id}/deposit")
	public ResponseEntity<Object> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){
		Double amt = request.get("amount");
		Account deposit = accountService.Deposit(id, amt);
		return new ResponseEntity<>(deposit,HttpStatus.OK);
	}
	
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<Object> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
		Double amt = request.get("amount");
		Account withdraw = accountService.withdraw(id, amt);
		return new ResponseEntity<>(withdraw,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Account>> allAccounts(){
		List<Account> allAccount = accountService.getAllAccount();
		return new ResponseEntity<List<Account>>(allAccount,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getByAccount(@PathVariable Long id){
		Account accountId = accountService.getByAccount(id);
		return new ResponseEntity<>(accountId,HttpStatus.OK);
	}
	
	@PostMapping("/saveAccount")
	public ResponseEntity<Account> saveAccount(@RequestBody Account account){
		Account createAccount = accountService.createAccount(account);
		return new ResponseEntity<>(createAccount,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account){
		Account updateAccount = accountService.updateAccount(id, account);
		return new ResponseEntity<>(updateAccount,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Account> DeleteAccount(@PathVariable Long id){
		accountService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
