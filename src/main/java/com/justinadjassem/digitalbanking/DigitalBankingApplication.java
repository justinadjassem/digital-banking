package com.justinadjassem.digitalbanking;

import com.justinadjassem.digitalbanking.entities.*;
import com.justinadjassem.digitalbanking.enums.AccountStatus;
import com.justinadjassem.digitalbanking.enums.OperationType;
import com.justinadjassem.digitalbanking.exceptions.BalanceNotSufficentException;
import com.justinadjassem.digitalbanking.exceptions.BankAccountNotFoundException;
import com.justinadjassem.digitalbanking.exceptions.CustomerNotFoundException;
import com.justinadjassem.digitalbanking.repositories.AccountOperationRepository;
import com.justinadjassem.digitalbanking.repositories.BankAccountRepository;
import com.justinadjassem.digitalbanking.repositories.CustomerRepository;
import com.justinadjassem.digitalbanking.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {
	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}
	@Bean
	CommandLineRunner start(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Michel","Lucas","Justin").forEach(name ->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listCustomers().forEach(customer -> {
				try {
					bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());
					List<BankAccount> bankAccounts = bankAccountService.bankAccountList();
					for (BankAccount bankAccount:bankAccounts){
						for (int i = 0; i < 10; i++) {
							bankAccountService.credit(bankAccount.getId(),10000+Math.random()*120000,"Credit");
							bankAccountService.debit(bankAccount.getId(),1000+Math.random()*9000,"Debit");
						}
					}
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				} catch (BankAccountNotFoundException | BalanceNotSufficentException e) {
					e.printStackTrace();
				}

			});
//			customerRepository.findAll().forEach(cust->{
//				CurrentAccount currentAccount = new CurrentAccount();
//				currentAccount.setId(UUID.randomUUID().toString());
//				currentAccount.setBalance(Math.random()*90000);
//				currentAccount.setCreatedAt(new Date());
//				currentAccount.setStatus(AccountStatus.CREATED);
//				currentAccount.setCustomer(cust);
//				currentAccount.setOverDraft(9000.0);
//				bankAccountRepository.save(currentAccount);
//
//				SavingAccount savingAccount = new SavingAccount();
//				savingAccount.setId(UUID.randomUUID().toString());
//				savingAccount.setBalance(Math.random()*90000);
//				savingAccount.setCreatedAt(new Date());
//				savingAccount.setStatus(AccountStatus.CREATED);
//				savingAccount.setCustomer(cust);
//				savingAccount.setInterestRate(5.5);
//				bankAccountRepository.save(savingAccount);
//			});
//			bankAccountRepository.findAll().forEach(acc ->{
//				for (int i = 0; i < 10; i++) {
//					AccountOperation accountOperation = new AccountOperation();
//					accountOperation.setOperationDate(new Date());
//					accountOperation.setAmount(Math.random()*12000);
//					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
//					accountOperation.setBankAccount(acc);
//					accountOperationRepository.save(accountOperation);
//
//				}
//			});
		};
	}

}
