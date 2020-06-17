package com.adcubum.persistence.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Contract {

   @Id
   public String id;

   public LocalDate validFrom;

   public LocalDate validTo;

   @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   public Set<ContractState> states;
}
