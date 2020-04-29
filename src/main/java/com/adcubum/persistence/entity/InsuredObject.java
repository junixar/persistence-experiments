package com.adcubum.persistence.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class InsuredObject implements Head<InsuredObjectState> {

    @Id
    public String id;

    public String description;

    public BigDecimal premium;

    public String offerNo;

    @OneToMany
    @JoinColumn(name = "insured_object_id")
    public Collection<InsuredObjectState> states;

    public LocalDate validFrom;

    public LocalDate validTo;

    @Override
    public Collection<InsuredObjectState> getStates() {
        return states;
    }
}
