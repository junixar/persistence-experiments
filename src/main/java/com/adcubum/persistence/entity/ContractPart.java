package com.adcubum.persistence.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Entity
public class ContractPart implements Head<ContractPartState> {

    @Id
    public String id;

    public String productPartId;

    @OneToMany
    @JoinColumn(name = "contract_part_id")
    public Set<ContractPartState> states;

    @OneToMany
    @JoinColumn(name = "contract_part_id")
    public Collection<InsuredObjectState> insuredObjects;

    public LocalDate validFrom;

    public LocalDate validTo;

    @Override
    public Collection<ContractPartState> getStates() {
        return states;
    }
}
