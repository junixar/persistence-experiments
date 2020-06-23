package com.adcubum.persistence.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;

@NamedEntityGraphs({
      @NamedEntityGraph(name = "contract",
            attributeNodes = {@NamedAttributeNode("states")})})
@Entity
public class Contract {

   @Id
   public String id;

   public LocalDate validFrom;

   public LocalDate validTo;

   @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @Filter(name = "state")
   public Set<ContractState> states;

   @OneToMany(mappedBy = "original", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   public Set<Contract> abeyances;

   @ManyToOne
   public Contract original;

}
