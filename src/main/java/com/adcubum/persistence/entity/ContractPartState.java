package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ContractPartState implements State<ContractPart> {

    @Id
    public String id;

    @ManyToOne
    @JoinColumn(nullable = false)
    public ContractPart contractPart;

    @ManyToOne
    public Contract contract;

    public Integer premium;

    public LocalDate stateBegin;

    public LocalDate stateEnd;

    @Override
    public ContractPart getHead() {
        return contractPart;
    }
}
