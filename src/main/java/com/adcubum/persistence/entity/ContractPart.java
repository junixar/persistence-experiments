package com.adcubum.persistence.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

@Entity
public class ContractPart {

   @Id
   @Type(type = "uuid-char")
   public UUID id;

   @Type(type = "uuid-char")
   public UUID boid;

   @Type(type = "uuid-char")
   public UUID contractBoid;

   public LocalDate stateBegin;

   public LocalDate stateEnd;

   public String payload;

}
