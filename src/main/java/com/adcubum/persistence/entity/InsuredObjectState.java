package com.adcubum.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class InsuredObjectState implements State<InsuredObject> {

    @Id
    public String id;

    @ManyToOne
    public InsuredObject insuredObject;

    @ManyToOne
    public ContractPart contractPart;

    public String description;

    public BigDecimal premium;

    public String offerNo;

    public LocalDate stateBegin;

    public LocalDate stateEnd;

    @Override
    public InsuredObject getHead() {
        return insuredObject;
    }
}
