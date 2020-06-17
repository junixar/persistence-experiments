package com.adcubum.persistence.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Audited
@NamedEntityGraphs({
      @NamedEntityGraph(name = "partner",
            attributeNodes = {@NamedAttributeNode("states")})
})
public class Partner implements Head {

    @Id
    public String id;

    public String birthName;

    @Version
    public Long version;

    @OneToMany(mappedBy = "partner", fetch = FetchType.EAGER)
    @Filter(name="state", condition=":keyDate BETWEEN state_begin AND state_end")
    public Set<PartnerState> states;

    @Override
    public Collection<PartnerState> getStates() {
        return states;
    }
}

