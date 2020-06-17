package com.adcubum.persistence.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.Objects;

@Entity
@Audited
@FilterDef(name="state", parameters=@ParamDef( name="keyDate", type="timestamp" ))
public class PartnerState implements State<Partner>  {

    @Id
    public String id;

    public String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PARTNER_ID", nullable = false, insertable = false, updatable = false)
    public Partner partner;

    public Date stateBegin;

    public Date stateEnd;

    @Override
    public Partner getHead() {
        return partner;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PartnerState that = (PartnerState) o;
        return Objects.equals(id, that.id);
    }

    @Override public int hashCode() {
        return Objects.hash(id);
    }
}
