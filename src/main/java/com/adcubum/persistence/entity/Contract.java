package com.adcubum.persistence.entity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Contract {

   @Id
   @Type(type = "uuid-char")
   public UUID id;

   @Column(columnDefinition = "CHAR(36)")
   @Type(type = "uuid-char")
   public UUID boid;

   public LocalDate stateBegin;

   public LocalDate stateEnd;

   public String payload;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "contractBoid")
   public Set<ContractPart> contractParts;

}
