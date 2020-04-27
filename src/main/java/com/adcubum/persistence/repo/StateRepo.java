package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.ContractState;
import com.adcubum.persistence.entity.State;

import java.time.LocalDate;
import java.util.Optional;

public interface StateRepo<S extends State> {

    public Optional<S> findById(String id, LocalDate keyDate);

    public S saveAsIs(S state);

    public S createNewLastStateInFuture(S state, LocalDate stateBegin);

    public S createNewIntermediateState(S state, LocalDate stateBegin, LocalDate stateEnd);

}
