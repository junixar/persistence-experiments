package com.adcubum.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class ContractState implements State<Contract> {

    @Id
    public String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    @Fetch(FetchMode.JOIN)
    public Contract contract;

    public String offerNo;

    public LocalDate stateBegin;

    public LocalDate stateEnd;

    @Override
    public Contract getHead() {
        return contract;
    }
}
