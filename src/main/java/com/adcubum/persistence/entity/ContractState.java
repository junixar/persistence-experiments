package com.adcubum.persistence.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ContractState {

   @Id
   public String id;

   @ManyToOne
   public Contract contract;

   public LocalDate validFrom;

   public LocalDate validTo;

   @Enumerated(EnumType.STRING)
   public DataState dataState;

   public String processId;

}
