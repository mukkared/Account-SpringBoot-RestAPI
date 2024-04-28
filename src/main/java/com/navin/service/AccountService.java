package com.navin.service;

import java.util.List;

import com.navin.entity.Account;

public interface AccountService {

	public Account createAccount(Account account);
	public Account getByAccount(Long accountId);
	public List<Account> getAllAccount();
	public Account Deposit(Long accountId, double amount);
	public Account withdraw(Long accountId, double amount);
	public void delete(Long id);
	public Account updateAccount(Long accountId, Account account);
}
