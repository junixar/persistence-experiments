package com.adcubum.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class ContractPart implements Head<ContractPartState> {

    @Id
    public String id;

    public String productPartId;

    public String billingAddress;

    @OneToMany
    @JoinColumn(name = "contract_part_id")
    public Collection<ContractPartState> states;

    @OneToMany
    @JoinColumn(name = "contract_part_id")
    public Collection<InsuredObjectState> insuredObjects;

    public LocalDate begin;

    public LocalDate end;

    @Override
    public Collection<ContractPartState> getStates() {
        return states;
    }
}
