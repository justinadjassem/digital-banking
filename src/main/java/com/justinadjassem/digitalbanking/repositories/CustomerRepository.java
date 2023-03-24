package com.justinadjassem.digitalbanking.repositories;

import com.justinadjassem.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
