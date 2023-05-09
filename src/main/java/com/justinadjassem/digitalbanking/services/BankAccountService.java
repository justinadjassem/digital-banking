package com.justinadjassem.digitalbanking.services;

import com.justinadjassem.digitalbanking.entities.BankAccount;
import com.justinadjassem.digitalbanking.entities.CurrentAccount;
import com.justinadjassem.digitalbanking.entities.Customer;
import com.justinadjassem.digitalbanking.entities.SavingAccount;
import com.justinadjassem.digitalbanking.exceptions.BalanceNotSufficentException;
import com.justinadjassem.digitalbanking.exceptions.BankAccountNotFoundException;
import com.justinadjassem.digitalbanking.exceptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
    public Customer saveCustomer (Customer customer);
    CurrentAccount saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
    SavingAccount saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    BankAccount  getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount, String description) throws BankAccountNotFoundException, BalanceNotSufficentException;
    void credit(String accountId, double amount,String description) throws BankAccountNotFoundException;
    void transfer(String accountIdSource, String accountIdDestination, double amount) throws BankAccountNotFoundException, BalanceNotSufficentException;

    List<BankAccount> bankAccountList();
}
