package com.adcubum.persistence.service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adcubum.persistence.entity.Contract;
import com.adcubum.persistence.entity.QContract;
import com.adcubum.persistence.entity.QContractPart;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Service
public class PersistenceService {

   private EntityManager entityManager;

   @Autowired
   public PersistenceService(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   public <T> Optional<T> find(Class<T> entityClass, Object primaryKey) {
      return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
   }

   public Optional<Contract> load(UUID primaryKey) {
      QContract qContract = QContract.contract;
      QContractPart qContractPart = QContractPart.contractPart;

      JPAQuery<?> query = new JPAQuery<Void>(entityManager);
      JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

      JPAQuery<Contract> jpaQuery = queryFactory.selectFrom(qContract)
            .join(qContractPart)
            .on(qContract.boid.eq(qContractPart.contractBoid))
            .where(qContract.id.eq(primaryKey));
      Contract contract = jpaQuery.where(qContract.id.ne(UUID.randomUUID())).fetchOne();


      return Optional.ofNullable(
            contract);

   }

}
