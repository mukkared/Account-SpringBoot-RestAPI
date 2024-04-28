package com.navin.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navin.entity.Account;
import com.navin.exception.ResourceNotFoundException;
import com.navin.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
	    Account existingAccount = accountRepository.findById(account.getAccountId()).orElse(null);
	    if (existingAccount == null) {
	        Long accountNumber = generateAccountNumber();
	        account.setAccountId(accountNumber);
	        return accountRepository.save(account);
	    } else {
	        throw new ResourceNotFoundException("Account already exists: " + account.getAccountId());
	    }
	}


	@Override
	public Account getByAccount(Long accountId) {
		Optional<Account> findByAccount = accountRepository.findById(accountId);
		Account account = null;
		if(findByAccount.isPresent()) {
			account = findByAccount.get();
		} else {
			throw new ResourceNotFoundException("Account has not present: "+accountId);
		}
		return account;
	}

	@Override
	public List<Account> getAllAccount() {
		List<Account> findAll = accountRepository.findAll();
		if(findAll.isEmpty()) {
			throw new ResourceNotFoundException("Account details has not presented ");
		}else {
			return findAll;
		}
	}

	@Override
	public Account Deposit(Long accountId, double amount) {
		Account depositAmt = null;
		Optional<Account> findById = accountRepository.findById(accountId);
		if(findById.isPresent()) {
			Account account = findById.get();
			double tot = account.getAmount()+amount;
			account.setAmount(tot);
			depositAmt = accountRepository.save(account);
		}
		return depositAmt ;
	}

	@Override
	public Account withdraw(Long accountId, double amount) {
		Account depositAmt = null;
		Optional<Account> findById = accountRepository.findById(accountId);
		if(findById.isPresent()) {
			Account account = findById.get();
			double tot = account.getAmount()-amount;
			account.setAmount(tot);
			depositAmt = accountRepository.save(account);
		}
		return depositAmt ;
	}

	@Override
	public void delete(Long id) {
		Optional<Account> accountId = accountRepository.findById(id);
		if(accountId.isPresent()) {
			accountRepository.deleteById(id);
		}else {
			throw new ResourceNotFoundException("Account has not found: "+id);
		}
	}
	
	@SuppressWarnings("unused")
	private Long generateAccountNumber() {
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	    int numDigits = 5 + random.nextInt(6); // Generate a random number between 5 and 10 (inclusive)
	    for (int i = 0; i < numDigits; i++) {
	        int digit = random.nextInt(10); // Generate a random digit between 0 and 9
	        sb.append(digit);
	    }
	    return Long.parseLong(sb.toString());
	}


	@Override
	public Account updateAccount(Long accountId, Account account) {
		Optional<Account> accId = accountRepository.findById(accountId);
		Account updates = null;
		if(accId.isPresent()) {
			Account updateAccount = accId.get();
			updateAccount.setAccountName(account.getAccountName());
			updateAccount.setHoldername(account.getHoldername());
			updateAccount.setAmount(account.getAmount());
			updates = accountRepository.save(updateAccount);
		}else {
			throw new ResourceNotFoundException("Account details now found: "+accountId);
		}
		return updates;
	}

}
