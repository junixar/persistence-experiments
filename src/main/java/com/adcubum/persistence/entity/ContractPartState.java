package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ContractPartState implements State<ContractPart> {

    @Id
    public String id;

    @ManyToOne
    public ContractPart contractPart;

    @ManyToOne
    public Contract contract;

    public String description;

    public BigDecimal premium;

    public String offerNo;

    public LocalDate stateBegin;

    public LocalDate stateEnd;

    @Override
    public ContractPart getHead() {
        return contractPart;
    }
}
