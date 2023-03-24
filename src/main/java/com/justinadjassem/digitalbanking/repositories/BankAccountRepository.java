package com.justinadjassem.digitalbanking.repositories;

import com.justinadjassem.digitalbanking.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
}
