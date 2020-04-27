package com.adcubum.persistence.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Collection;

@Entity
@FilterDef(name="state", parameters=@ParamDef( name="keyDate", type="timestamp" ))
@Audited
public class Partner implements Head {

    @Id
    public String id;

    public String birthName;

    @Version
    public Long version;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="PARTNER_ID")
    @Filter(name="state", condition=":keyDate BETWEEN state_begin AND state_end")
    public Collection<PartnerState> states;

    @Override
    public Collection<PartnerState> getStates() {
        return states;
    }
}

