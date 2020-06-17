package com.adcubum.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import org.hibernate.annotations.Filter;

@Entity
public class Contract implements Head<ContractState> {

    @Id
    public String id;

    public String contractNo;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    public Set<ContractState> states;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
//    @Filter(name = "state")
    public Set<ContractPartState> contractPartStates;

    public LocalDate validFrom;

    public LocalDate validTo;

    @Override
    public Collection<ContractState> getStates() {
        return states;
    }
}
