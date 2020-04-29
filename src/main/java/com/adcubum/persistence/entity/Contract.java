package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Contract implements Head<ContractState> {

    @Id
    public String id;

    public String contractNo;

    public String productId;

    @OneToMany
    @JoinColumn(name = "contract_id")
    public Collection<ContractState> states;

    @OneToMany
    @JoinColumn(name = "contract_id")
    public Collection<ContractPartState> contractPartStates;

    public LocalDate validFrom;

    public LocalDate validTo;

    @Override
    public Collection<ContractState> getStates() {
        return states;
    }
}
