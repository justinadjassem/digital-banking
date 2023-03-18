package com.justinadjassem.digitalbanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("SA")
public class SavingAccount extends BankAccount{
    private Double interestRate;
}
