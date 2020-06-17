@FilterDef(
        name = "state",
        parameters = {@ParamDef(name = "keyDate", type = "timestamp")},
        defaultCondition = ":keyDate BETWEEN state_begin AND state_end"
)
package com.adcubum.persistence.entity;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;