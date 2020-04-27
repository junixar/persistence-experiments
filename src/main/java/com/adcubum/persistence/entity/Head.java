package com.adcubum.persistence.entity;

import java.util.Collection;

public interface Head<S extends State> {

    Collection<S> getStates();

}
