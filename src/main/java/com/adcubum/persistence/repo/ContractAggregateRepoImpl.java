package com.adcubum.persistence.repo;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.entity.ContractState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public class ContractAggregateRepoImpl implements ContractAggregateRepo {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<ContractState> findById(String contractId, LocalDate keyDate) {
        return Optional.empty();
    }

    @Override
    public void save(ContractState contractState) {
        if (stateIsConnectedToLastExistingState(contractState)) {
            simplyCreateStateAsIs(contractState);
        }
        
        Collection<ContractState> touchedStates = findAllStatesTouchedByTheGivenOne(contractState);

        if (touchedStates.isEmpty()) {
            useCase1CreateNewContractAndNewContractState(contractState);
        } else {

            //detect existing (the same begin/end dates) state with or without changes in content

            //the case with termination of the state is still nor clarified.
            //For MVP we could update the existing state (business attributes as well as state_end), identified by id

            for (ContractState touchedState : touchedStates) {
                if (touchedState.stateBegin.isBefore(contractState.stateBegin) && !touchedState.stateEnd.isAfter(contractState.stateEnd)) {
                    //i.e. touchedState.stateBegin==01.01.2020 and touchedState.stateEnd==31.01.2020
                    // contractState.stateBegin==15.01.2020 and contractState.stateEnd==31.12.2020
                    terminateStateToGivenDate(touchedState, contractState.stateBegin);
                } else if (isFirstStateIncludedIntoSecondOne(touchedState, contractState)) {
                    //i.e. touchedState.stateBegin==01.02.2020 and touchedState.stateEnd==01.12.2020
                    // contractState.stateBegin==15.01.2020 and contractState.stateEnd==31.12.2020
                    deactivate(touchedState);
                } else if (touchedState.stateBegin.isAfter(contractState.stateBegin)
                        && touchedState.stateBegin.isBefore(contractState.stateEnd)) {
                    //i.e. touchedState.stateBegin==01.02.2020 and touchedState.stateEnd==01.01.3000
                    // contractState.stateBegin==15.01.2020 and contractState.stateEnd==31.12.2020
                    ContractState newTouchedState = cloneAndChangeStateBeginOfNewOneToGivenDate(touchedState, contractState.stateBegin);
                    deactivate(touchedState);
                } else if (isFirstStateIncludedIntoSecondOne(contractState, touchedState)) {
                    //i.e. touchedState.stateBegin==01.01.2020 and touchedState.stateEnd==01.01.3000
                    // contractState.stateBegin==15.01.2020 and contractState.stateEnd==31.12.2020
                    ContractState newTouchedStateBefore = cloneAndCloseToGivenDate(touchedState, contractState.stateBegin);
                    ContractState newTouchedStateAfter = cloneAndChangeStateBeginOfNewOneToGivenDate(touchedState, contractState.stateBegin);
                    deactivate(touchedState);
                }
            }

            saveNewState(contractState);
        }

    }

    private void simplyCreateStateAsIs(ContractState contractState) {
        throw new UnsupportedOperationException();
    }

    private boolean stateIsConnectedToLastExistingState(ContractState contractState) {
        throw new UnsupportedOperationException();
    }

    private void saveNewState(ContractState contractState) {
        throw new UnsupportedOperationException();
    }

    private ContractState cloneAndCloseToGivenDate(ContractState touchedState, LocalDate stateBegin) {
        throw new UnsupportedOperationException();
    }

    private ContractState cloneAndChangeStateBeginOfNewOneToGivenDate(ContractState touchedState, LocalDate stateBegin) {
        throw new UnsupportedOperationException();
    }

    private void deactivate(ContractState touchedState) {
        throw new UnsupportedOperationException();
    }

    private void terminateStateToGivenDate(ContractState touchedState, LocalDate stateBegin) {
        throw new UnsupportedOperationException();
    }

    private Collection<ContractState> findAllStatesTouchedByTheGivenOne(ContractState contractState) {
        throw new UnsupportedOperationException();
    }

    private boolean isFirstStateIncludedIntoSecondOne(ContractState firstState, ContractState secondOne) {
        throw new UnsupportedOperationException();
    }

    private void useCase1CreateNewContractAndNewContractState(ContractState contractState) {

    }

}
