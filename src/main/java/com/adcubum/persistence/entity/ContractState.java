package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class ContractState implements State<Contract> {

    @Id
    public String id;

    @ManyToOne
    public Contract contract;

    public String description;

    public String offerNo;

    public LocalDate stateBegin;

    public LocalDate stateEnd;

    @Override
    public Contract getHead() {
        return contract;
    }
}
